package com.cudzdev.echoes_of_the_void.client.render;

import com.cudzdev.echoes_of_the_void.block.RunicCasingBlock;
import com.cudzdev.echoes_of_the_void.block.entity.RunicCasingBlockEntity;
import com.cudzdev.echoes_of_the_void.util.ModTags;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;

public class RunicCasingRenderer implements BlockEntityRenderer<RunicCasingBlockEntity> {

    public RunicCasingRenderer(BlockEntityRendererFactory.Context context) { }

    @Override
    public void render(RunicCasingBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        ItemStack glyphStack = entity.getDisplayedGlyph();
        if (glyphStack.isEmpty()) return;

        Identifier texture = GlyphTextureManager.getTexture(glyphStack.getItem());
        if (texture == null){
            return;
        }

        matrices.push();

        Direction facing = entity.getCachedState().get(RunicCasingBlock.FACING);
        float rotationDegrees = -facing.asRotation();

        matrices.translate(0.5, 0.5, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotationDegrees));
        matrices.translate(0, 0, 0.55);

        if (glyphStack.isIn(ModTags.Items.PRIME_GLYPHS)) {
            matrices.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrices.scale(1.0f, 1.0f, 1.0f);
        }


        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEyes(texture));
        Matrix4f matrix = matrices.peek().getPositionMatrix();

        float normalX = 0;
        float normalY = 1;
        float normalZ = 0;

        vertexConsumer.vertex(matrix, -0.5f, -0.5f, 0).color(255, 255, 255, 255).texture(0, 1).overlay(overlay).light(15728880).normal(normalX, normalY, normalZ);
        vertexConsumer.vertex(matrix, 0.5f, -0.5f, 0).color(255, 255, 255, 255).texture(1, 1).overlay(overlay).light(15728880).normal(normalX, normalY, normalZ);
        vertexConsumer.vertex(matrix, 0.5f, 0.5f, 0).color(255, 255, 255, 255).texture(1, 0).overlay(overlay).light(15728880).normal(normalX, normalY, normalZ);
        vertexConsumer.vertex(matrix, -0.5f, 0.5f, 0).color(255, 255, 255, 255).texture(0, 0).overlay(overlay).light(15728880).normal(normalX, normalY, normalZ);

        matrices.pop();
    }
}
