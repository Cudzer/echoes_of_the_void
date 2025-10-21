package com.cudzdev.echoes_of_the_void.sound;

import com.cudzdev.echoes_of_the_void.Main;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final Identifier RESONANCE_TABLE_PROCESSING_ID = Identifier.of(Main.MOD_ID, "block.resonance_table.processing");
    public static final Identifier PORTAL_ACTIVATE_ID = Identifier.of(Main.MOD_ID, "block.portal_activate");
    public static SoundEvent RESONANCE_TABLE_PROCESSING_EVENT = SoundEvent.of(RESONANCE_TABLE_PROCESSING_ID);
    public static SoundEvent PORTAL_ACTIVATE_EVENT = SoundEvent.of(PORTAL_ACTIVATE_ID);

    public static void registerSounds() {
        Registry.register(Registries.SOUND_EVENT, RESONANCE_TABLE_PROCESSING_ID, RESONANCE_TABLE_PROCESSING_EVENT);
        Registry.register(Registries.SOUND_EVENT, PORTAL_ACTIVATE_ID, PORTAL_ACTIVATE_EVENT);
    }
}
