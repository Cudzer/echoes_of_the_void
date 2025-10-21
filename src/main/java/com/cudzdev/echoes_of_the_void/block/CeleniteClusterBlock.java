package com.cudzdev.echoes_of_the_void.block;

import net.minecraft.block.AmethystClusterBlock;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class CeleniteClusterBlock extends AmethystClusterBlock {
    public CeleniteClusterBlock(Settings settings) {
        super(7, 3, settings);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        WorldView worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        Direction[] placementDirections = ctx.getPlacementDirections();

        for (Direction direction : placementDirections) {
            Direction opposite = direction.getOpposite();
            if (worldView.getBlockState(blockPos.offset(opposite)).isSideSolidFullSquare(worldView, blockPos.offset(opposite), direction)) {
                return this.getDefaultState()
                        .with(FACING, direction)
                        .with(WATERLOGGED, worldView.getFluidState(blockPos).getFluid() == Fluids.WATER);
            }
        }

        // Fallback if placed in mid-air
        return this.getDefaultState()
                .with(FACING, Direction.UP)
                .with(WATERLOGGED, worldView.getFluidState(blockPos).getFluid() == Fluids.WATER);
    }
}
