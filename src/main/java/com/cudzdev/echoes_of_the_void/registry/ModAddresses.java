package com.cudzdev.echoes_of_the_void.registry;

import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class ModAddresses {
    public static final DefaultedList<ItemStack> VESPERA_ADDRESS = DefaultedList.ofSize(5, ItemStack.EMPTY);
    public static final DefaultedList<ItemStack> OVERWORLD_ADDRESS = DefaultedList.ofSize(5, ItemStack.EMPTY);

    static {
        VESPERA_ADDRESS.set(0, new ItemStack(ModItems.PRIME_GLYPH_I));
        VESPERA_ADDRESS.set(1, new ItemStack(ModItems.PRIME_GLYPH_S));
        VESPERA_ADDRESS.set(2, new ItemStack(ModItems.PRIME_GLYPH_T));
        VESPERA_ADDRESS.set(3, new ItemStack(ModItems.PRIME_GLYPH_I));
        VESPERA_ADDRESS.set(4, new ItemStack(ModItems.PRIME_GLYPH_XX));

        OVERWORLD_ADDRESS.set(0, new ItemStack(ModItems.PRIME_GLYPH_XX));
        OVERWORLD_ADDRESS.set(1, new ItemStack(ModItems.PRIME_GLYPH_S));
        OVERWORLD_ADDRESS.set(2, new ItemStack(ModItems.PRIME_GLYPH_I));
        OVERWORLD_ADDRESS.set(3, new ItemStack(ModItems.PRIME_GLYPH_T));
        OVERWORLD_ADDRESS.set(4, new ItemStack(ModItems.PRIME_GLYPH_XX));
    }
}
