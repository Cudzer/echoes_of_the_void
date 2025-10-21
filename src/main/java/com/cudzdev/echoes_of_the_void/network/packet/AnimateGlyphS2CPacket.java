package com.cudzdev.echoes_of_the_void.network.packet;

import com.cudzdev.echoes_of_the_void.Main;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public record AnimateGlyphS2CPacket(BlockPos targetPos, BlockPos relativeStartPos, ItemStack glyph) implements CustomPayload {
    public static final CustomPayload.Id<AnimateGlyphS2CPacket> ID = new CustomPayload.Id<>(Identifier.of(Main.MOD_ID, "animate_glyph"));

    public static final PacketCodec<RegistryByteBuf, AnimateGlyphS2CPacket> CODEC = PacketCodec.tuple(
            BlockPos.PACKET_CODEC, AnimateGlyphS2CPacket::targetPos,
            BlockPos.PACKET_CODEC, AnimateGlyphS2CPacket::relativeStartPos,
            ItemStack.PACKET_CODEC, AnimateGlyphS2CPacket::glyph,
            AnimateGlyphS2CPacket::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
