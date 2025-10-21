package com.cudzdev.echoes_of_the_void.registry;

import com.cudzdev.echoes_of_the_void.Main;
import com.cudzdev.echoes_of_the_void.gui.ResonanceTableScreenHandler;
import com.cudzdev.echoes_of_the_void.gui.StellarDialScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<StellarDialScreenHandler> STELLAR_DIAL_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER,
            Identifier.of(Main.MOD_ID, "stellar_dial"),
            new ExtendedScreenHandlerType<>(StellarDialScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<ResonanceTableScreenHandler> RESONANCE_TABLE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER,
                    Identifier.of(Main.MOD_ID, "resonance_table"),
                    new ExtendedScreenHandlerType<>(ResonanceTableScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerAllScreenHandlers() {
    }
}
