package com.cudzdev.echoes_of_the_void.client.render;

import com.cudzdev.echoes_of_the_void.Main;
import com.cudzdev.echoes_of_the_void.registry.ModItems;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class GlyphTextureManager {
    private static final Map<Item, Identifier> TEXTURE_MAP = new HashMap<>();

    static {
        TEXTURE_MAP.put(ModItems.STELLAR_GLYPH_R, Identifier.of(Main.MOD_ID, "textures/effect/symbol-r.png"));
        TEXTURE_MAP.put(ModItems.STELLAR_GLYPH_8, Identifier.of(Main.MOD_ID, "textures/effect/symbol-8.png"));
        TEXTURE_MAP.put(ModItems.STELLAR_GLYPH_O, Identifier.of(Main.MOD_ID, "textures/effect/symbol-o.png"));
        TEXTURE_MAP.put(ModItems.STELLAR_GLYPH_SLASH, Identifier.of(Main.MOD_ID, "textures/effect/symbol-slash.png"));
        TEXTURE_MAP.put(ModItems.STELLAR_GLYPH_W, Identifier.of(Main.MOD_ID, "textures/effect/symbol-w.png"));
        TEXTURE_MAP.put(ModItems.PRIME_GLYPH_BADGE, Identifier.of(Main.MOD_ID, "textures/effect/symbol-badge.png"));
        TEXTURE_MAP.put(ModItems.PRIME_GLYPH_I, Identifier.of(Main.MOD_ID, "textures/effect/symbol-i.png"));
        TEXTURE_MAP.put(ModItems.PRIME_GLYPH_T, Identifier.of(Main.MOD_ID, "textures/effect/symbol-t.png"));
        TEXTURE_MAP.put(ModItems.PRIME_GLYPH_S, Identifier.of(Main.MOD_ID, "textures/effect/symbol-s.png"));
        TEXTURE_MAP.put(ModItems.PRIME_GLYPH_XX, Identifier.of(Main.MOD_ID, "textures/effect/symbol-xx.png"));
    }

    public static Identifier getTexture(Item item) {
        return TEXTURE_MAP.get(item);
    }
}
