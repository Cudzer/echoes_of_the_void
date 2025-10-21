package com.cudzdev.echoes_of_the_void.util;

import com.cudzdev.echoes_of_the_void.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class EchowayStructureUtil {

    private static final List<BlockPos> RUNIC_CASING_ORDER = List.of(
            new BlockPos(-2, 1, 8), // Bottom-left
            new BlockPos(2, 1, 8),  // Bottom-right
            new BlockPos(-2, 3, 8), // Top-left
            new BlockPos(2, 3, 8),  // Top-right
            new BlockPos(0, 4, 8)   // Top-center (final chevron)
            // Note: I've reordered the top row from your file to make the center one last, which is more dramatic.
    );

    private static final Map<BlockPos, Predicate<BlockState>> ECHOWAY_PATTERN = Map.ofEntries(
            Map.entry(new BlockPos(-1, 0, 8), state -> state.isOf(ModBlocks.ARCHITECT_STONE_BRICK)),
            Map.entry(new BlockPos(0, 0, 8), state -> state.isOf(ModBlocks.ARCHITECT_STONE_BRICK)),
            Map.entry(new BlockPos(1, 0, 8), state -> state.isOf(ModBlocks.ARCHITECT_STONE_BRICK)),

            // --- Middle Section (Pillars, Z=-1) ---
            Map.entry(new BlockPos(-2, 1, 8), state -> state.isOf(ModBlocks.RUNIC_CASING)),
            Map.entry(new BlockPos(2, 1, 8), state -> state.isOf(ModBlocks.RUNIC_CASING)),

            Map.entry(new BlockPos(-2, 2, 8), state -> state.isOf(ModBlocks.ARCHITECT_STONE_BRICK)),
            Map.entry(new BlockPos(2, 2, 8), state -> state.isOf(ModBlocks.ARCHITECT_STONE_BRICK)),

            Map.entry(new BlockPos(-2, 3, 8), state -> state.isOf(ModBlocks.RUNIC_CASING)),
            Map.entry(new BlockPos(2, 3, 8), state -> state.isOf(ModBlocks.RUNIC_CASING)),

            // --- Top Row (Y=4, Z=-1) ---
            Map.entry(new BlockPos(-1, 4, 8), state -> state.isOf(ModBlocks.ARCHITECT_STONE_BRICK)),
            Map.entry(new BlockPos(0, 4, 8), state -> state.isOf(ModBlocks.RUNIC_CASING)),
            Map.entry(new BlockPos(1, 4, 8), state -> state.isOf(ModBlocks.ARCHITECT_STONE_BRICK))
    );

    public static Map<BlockPos, Predicate<BlockState>> getEchowayPattern() {
        return ECHOWAY_PATTERN;
    }

    public static List<BlockPos> getRunicCasingOrder() {
        return RUNIC_CASING_ORDER;
    }
}
