package com.cudzdev.echoes_of_the_void.block.entity;

import com.cudzdev.echoes_of_the_void.gui.ResonanceTableScreenHandler;
import com.cudzdev.echoes_of_the_void.registry.ModBlockEntities;
import com.cudzdev.echoes_of_the_void.registry.ModRecipes;
import com.cudzdev.echoes_of_the_void.recipe.ResonanceRecipe;
import com.cudzdev.echoes_of_the_void.recipe.ResonanceRecipeInput;
import com.cudzdev.echoes_of_the_void.sound.ModSounds;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class ResonanceTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory, SidedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int OUTPUT_SLOT = 2;

    public enum SideConfig {
        NONE,
        INPUT_CRYSTAL,
        INPUT_CATALYST,
        OUTPUT;

        public SideConfig next() { return values()[(this.ordinal() + 1) % values().length]; }
        public static SideConfig fromOrdinal(int ord) { return values()[ord]; }
    }

    private final Map<Direction, SideConfig> sideConfigs = new EnumMap<>(Direction.class);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int soundTicks = 0;
    private int maxProgress = 72;

    public ResonanceTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RESONANCE_TABLE_BLOCK_ENTITY_TYPE, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index){
                    case 0 -> ResonanceTableBlockEntity.this.progress;
                    case 1 -> ResonanceTableBlockEntity.this.maxProgress;
                    case 2, 3, 4, 5, 6, 7 -> ResonanceTableBlockEntity.this.sideConfigs.get(indexToDirection(index)).ordinal();
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index){
                    case 0 -> ResonanceTableBlockEntity.this.progress = value;
                    case 1 -> ResonanceTableBlockEntity.this.maxProgress = value;
                    case 2, 3, 4, 5, 6, 7 -> ResonanceTableBlockEntity.this.sideConfigs.put(indexToDirection(index), SideConfig.fromOrdinal(value));
                }
            }

            @Override
            public int size() {
                return 8;
            }
        };

        sideConfigs.put(Direction.UP, SideConfig.INPUT_CRYSTAL);
        sideConfigs.put(Direction.DOWN, SideConfig.OUTPUT);
        sideConfigs.put(Direction.NORTH, SideConfig.INPUT_CATALYST);
        sideConfigs.put(Direction.SOUTH, SideConfig.NONE);
        sideConfigs.put(Direction.EAST, SideConfig.NONE);
        sideConfigs.put(Direction.WEST, SideConfig.NONE);
    }

    private Direction indexToDirection(int index) {
        return Direction.values()[index - 2];
    }

    public void cycleSideConfig(Direction side) {
        if (world != null && !world.isClient) {
            sideConfigs.put(side, sideConfigs.getOrDefault(side, SideConfig.NONE).next());
            markDirty();

            world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_LISTENERS);
        }
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.echoes_of_the_void.resonance_table");
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        // This method defines which slots are accessible from which side.
        return switch (sideConfigs.getOrDefault(side, SideConfig.NONE)) {
            case INPUT_CRYSTAL -> new int[]{INPUT_SLOT_1};
            case INPUT_CATALYST -> new int[]{INPUT_SLOT_2};
            case OUTPUT -> new int[]{OUTPUT_SLOT};
            default -> new int[0]; // No slots available for NONE
        };
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        if (dir == null) return false;
        // Check if the configuration for the given side matches the slot
        return switch (sideConfigs.getOrDefault(dir, SideConfig.NONE)) {
            case INPUT_CRYSTAL -> slot == INPUT_SLOT_1;
            case INPUT_CATALYST -> slot == INPUT_SLOT_2;
            default -> false;
        };
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        if (dir == null) return false;
        // Check if the configuration for the given side is set to OUTPUT
        return sideConfigs.getOrDefault(dir, SideConfig.NONE) == SideConfig.OUTPUT && slot == OUTPUT_SLOT;
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ResonanceTableScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup)
    {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("resonance_table.progress", progress);
        nbt.putInt("resonance_table.max_progress", maxProgress);

        for (Map.Entry<Direction, SideConfig> entry : sideConfigs.entrySet()) {
            nbt.putString("side_config." + entry.getKey().getName(), entry.getValue().name());
        }
        nbt.putInt("resonance_table.sound_ticks", soundTicks);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup)
    {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("resonance_table.progress");
        maxProgress = nbt.getInt("resonance_table.max_progress");

        for (Direction dir : Direction.values()) {
            String configName = nbt.getString("side_config." + dir.getName());
            if (!configName.isEmpty()) {
                sideConfigs.put(dir, SideConfig.valueOf(configName));
            }
        }
        soundTicks = nbt.getInt("resonance_table.sound_ticks");
    }

    public static void tick(World world, BlockPos pos, BlockState state, ResonanceTableBlockEntity entity) {
        if (world.isClient()) return;

        boolean wasCrafting = entity.progress > 0;
        boolean isCrafting = false;

        if(entity.hasRecipe()) {
            isCrafting = true;
            entity.increaseCraftingProgress();
            markDirty(world, pos, state);

            if(entity.hasCraftingFinished()){
                entity.craftItem();
                entity.resetProgress();
            }
        }
        else{
            entity.resetProgress();
        }

        if (isCrafting != wasCrafting) {
            markDirty(world, pos, state);
        }

        if (isCrafting) {
            entity.soundTicks++;
            if (entity.soundTicks >= 140) {
                world.playSound(null, pos, ModSounds.RESONANCE_TABLE_PROCESSING_EVENT, SoundCategory.BLOCKS, 0.1f, 1.0f);
                entity.soundTicks = 0;
            }
        } else if (entity.soundTicks > 0) {
            entity.soundTicks = 0;
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 72;
    }

    private void craftItem() {
        Optional<RecipeEntry<ResonanceRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();

        this.removeStack(INPUT_SLOT_1, 1);
        this.removeStack(INPUT_SLOT_2, 1);
        this.setStack(OUTPUT_SLOT, new ItemStack(output.getItem(),
                this.getStack(OUTPUT_SLOT).getCount() + output.getCount()));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<ResonanceRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()){
            return false;
        }

        ItemStack output = recipe.get().value().output();

        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeEntry<ResonanceRecipe>> getCurrentRecipe() {
        return this.getWorld().getRecipeManager()
                .getFirstMatch(ModRecipes.RESONANCE_TYPE, new ResonanceRecipeInput(inventory.get(INPUT_SLOT_1), inventory.get(INPUT_SLOT_2)), this.getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = this.getStack(OUTPUT_SLOT).isEmpty() ? 64 : this.getStack(OUTPUT_SLOT).getMaxCount();
        int currentCount = this.getStack(OUTPUT_SLOT).getCount();
        return maxCount >= currentCount + count;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket(){
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup)
    {
        return createNbt(registryLookup);
    }
}
