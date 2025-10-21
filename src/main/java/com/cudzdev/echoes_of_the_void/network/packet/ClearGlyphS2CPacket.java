package com.cudzdev.echoes_of_the_void.network.packet;

import com.cudzdev.echoes_of_the_void.Main;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public record ClearGlyphS2CPacket(BlockPos targetPos) implements CustomPayload {
    public static final CustomPayload.Id<ClearGlyphS2CPacket> ID = new CustomPayload.Id<>(Identifier.of(Main.MOD_ID, "clear_resonator"));

    public static final PacketCodec<RegistryByteBuf, ClearGlyphS2CPacket> CODEC = PacketCodec.tuple(
            BlockPos.PACKET_CODEC, ClearGlyphS2CPacket::targetPos,
            ClearGlyphS2CPacket::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}