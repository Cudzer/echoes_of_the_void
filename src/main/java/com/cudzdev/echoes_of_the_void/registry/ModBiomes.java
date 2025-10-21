package com.cudzdev.echoes_of_the_void.registry;

import com.cudzdev.echoes_of_the_void.Main;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class ModBiomes {
    public static final RegistryKey<Biome> VESPERA_GLIMMERWOOD_FOREST = registerKey("vespera_glimmerwood_forest");
    public static final RegistryKey<Biome> VESPERA_LUCENT_MEADOWS = registerKey("vespera_lucent_meadows");
    public static final RegistryKey<Biome> VESPERA_AETHERIUM_SPIRES = registerKey("vespera_aetherium_spires");
    public static final RegistryKey<Biome> VESPERA_GLOWCAP_FEN = registerKey("vespera_glowcap_fen");
    public static final RegistryKey<Biome> RESONANT_CHASM = registerKey("resonant_chasm");
    public static final RegistryKey<Biome> AETHERIUM_CAVERNS = registerKey("aetherium_caverns");

    private static RegistryKey<Biome> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Main.MOD_ID, name));
    }

    public static void register() {
        Main.LOGGER.debug("Registering Biomes for " + Main.MOD_ID);
    }
}
