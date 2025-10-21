package com.cudzdev.echoes_of_the_void.block;

import com.cudzdev.echoes_of_the_void.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class GlimmerwoodSaplingBlock extends SaplingBlock {

    public GlimmerwoodSaplingBlock(SaplingGenerator generator, Settings settings) {
        super(generator, settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        // Allows the sapling to be planted on your custom Glow-Moss block
        return floor.isOf(ModBlocks.GLOW_MOSS) || super.canPlantOnTop(floor, world, pos);
    }
}
