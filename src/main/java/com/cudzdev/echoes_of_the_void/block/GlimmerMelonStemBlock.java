package com.cudzdev.echoes_of_the_void.block;

import com.cudzdev.echoes_of_the_void.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.StemBlock;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;

public class GlimmerMelonStemBlock extends StemBlock {
    public GlimmerMelonStemBlock(RegistryKey<Block> gourdBlock, RegistryKey<Block> attachedStemBlock, RegistryKey<Item> pickBlockItem, Settings settings) {
        super(gourdBlock, attachedStemBlock, pickBlockItem, settings);
    }
}
