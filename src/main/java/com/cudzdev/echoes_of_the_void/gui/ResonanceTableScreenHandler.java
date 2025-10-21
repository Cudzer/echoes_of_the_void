package com.cudzdev.echoes_of_the_void.gui;

import com.cudzdev.echoes_of_the_void.block.entity.ResonanceTableBlockEntity;
import com.cudzdev.echoes_of_the_void.gui.slot.ResultSlot;
import com.cudzdev.echoes_of_the_void.registry.ModScreenHandlers;
import com.cudzdev.echoes_of_the_void.util.ModTags;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

public class ResonanceTableScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    private final ResonanceTableBlockEntity blockEntity;
    private BlockPos blockPos;

    public ResonanceTableScreenHandler(int syncId, PlayerInventory inventory, BlockPos pos) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(pos), new ArrayPropertyDelegate(8));
        this.blockPos = pos;
    }

    public ResonanceTableScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate)
    {
        super(ModScreenHandlers.RESONANCE_TABLE_SCREEN_HANDLER, syncId);
        this.inventory = ((Inventory) blockEntity);
        this.blockEntity = ((ResonanceTableBlockEntity) blockEntity);
        this.propertyDelegate = arrayPropertyDelegate;

        this.addSlot(new Slot(inventory, 0, 56, 17){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModTags.Items.RESONANT_CRYSTALS);
            }
        });
        this.addSlot(new Slot(inventory, 1, 56, 53){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(Items.REDSTONE);
            }
        });
        this.addSlot(new ResultSlot(inventory, 2, 116, 35));

        addPlayerToInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(arrayPropertyDelegate);
    }

    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public boolean isCrafting()
    {
        return propertyDelegate.get(0) > 0;
    }

    public int getScaledArrowProgress()
    {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);
        int arrowPixelSize = 24;

        return maxProgress != 0 && progress != 0 ? progress * arrowPixelSize / maxProgress : 0;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            // Check if the click is from the player's inventory
            if (invSlot >= this.inventory.size()) {
                System.out.println("================================");
                System.out.println("Shift-clicked item: " + originalStack.getItem());
                System.out.println("Is in RESONANT_CRYSTALS tag? " + originalStack.isIn(ModTags.Items.RESONANT_CRYSTALS));
                System.out.println("================================");
                // If the item is a resonant crystal...
                if (originalStack.isIn(ModTags.Items.RESONANT_CRYSTALS)) {
                    // ...try to move it ONLY to the crystal slot (slot 0).
                    if (!this.insertItem(originalStack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                // If the item is redstone...
                else if (originalStack.isOf(Items.REDSTONE)) {
                    // ...try to move it ONLY to the catalyst slot (slot 1).
                    if (!this.insertItem(originalStack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                // If the item is neither, handle standard inventory shift-clicking.
                else if (invSlot < this.inventory.size() + 27) {
                    if (!this.insertItem(originalStack, this.inventory.size() + 27, this.slots.size(), true)) {
                        return ItemStack.EMPTY;
                    }
                } else if (invSlot < this.slots.size()) {
                    if (!this.insertItem(originalStack, this.inventory.size(), this.inventory.size() + 27, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }
            // If the click is from the machine's inventory, move it to the player's inventory.
            else {
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

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerToInventory(PlayerInventory playerInventory)
    {
        int playerInvY = 84; // This is the standard Y offset for a double chest GUI
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, playerInvY + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory)
    {
        int hotbarY = 142; // This is the standard Y offset for a double chest GUI
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, hotbarY));
        }
    }
}
