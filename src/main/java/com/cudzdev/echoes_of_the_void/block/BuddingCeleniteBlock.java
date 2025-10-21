package com.cudzdev.echoes_of_the_void.block;

import com.cudzdev.echoes_of_the_void.registry.ModBlocks;
import net.minecraft.block.AmethystBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

public class BuddingCeleniteBlock extends AmethystBlock {
    public static final int GROW_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();

    public BuddingCeleniteBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(GROW_CHANCE) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockPos = pos.offset(direction);
            BlockState blockState = world.getBlockState(blockPos);
            Block block = null;
            if (blockState.isAir()) {
                block = ModBlocks.CELENITE_CLUSTER_BLOCK; // The block that will grow
            }

            if (block != null) {
                BlockState blockState2 = block.getDefaultState().with(CeleniteClusterBlock.FACING, direction);
                world.setBlockState(blockPos, blockState2);
            }
        }
    }
}
