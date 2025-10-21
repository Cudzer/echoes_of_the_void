package com.cudzdev.echoes_of_the_void.gui.widgets;

import com.cudzdev.echoes_of_the_void.Main;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.util.Identifier;

public class GuiConfigTab extends GuiElement{
    public static final Identifier WIDGETS_TEXTURE = Identifier.of(Main.MOD_ID, "textures/gui/config_panel_widgets.png");

    private boolean isOpen = false;
    private final int panelWidth = 80;
    private final int tabWidth = 24;
    private final int tabHeight = 24;
    private final int textureWidth = 80; // The full width of the widget texture file
    private final int textureHeight = 166;

    public GuiConfigTab(HandledScreen<?> screen) {
        super(screen, 0, 0, 0, 0, false); // Position will be set later
    }

    public boolean isOpen() {
        return isOpen;
    }

    public int getPanelWidth() {
        return panelWidth;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // The tab button is now centered vertically
        int tabY = this.y + (this.height / 2) - (this.tabHeight / 2);
        int tabX = this.x - this.tabWidth;

        if (mouseX >= tabX && mouseX < tabX + tabWidth && mouseY >= tabY && mouseY < tabY + tabHeight) {
            this.isOpen = !this.isOpen;
            ((IHasConfigTab)screen).onConfigTabToggled();
            return true;
        }
        return false;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        RenderSystem.setShaderTexture(0, WIDGETS_TEXTURE);

        if (this.isOpen) {
            context.drawTexture(WIDGETS_TEXTURE, this.x - this.panelWidth, this.y, 0, 0, this.panelWidth, this.height, textureWidth, textureHeight);
        }

        int tabTextureX = 0;
        int tabTextureY = this.isOpen ? this.tabHeight : 0;
        int tabY = this.y + (this.height / 2) - (this.tabHeight / 2);

        // Draw the tab button at its new, centered Y position
        context.drawTexture(WIDGETS_TEXTURE, this.x - this.tabWidth, tabY,
                tabTextureX, tabTextureY, this.tabWidth, this.tabHeight, textureWidth, textureHeight);
    }
}
