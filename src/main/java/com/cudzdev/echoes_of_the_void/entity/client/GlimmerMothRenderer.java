package com.cudzdev.echoes_of_the_void.entity.client;

import com.cudzdev.echoes_of_the_void.Main;
import com.cudzdev.echoes_of_the_void.entity.GlimmerMothEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GlimmerMothRenderer extends GeoEntityRenderer<GlimmerMothEntity> {
    public GlimmerMothRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new GlimmerMothModel());
    }

    // This method is now used to add our glowing layer ON TOP of the base texture.
    @Override
    public void render(GlimmerMothEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        // This renders the base model and texture first.
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

        // Now, we get our special glow layer and render it on top.
        // This ensures the base texture is always visible.
        RenderLayer glowLayer = RenderLayer.getEyes(Identifier.of(Main.MOD_ID, "textures/entity/glimmer_moth_glow.png"));
        VertexConsumer glowBuffer = bufferSource.getBuffer(glowLayer);

        // Render the model again, but this time with the glow texture and full brightness
        super.render(entity, entityYaw, partialTick, poseStack, (layer) -> glowBuffer, 15728880);
    }

//    @Override
//    public RenderLayer getRenderType(GlimmerMothEntity animatable, Identifier texture, VertexConsumerProvider bufferSource, float partialTick) {
//        return RenderLayer.getEyes(Identifier.of(Main.MOD_ID, "textures/entity/glimmer_moth_glow.png"));
//    }
}
