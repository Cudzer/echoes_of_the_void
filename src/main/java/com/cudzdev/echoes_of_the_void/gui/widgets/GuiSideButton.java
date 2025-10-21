package com.cudzdev.echoes_of_the_void.gui.widgets;

import com.cudzdev.echoes_of_the_void.Main;
import com.cudzdev.echoes_of_the_void.block.entity.ResonanceTableBlockEntity;
import com.cudzdev.echoes_of_the_void.gui.ResonanceTableScreenHandler;
import com.cudzdev.echoes_of_the_void.network.packet.CycleSideConfigC2SPacket;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.List;

public class GuiSideButton extends GuiElement{
    public static final Identifier ICONS_TEXTURE = Identifier.of(Main.MOD_ID, "textures/gui/config_icons.png");
    private final Direction side;
    private final ResonanceTableScreenHandler handler;
    private final int textureWidth = 18; // Full width of the icon sprite sheet
    private final int textureHeight = 72;

    public GuiSideButton(HandledScreen<?> screen, int x, int y, Direction side) {
        super(screen, x, y, 18, 18, false); // Buttons are 18x18 pixels
        this.side = side;
        this.handler = (ResonanceTableScreenHandler) screen.getScreenHandler();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.isMouseOver(mouseX, mouseY)) {
            MinecraftClient.getInstance().player.playSound(SoundEvents.UI_BUTTON_CLICK.value(), 1.0F, 1.0F);
            System.out.println("Clicked on side button: " + this.side.getName());
            ClientPlayNetworking.send(new CycleSideConfigC2SPacket(handler.getBlockPos(), this.side));
            return true;
        }
        return false;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        RenderSystem.setShaderTexture(0, ICONS_TEXTURE);

        // Get the current config for this side from the property delegate
        int configOrdinal = this.handler.getPropertyDelegate().get(2 + this.side.ordinal());
        ResonanceTableBlockEntity.SideConfig currentConfig = ResonanceTableBlockEntity.SideConfig.fromOrdinal(configOrdinal);

        // Determine which icon to draw based on the current state
        int v = switch (currentConfig) {
            case NONE -> 0;
            case INPUT_CRYSTAL -> 18;
            case INPUT_CATALYST -> 36;
            case OUTPUT -> 54;
        };

        // Draw the correct 18x18 icon from the sprite sheet
        context.drawTexture(ICONS_TEXTURE, this.x, this.y, 0, v, this.width, this.height, textureWidth, textureHeight);
    }

    public void renderTooltip(DrawContext context, TextRenderer textRenderer, int mouseX, int mouseY) {
        if (this.isMouseOver(mouseX, mouseY)) {
            int configOrdinal = this.handler.getPropertyDelegate().get(2 + this.side.ordinal());
            ResonanceTableBlockEntity.SideConfig currentConfig = ResonanceTableBlockEntity.SideConfig.fromOrdinal(configOrdinal);

            Text sideText = Text.translatable("gui.echoes_of_the_void.side." + side.getName());
            Text configText = Text.translatable("gui.echoes_of_the_void.side_config." + currentConfig.name().toLowerCase());

            context.drawTooltip(textRenderer, List.of(sideText, configText), mouseX, mouseY);
        }
    }
}
