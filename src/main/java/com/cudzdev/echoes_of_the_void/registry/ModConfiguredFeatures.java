package com.cudzdev.echoes_of_the_void.registry;

import com.cudzdev.echoes_of_the_void.Main;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLIMMERWOOD_KEY = registerKey("glimmerwood");
    public static final RegistryKey<ConfiguredFeature<?, ?>> STARWILLOW_KEY = registerKey("starwillow");

    public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_BLUE_CAVESHROOM_KEY = registerKey("huge_blue_caveshroom");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_GREEN_CAVESHROOM_KEY = registerKey("huge_green_caveshroom");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_PINK_CAVESHROOM_KEY = registerKey("huge_pink_caveshroom");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
    }

    private static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Main.MOD_ID, name));
    }

    public static void register() {
        Main.LOGGER.debug("Registering Configured Features for " + Main.MOD_ID);
    }
}
