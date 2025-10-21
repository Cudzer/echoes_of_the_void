package com.cudzdev.echoes_of_the_void.network.packet;

import com.cudzdev.echoes_of_the_void.Main;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public record CycleSideConfigC2SPacket(BlockPos pos, Direction side) implements CustomPayload {
    public static final CustomPayload.Id<CycleSideConfigC2SPacket> ID = new CustomPayload.Id<>(Identifier.of(Main.MOD_ID, "cycle_side_config"));

    public static final PacketCodec<RegistryByteBuf, CycleSideConfigC2SPacket> CODEC = PacketCodec.tuple(
            BlockPos.PACKET_CODEC, CycleSideConfigC2SPacket::pos,
            Direction.PACKET_CODEC, CycleSideConfigC2SPacket::side,
            CycleSideConfigC2SPacket::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
