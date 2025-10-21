package com.cudzdev.echoes_of_the_void.block.entity;

import com.cudzdev.echoes_of_the_void.block.RunicCasingBlock;
import com.cudzdev.echoes_of_the_void.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class RunicCasingBlockEntity extends BlockEntity {

    private ItemStack displayedGlyph = ItemStack.EMPTY;

    public RunicCasingBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RUNIC_CASING_BLOCK_ENTITY_TYPE, pos, state);
    }

    public ItemStack getDisplayedGlyph() { return displayedGlyph; }

    public void setDisplayedGlyph(ItemStack stack) {
        this.displayedGlyph = stack.copy();
        this.markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        if (!this.displayedGlyph.isEmpty()) {
            nbt.put("displayedGlyph", this.displayedGlyph.encode(registryLookup));
        }
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        if (nbt.contains("displayedGlyph")) {
            this.displayedGlyph = ItemStack.fromNbt(registryLookup, nbt.getCompound("displayedGlyph")).orElse(ItemStack.EMPTY);
        }
        else {
            this.displayedGlyph = ItemStack.EMPTY;
        }
    }

    public void clearGlyph() {
        this.displayedGlyph = ItemStack.EMPTY;
        if (world != null && getCachedState().get(RunicCasingBlock.LIT)) {
            world.setBlockState(getPos(), getCachedState().with(RunicCasingBlock.LIT, false), 3);
        }
    }

    public void setDisplayedGlyphClient(ItemStack stack) {
        this.displayedGlyph = stack.copy();
        if (world != null && !getCachedState().get(RunicCasingBlock.LIT)) {
            world.setBlockState(getPos(), getCachedState().with(RunicCasingBlock.LIT, true), 3);
        }
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}
