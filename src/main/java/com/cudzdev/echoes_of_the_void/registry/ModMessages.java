package com.cudzdev.echoes_of_the_void.registry;

import com.cudzdev.echoes_of_the_void.Main;
import com.cudzdev.echoes_of_the_void.block.entity.ResonanceTableBlockEntity;
import com.cudzdev.echoes_of_the_void.block.entity.RunicCasingBlockEntity;
import com.cudzdev.echoes_of_the_void.block.entity.StellarDialBlockEntity;
import com.cudzdev.echoes_of_the_void.network.packet.ClearGlyphS2CPacket;
import com.cudzdev.echoes_of_the_void.network.packet.CycleSideConfigC2SPacket;
import com.cudzdev.echoes_of_the_void.network.packet.DialGateC2SPacket;
import com.cudzdev.echoes_of_the_void.network.packet.SetGlyphS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier DIAL_GATE_ID = Identifier.of(Main.MOD_ID, "dial_gate");

    // 2. This method is for registering packets sent FROM the Client TO the Server (C2S)
    public static void registerC2SPackets() {
        // Register the payload type
        PayloadTypeRegistry.playC2S().register(DialGateC2SPacket.ID, DialGateC2SPacket.CODEC);

        // Register the server-side receiver for the packet
        ServerPlayNetworking.registerGlobalReceiver(DialGateC2SPacket.ID, (payload, context) -> {
            // This code now runs on the SERVER when the packet is received.
            // The payload is the DialGateC2SPacket object we sent.
            context.server().execute(() -> {
                if(context.player().getWorld().getBlockEntity(payload.pos()) instanceof StellarDialBlockEntity blockEntity) {
                    blockEntity.beginDialingSequence();
                }
            });
        });

        PayloadTypeRegistry.playC2S().register(CycleSideConfigC2SPacket.ID, CycleSideConfigC2SPacket.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(CycleSideConfigC2SPacket.ID, (payload, context) -> {
            context.server().execute(() -> {
                // This logic handles a player clicking a side config button in the Resonance Table GUI
                if(context.player().getWorld().getBlockEntity(payload.pos()) instanceof ResonanceTableBlockEntity blockEntity) {
                    blockEntity.cycleSideConfig(payload.side());
                }
            });
        });
    }

    public static void registerS2CPackets() {
        // Register our new ClearGlyph packet
        PayloadTypeRegistry.playS2C().register(ClearGlyphS2CPacket.ID, ClearGlyphS2CPacket.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(ClearGlyphS2CPacket.ID, (payload, context) -> {
            context.client().execute(() -> {
                if(context.client().world.getBlockEntity(payload.targetPos()) instanceof RunicCasingBlockEntity blockEntity) {
                    blockEntity.clearGlyph();
                }
            });
        });

        PayloadTypeRegistry.playS2C().register(SetGlyphS2CPacket.ID, SetGlyphS2CPacket.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(SetGlyphS2CPacket.ID, (payload, context) -> {
            context.client().execute(() -> {
                if(context.client().world.getBlockEntity(payload.targetPos()) instanceof RunicCasingBlockEntity blockEntity) {
                    blockEntity.setDisplayedGlyphClient(payload.glyph());
                }
            });
        });
    }
}
