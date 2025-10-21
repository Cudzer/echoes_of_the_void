package com.cudzdev.echoes_of_the_void.gui;

import com.cudzdev.echoes_of_the_void.block.entity.StellarDialBlockEntity;
import com.cudzdev.echoes_of_the_void.registry.ModScreenHandlers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

public class StellarDialScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    public final BlockPos blockPos;
    public final StellarDialBlockEntity blockEntity;

    public StellarDialScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(pos), pos);
    }

    public StellarDialScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, BlockPos pos) {
        super(ModScreenHandlers.STELLAR_DIAL_SCREEN_HANDLER, syncId);
        this.inventory = (Inventory) blockEntity;
        checkSize(inventory, 11);
        this.blockPos = pos;
        this.blockEntity = (StellarDialBlockEntity) blockEntity;
        inventory.onOpen(playerInventory.player);

        // Resonator Slots
        this.addSlot(new Slot(inventory, 2, 80, 18)); // Slot 4
        this.addSlot(new Slot(inventory, 1, 44, 36)); // Slot 11
        this.addSlot(new Slot(inventory, 3, 116, 36)); // Slot 15
        this.addSlot(new Slot(inventory, 0, 8, 54));  // Slot 18
        this.addSlot(new Slot(inventory, 4, 152, 54)); // Slot 26

        // Power Source Slot
        this.addSlot(new Slot(inventory, 5, 80, 87)); // Slot 40



        for (int i = 0; i < 5; i++) {
            this.addSlot(new Slot(inventory, 6 + i, 44 + i * 18, 65) {
                @Override
                public boolean canInsert(ItemStack stack) {
                    return false; // Players cannot place items in these slots
                }
                @Override
                public boolean canTakeItems(PlayerEntity playerEntity) {
                    return false; // Players cannot take items from these slots
                }
            });
        }

        // Player Inventory (27 slots, indices 5-31)
        int playerInvY = 140; // This is the standard Y offset for a double chest GUI
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, playerInvY + i * 18));
            }
        }

        // Player Hotbar (9 slots, indices 32-40)
        int hotbarY = 198; // This is the standard Y offset for a double chest GUI
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, hotbarY));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);

        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            // Check if the click is from the player's inventory
            if (slotIndex >= this.inventory.size()) {
                // Try to move the item into the glyph inventory (slots 0-4)
                if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // Otherwise, try to move the item from the glyph inventory into the player's inventory
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }
}
