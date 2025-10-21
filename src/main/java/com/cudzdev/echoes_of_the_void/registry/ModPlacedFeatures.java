package com.cudzdev.echoes_of_the_void.registry;

import com.cudzdev.echoes_of_the_void.Main;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> GLIMMERWOOD_PLACED_KEY = registerKey("glimmerwood_placed");
    public static final RegistryKey<PlacedFeature> STARWILLOW_PLACED_KEY = registerKey("starwillow_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
    }

    private static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Main.MOD_ID, name));
    }

    public static void register() {
        Main.LOGGER.debug("Registering Placed Features for " + Main.MOD_ID);
    }
}
