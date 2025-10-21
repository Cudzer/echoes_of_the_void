package com.cudzdev.echoes_of_the_void.datagen;

import com.cudzdev.echoes_of_the_void.registry.ModItems;
import com.cudzdev.echoes_of_the_void.util.ModTags;
import dev.architectury.platform.Mod;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Items.RESONANT_CRYSTALS)
                .add(Items.AMETHYST_SHARD)
                .add(ModItems.CELENITE_CRYSTAL);

        getOrCreateTagBuilder(ModTags.Items.PRIME_GLYPHS)
                .add(ModItems.PRIME_GLYPH_BADGE);
        getOrCreateTagBuilder(ModTags.Items.PRIME_GLYPHS)
                .add(ModItems.PRIME_GLYPH_I);
        getOrCreateTagBuilder(ModTags.Items.PRIME_GLYPHS)
                .add(ModItems.PRIME_GLYPH_T);
        getOrCreateTagBuilder(ModTags.Items.PRIME_GLYPHS)
                .add(ModItems.PRIME_GLYPH_S);
        getOrCreateTagBuilder(ModTags.Items.PRIME_GLYPHS)
                .add(ModItems.PRIME_GLYPH_XX);

        getOrCreateTagBuilder(ModTags.Items.ALL_GLYPHS)
                .add(ModItems.PRIME_GLYPH_I)
                .add(ModItems.PRIME_GLYPH_BADGE)
                .add(ModItems.PRIME_GLYPH_S)
                .add(ModItems.PRIME_GLYPH_T)
                .add(ModItems.PRIME_GLYPH_XX)
                .add(ModItems.STELLAR_GLYPH_8)
                .add(ModItems.STELLAR_GLYPH_O)
                .add(ModItems.STELLAR_GLYPH_R)
                .add(ModItems.STELLAR_GLYPH_SLASH)
                .add(ModItems.STELLAR_GLYPH_W);

        getOrCreateTagBuilder(ModTags.Items.C_GEMS)
                .add(ModItems.CELENITE_CRYSTAL)
                .add(ModItems.AETHERIUM_CRYSTAL);
        getOrCreateTagBuilder(ModTags.Items.C_SEEDS)
                .add(ModItems.GLIMMER_MELON_SEEDS);
        getOrCreateTagBuilder(ModTags.Items.C_FOODS_FRUIT)
                .add(ModItems.GLIMMER_MELON_SLICE);
    }
}
