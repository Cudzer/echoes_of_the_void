package com.cudzdev.echoes_of_the_void.network.packet;

import com.cudzdev.echoes_of_the_void.Main;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public record DialGateC2SPacket(BlockPos pos) implements CustomPayload {
    public static final CustomPayload.Id<DialGateC2SPacket> ID = new CustomPayload.Id<>(Identifier.of(Main.MOD_ID, "dial_gate"));

    public static final PacketCodec<RegistryByteBuf, DialGateC2SPacket> CODEC = PacketCodec.tuple(
            BlockPos.PACKET_CODEC, DialGateC2SPacket::pos,
            DialGateC2SPacket::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}