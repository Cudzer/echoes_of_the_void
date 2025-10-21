package com.cudzdev.echoes_of_the_void.registry;

import com.cudzdev.echoes_of_the_void.Main;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;

public class ModDimensions {
    public static final RegistryKey<World> VESPERA_KEY = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of(Main.MOD_ID, "vespera"));

    public static final RegistryKey<DimensionType> VESPERA_TYPE_KEY = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            Identifier.of(Main.MOD_ID, "vespera"));

    public static final RegistryKey<DimensionOptions> VESPERA_OPTIONS_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            Identifier.of(Main.MOD_ID, "vespera"));

    public static void register() {
        Main.LOGGER.debug("Registering ModDimensions for " + Main.MOD_ID);
    }
}
