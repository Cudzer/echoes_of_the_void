package com.cudzdev.echoes_of_the_void.network.packet;

import com.cudzdev.echoes_of_the_void.Main;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public record SetGlyphS2CPacket(BlockPos targetPos, ItemStack glyph) implements CustomPayload {
    public static final CustomPayload.Id<SetGlyphS2CPacket> ID = new CustomPayload.Id<>(Identifier.of(Main.MOD_ID, "set_resonator"));

    public static final PacketCodec<RegistryByteBuf, SetGlyphS2CPacket> CODEC = PacketCodec.tuple(
            BlockPos.PACKET_CODEC, SetGlyphS2CPacket::targetPos,
            ItemStack.PACKET_CODEC, SetGlyphS2CPacket::glyph,
            SetGlyphS2CPacket::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
