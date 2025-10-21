package com.cudzdev.echoes_of_the_void.util;

import com.cudzdev.echoes_of_the_void.registry.ModBlocks;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

public class ModRegistries {
    public static void registerModStuffs() {
        registerStrippables();
    }

    private static void registerStrippables() {
        StrippableBlockRegistry.register(ModBlocks.GLIMMERWOOD_LOG, ModBlocks.STRIPPED_GLIMMERWOOD_LOG);
        StrippableBlockRegistry.register(ModBlocks.GLIMMERWOOD_WOOD, ModBlocks.STRIPPED_GLIMMERWOOD_WOOD);
        StrippableBlockRegistry.register(ModBlocks.STARWILLOW_LOG, ModBlocks.STRIPPED_STARWILLOW_LOG);
        StrippableBlockRegistry.register(ModBlocks.STARWILLOW_WOOD, ModBlocks.STRIPPED_STARWILLOW_WOOD);
    }
}
