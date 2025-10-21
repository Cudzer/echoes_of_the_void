package com.cudzdev.echoes_of_the_void.registry;

import com.cudzdev.echoes_of_the_void.Main;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator GLIMMERWOOD = new SaplingGenerator(Main.MOD_ID + ":glimmerwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.GLIMMERWOOD_KEY), Optional.empty());
    public static final SaplingGenerator STARWILLOW = new SaplingGenerator(Main.MOD_ID + ":starwillow",
            Optional.empty(), Optional.of(ModConfiguredFeatures.STARWILLOW_KEY), Optional.empty());
}
