package com.cudzdev.echoes_of_the_void.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record ResonanceRecipeInput(ItemStack crystalInput, ItemStack redstoneInput) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return switch (slot) {
            case 0 -> this.crystalInput;
            case 1 -> this.redstoneInput;
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return this.crystalInput.isEmpty() && this.redstoneInput.isEmpty();
    }
}
