package com.cudzdev.echoes_of_the_void.gui;

import com.cudzdev.echoes_of_the_void.Main;
import com.cudzdev.echoes_of_the_void.network.packet.DialGateC2SPacket;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class StellarDialScreen extends HandledScreen<StellarDialScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(Main.MOD_ID, "textures/gui/containers/stellar_dial_pedestal_gui.png");

    public StellarDialScreen(StellarDialScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundHeight = 222;
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }

    @Override
    protected void init() {
        super.init();
        this.titleY = 6;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Engage"), (button) -> {
            ClientPlayNetworking.send(new DialGateC2SPacket(handler.blockPos));
            this.close();
        }).dimensions(this.x + 60, this.y + 112, 56, 20).build());
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        if (handler.blockEntity.hasAddress()) {
            //drawAddressDisplay(context, x, y);
        }
    }

    private void drawAddressDisplay(DrawContext context, int x, int y) {
        // Define the starting position for the address display (e.g., centered at the top)
        int addressDisplayY = y + 65;
        int addressDisplayX = x + 43;

        for (int i = 0; i < 5; i++) {
            // Get the glyph for this part of the address
            ItemStack glyphStack = handler.getSlot(6 + i).getStack();

            // Calculate the position for this specific glyph
            int slotX = addressDisplayX + (i * 18);

            // Draw the item. The +1 offsets are to center it nicely in a standard slot graphic.
            context.drawItem(glyphStack, slotX + 1, addressDisplayY + 1);
            // This draws the item's count (though glyphs likely won't stack)
            context.drawItemInSlot(this.textRenderer, glyphStack, slotX + 1, addressDisplayY + 1);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        // Draw the status text (adjust Y as needed)
        Text statusText = Text.literal("Status: IDLE");
        int textX = this.x + (this.backgroundWidth / 2) - (this.textRenderer.getWidth(statusText) / 2);
        context.drawText(this.textRenderer, statusText, textX - 50, this.y + 100, 0x404040, false);

        Text addressLabel = Text.literal("Address");
        int labelX = this.x + (this.backgroundWidth / 2) - (this.textRenderer.getWidth(addressLabel) / 2);
        context.drawText(this.textRenderer, addressLabel, labelX, this.y + 55, 0x404040, false);

        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
