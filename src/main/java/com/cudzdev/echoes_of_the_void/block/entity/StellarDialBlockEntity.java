package com.cudzdev.echoes_of_the_void.block.entity;

import com.cudzdev.echoes_of_the_void.block.StellarDialPedestalBlock;
import com.cudzdev.echoes_of_the_void.gui.StellarDialScreenHandler;
import com.cudzdev.echoes_of_the_void.item.ResonantBatteryItem;
import com.cudzdev.echoes_of_the_void.registry.ModAddresses;
import com.cudzdev.echoes_of_the_void.registry.ModBlockEntities;
import com.cudzdev.echoes_of_the_void.registry.ModDimensions;
import com.cudzdev.echoes_of_the_void.registry.ModItems;
import com.cudzdev.echoes_of_the_void.network.packet.ClearGlyphS2CPacket;
import com.cudzdev.echoes_of_the_void.network.packet.SetGlyphS2CPacket;
import com.cudzdev.echoes_of_the_void.sound.ModSounds;
import com.cudzdev.echoes_of_the_void.util.EchowayNetworkState;
import com.cudzdev.echoes_of_the_void.util.EchowayStructureUtil;
import com.cudzdev.echoes_of_the_void.util.EchowayTeleporter;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public class StellarDialBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, SidedInventory {

    private static final List<Item> GLYPH_POOL = List.of(
            ModItems.STELLAR_GLYPH_R,
            ModItems.STELLAR_GLYPH_8,
            ModItems.STELLAR_GLYPH_O,
            ModItems.STELLAR_GLYPH_W,
            ModItems.STELLAR_GLYPH_SLASH
    );

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(11, ItemStack.EMPTY);
    private final DefaultedList<ItemStack> addressInventory = DefaultedList.ofSize(5, ItemStack.EMPTY);
    private UUID gateId;

    private static final int POWER_SLOT = 5;
    private static final long RF_PER_DIAL = 2_000;
    private static final int ADDRESS_DISPLAY_START_INDEX = 6;

    private boolean isComplete = false;
    private int dialingTicks = 0;
    private int activeTicks = 0;
    private EchowayNetworkState.EchowayInfo destinationInfo;

    private EchowayState state = EchowayState.IDLE;
    private boolean hasAddress = false;

    public StellarDialBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STELLAR_DIAL_BLOCK_ENTITY, pos, state);
        this.gateId = UUID.randomUUID();
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override public int size() { return inventory.size(); }
    @Override public boolean isEmpty() { return inventory.stream().allMatch(ItemStack::isEmpty); }
    @Override public ItemStack getStack(int slot) { return this.inventory.get(slot); }
    @Override public ItemStack removeStack(int slot, int amount) { return Inventories.splitStack(this.inventory, slot, amount); }
    @Override public ItemStack removeStack(int slot) { return Inventories.removeStack(this.inventory, slot); }
    @Override public void setStack(int slot, ItemStack stack) { this.inventory.set(slot, stack); if (stack.getCount() > this.getMaxCountPerStack()) { stack.setCount(this.getMaxCountPerStack()); } }
    @Override public void clear() { this.inventory.clear(); }
    @Override public boolean canPlayerUse(PlayerEntity player) { return true; }

    @Override public int[] getAvailableSlots(Direction side) { return IntStream.range(0, size()).toArray(); }
    @Override public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) { return slot < 6; }
    @Override public boolean canExtract(int slot, ItemStack stack, Direction dir) { return true; }

    public enum EchowayState {
        IDLE,
        DIALING,
        ACTIVE
    }

    public void onFirstValidation(ServerWorld world) {
        if (!this.hasAddress) {
            assignRandomAddress(world);
        }
    }

    private void assignRandomAddress(ServerWorld world) {
        EchowayNetworkState networkState = EchowayNetworkState.getServerState(world);
        Random random = new Random();

        // Loop indefinitely until a unique address is found
        while (true) {
            DefaultedList<ItemStack> newAddress = DefaultedList.ofSize(5, ItemStack.EMPTY);
            for (int i = 0; i < 5; i++) {
                newAddress.set(i, new ItemStack(GLYPH_POOL.get(random.nextInt(GLYPH_POOL.size()))));
            }

            // Check if this randomly generated address is already in use
            Optional<EchowayNetworkState.EchowayInfo> existing = findDestination(networkState, newAddress);
            if (existing.isEmpty()) {
                // --- It's unique! Assign and register it. ---
                EchowayNetworkState.EchowayInfo info = new EchowayNetworkState.EchowayInfo(newAddress, this.pos, this.world.getRegistryKey());
                networkState.gates.put(this.gateId, info);
                networkState.markDirty();

                for (int i = 0; i < 5; i++) {
                    this.inventory.set(ADDRESS_DISPLAY_START_INDEX + i, newAddress.get(i).copy());
                    this.addressInventory.set(i, newAddress.get(i).copy());
                }
                this.hasAddress = true;
                markDirty();
                world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_LISTENERS);
                break;
            }
        }
    }

    public void onBreak() {
        if (world instanceof ServerWorld serverWorld && this.hasAddress) {
            EchowayNetworkState networkState = EchowayNetworkState.getServerState(serverWorld);
            networkState.gates.remove(this.gateId);
            networkState.markDirty();
            System.out.println("Gate " + this.gateId + " deregistered from network.");
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putBoolean("IsComplete", isComplete);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putBoolean("gate.has_address", hasAddress);
        nbt.putUuid("gate.id", gateId);

        super.writeNbt(nbt, registryLookup);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        isComplete = nbt.getBoolean("IsComplete");
        Inventories.readNbt(nbt, inventory, registryLookup);
        hasAddress = nbt.getBoolean("gate.has_address");
        if (nbt.containsUuid("gate.id")) {
            gateId = nbt.getUuid("gate.id");
        }
    }

    public void beginDialingSequence() {
        if (!(world instanceof ServerWorld serverWorld) || this.state != EchowayState.IDLE) return;

        DefaultedList<ItemStack> dialedAddress = getDialedAddress();
        if (dialedAddress == null) {
            world.playSound(null, pos, SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT, SoundCategory.BLOCKS, 0.5f, 1.0f);
            return;
        }

        if(serverWorld.getRegistryKey() == World.NETHER || serverWorld.getRegistryKey() == World.END){
            //Prevent address dialing for now
            world.playSound(null, pos, SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT, SoundCategory.BLOCKS, 0.5f, 1.0f);
            return;
        }

        if (areAddressesEqual(dialedAddress, ModAddresses.VESPERA_ADDRESS)) {

            this.destinationInfo = new EchowayNetworkState.EchowayInfo(
                    dialedAddress,
                    this.pos,
                    ModDimensions.VESPERA_KEY
            );
            startDialing();
            return;
        }

        if (areAddressesEqual(dialedAddress, ModAddresses.OVERWORLD_ADDRESS)) {
            this.destinationInfo = new EchowayNetworkState.EchowayInfo(dialedAddress, this.pos, World.OVERWORLD);
            startDialing();
            return;
        }

        if (this.hasAddress && areAddressesEqual(dialedAddress, this.addressInventory)) {
            world.playSound(null, pos, SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT, SoundCategory.BLOCKS, 0.5f, 1.0f);
            return;
        }

        EchowayNetworkState networkState = EchowayNetworkState.getServerState(serverWorld);
        Optional<EchowayNetworkState.EchowayInfo> destination = findDestination(networkState, dialedAddress);

        if (destination.isPresent()) {
            this.destinationInfo = destination.get();
            startDialing();
        } else {
            world.playSound(null, pos, SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT, SoundCategory.BLOCKS, 0.5f, 1.0f);
        }
    }

    private DefaultedList<ItemStack> getDialedAddress() {
        DefaultedList<ItemStack> dialedAddress = DefaultedList.ofSize(5, ItemStack.EMPTY);
        for (int i = 0; i < 5; i++) {
            ItemStack glyphStack = this.inventory.get(i);
            if (glyphStack.isEmpty()) return null; // Incomplete address
            dialedAddress.set(i, glyphStack);
        }
        return dialedAddress;
    }

    private Optional<EchowayNetworkState.EchowayInfo> findDestination(EchowayNetworkState networkState, DefaultedList<ItemStack> targetAddress) {
        for (EchowayNetworkState.EchowayInfo info : networkState.gates.values()) {
            if (areAddressesEqual(info.address(), targetAddress)) {
                return Optional.of(info);
            }
        }
        return Optional.empty();
    }

    private void startDialing() {
        ItemStack powerStack = this.inventory.get(POWER_SLOT);

        if (powerStack.getItem() instanceof ResonantBatteryItem batteryItem) {
            long energy = batteryItem.getEnergyCapacity(powerStack);

            if(energy >= RF_PER_DIAL){
                batteryItem.setStoredEnergy(powerStack, energy - RF_PER_DIAL);

                this.state = EchowayState.DIALING;
                this.dialingTicks = 0;
                markDirty();
                world.playSound(null, pos, SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.BLOCKS, 1.0f, 1.2f);
            }else {
                world.playSound(null, pos, SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT, SoundCategory.BLOCKS, 0.5f, 1.0f);
            }
        }
        else {
            world.playSound(null, pos, SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT, SoundCategory.BLOCKS, 0.5f, 1.0f);
        }
    }

    private boolean areAddressesEqual(DefaultedList<ItemStack> address1, DefaultedList<ItemStack> address2) {
        if (address1.size() != address2.size()) return false;
        for (int i = 0; i < address1.size(); i++) {
            if (!ItemStack.areItemsEqual(address1.get(i), address2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static void tick(World world, BlockPos pos, BlockState state, StellarDialBlockEntity entity) {
        if (world.isClient() || !(world instanceof ServerWorld serverWorld)) return;
        Direction facing = state.get(StellarDialPedestalBlock.FACING);

        if (entity.state == EchowayState.DIALING) {
            entity.dialingTicks++;
            if (entity.dialingTicks % 20 == 0 && entity.dialingTicks <= 100) {
                int glyphIndex = (entity.dialingTicks / 20) - 1;
                entity.lightUpRunicCasing(glyphIndex, facing, true);
                world.playSound(null, pos, SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.BLOCKS, 1.0f, 1.5f - (glyphIndex * 0.1f));
            }

            if (entity.dialingTicks > 120) { // Example: 5 seconds
                entity.state = EchowayState.ACTIVE;
                entity.activeTicks = 0;
                EchowayTeleporter.activatePortalBlocks(serverWorld, pos, facing);
                world.playSound(null, pos, ModSounds.PORTAL_ACTIVATE_EVENT, SoundCategory.BLOCKS, 0.5f, 1.0f);
                entity.markDirty();
            }
        } else if (entity.state == EchowayState.ACTIVE) {
            entity.activeTicks++;
            if (entity.activeTicks > 300) {
                entity.state = EchowayState.IDLE;
                entity.destinationInfo = null;
                EchowayTeleporter.deactivatePortalBlocks(serverWorld, pos, facing);
                for(int i = 0; i < 5; i++) entity.lightUpRunicCasing(i, facing, false);
                world.playSound(null, pos, SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT, SoundCategory.BLOCKS, 0.5f, 1.0f);
                entity.markDirty();
                return;
            }

            BlockPos gateOrigin = pos.offset(facing.getOpposite(), 8);
            Box portalArea;
            if (facing.getAxis() == Direction.Axis.Z) {
                portalArea = new Box(gateOrigin.getX() - 1, gateOrigin.getY() + 1, gateOrigin.getZ(),
                        gateOrigin.getX() + 2, gateOrigin.getY() + 4, gateOrigin.getZ() + 1);
            } else { // East or West
                portalArea = new Box(gateOrigin.getX(), gateOrigin.getY() + 1, gateOrigin.getZ() - 1,
                        gateOrigin.getX() + 1, gateOrigin.getY() + 4, gateOrigin.getZ() + 2);
            }

            List<Entity> entitiesInPortal = world.getOtherEntities(null, portalArea, e -> !e.isSpectator());

            for (Entity entityToTeleport : entitiesInPortal) {
                if (entity.destinationInfo != null) {
                    EchowayTeleporter.teleportToDestination(entityToTeleport, entity.destinationInfo);
                }
            }
        }
    }

    private void lightUpRunicCasing(int index, Direction facing, boolean lit) {
        if (!(world instanceof ServerWorld serverWorld) || index < 0 || index >= EchowayStructureUtil.getRunicCasingOrder().size()) return;

        BlockPos relativePos = EchowayStructureUtil.getRunicCasingOrder().get(index);
        BlockRotation rotation = getRotationForDirection(facing);
        BlockPos absolutePos = this.pos.add(relativePos.rotate(rotation));

        BlockEntity targetEntity = world.getBlockEntity(absolutePos);
        if (targetEntity instanceof RunicCasingBlockEntity casingEntity) {
            BlockState casingState = casingEntity.getCachedState();

            if (lit) {
                ItemStack glyphToDisplay = this.inventory.get(index);
                if (!glyphToDisplay.isEmpty()) {
                    SetGlyphS2CPacket packet = new SetGlyphS2CPacket(absolutePos, glyphToDisplay);
                    for (ServerPlayerEntity player : PlayerLookup.tracking(serverWorld, pos)) {
                        ServerPlayNetworking.send(player, packet);
                    }
                }
            } else {
                ClearGlyphS2CPacket packet = new ClearGlyphS2CPacket(absolutePos);
                for (ServerPlayerEntity player : PlayerLookup.tracking(serverWorld, pos)) {
                    ServerPlayNetworking.send(player, packet);
                }
            }
        }
    }

    public boolean lazyValidate() {
        if (this.isComplete) {
            return true;
        }
        // If it's not known to be complete, run the full check.
        return validateStructure();
    }

    private boolean validateStructure() {
        if (!(world instanceof ServerWorld serverWorld)) return false;

        Direction facing = getCachedState().get(StellarDialPedestalBlock.FACING);
        BlockRotation rotation = getRotationForDirection(facing);

        for (var entry : EchowayStructureUtil.getEchowayPattern().entrySet()) {
            BlockPos relativePos = entry.getKey();
            BlockPos rotatedRelativePos = relativePos.rotate(rotation);
            BlockPos checkPos = this.pos.add(rotatedRelativePos);

            if (!entry.getValue().test(this.world.getBlockState(checkPos))) {
                return false; // Validation failed
            }
        }

        if (!this.hasAddress) {
            this.onFirstValidation(serverWorld);
        }

        // If all blocks in the pattern match, the structure is complete!
        this.isComplete = true;
        return true;
    }

    private BlockRotation getRotationForDirection(Direction facing) {
        return switch (facing) {
            case SOUTH -> BlockRotation.CLOCKWISE_180;
            case WEST -> BlockRotation.COUNTERCLOCKWISE_90;
            case EAST -> BlockRotation.CLOCKWISE_90;
            default -> BlockRotation.NONE; // North
        };
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Stellar Dial");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new StellarDialScreenHandler(syncId, playerInventory, this, this.pos);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }


    public boolean hasAddress() {
        return this.hasAddress;
    }
}
