package com.cudzdev.echoes_of_the_void;

import com.cudzdev.echoes_of_the_void.registry.ModBlocks;
import com.cudzdev.echoes_of_the_void.registry.ModBlockEntities;
import com.cudzdev.echoes_of_the_void.client.render.RunicCasingRenderer;
import com.cudzdev.echoes_of_the_void.registry.ModEntities;
import com.cudzdev.echoes_of_the_void.entity.client.GlimmerMothRenderer;
import com.cudzdev.echoes_of_the_void.registry.ModScreenHandlers;
import com.cudzdev.echoes_of_the_void.gui.ResonanceTableScreen;
import com.cudzdev.echoes_of_the_void.gui.StellarDialScreen;
import com.cudzdev.echoes_of_the_void.registry.ModMessages;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class MainClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.GLIMMER_MOTH, GlimmerMothRenderer::new);
        HandledScreens.register(ModScreenHandlers.STELLAR_DIAL_SCREEN_HANDLER, StellarDialScreen::new);
        HandledScreens.register(ModScreenHandlers.RESONANCE_TABLE_SCREEN_HANDLER, ResonanceTableScreen::new);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STARGAZER_FLOWER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRYSTAL_SPROUT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLUE_CAVESHROOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GREEN_CAVESHROOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PINK_CAVESHROOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CELENITE_CLUSTER_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.AETHERIUM_ROOTS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLIMMERWOOD_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STARWILLOW_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ATTACHED_GLIMMER_MELON_STEM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLIMMER_MELON_STEM, RenderLayer.getCutout());

        BlockEntityRendererFactories.register(ModBlockEntities.RUNIC_CASING_BLOCK_ENTITY_TYPE, RunicCasingRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ECHOWAY_PORTAL, RenderLayer.getTranslucent());

        ModMessages.registerS2CPackets();
    }
}
