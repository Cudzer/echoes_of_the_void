package com.cudzdev.echoes_of_the_void.block;

import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;

public class AttachedGlimmerMelonStemBlock extends AttachedStemBlock {
    public AttachedGlimmerMelonStemBlock(RegistryKey<Block> gourdBlock, RegistryKey<Block> attachedStemBlock, RegistryKey<Item> pickBlockItem, Settings settings) {
        super(gourdBlock, attachedStemBlock, pickBlockItem, settings);
    }
}
