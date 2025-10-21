package com.cudzdev.echoes_of_the_void.gui;

import com.cudzdev.echoes_of_the_void.Main;
import com.cudzdev.echoes_of_the_void.gui.widgets.GuiSideButton;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

public class ResonanceTableScreen extends HandledScreen<ResonanceTableScreenHandler> {
    public static final Identifier GUI_TEXTURE = Identifier.of(Main.MOD_ID, "textures/gui/resonance_table/resonance_table_gui.png");
    public static final Identifier ARROW_TEXTURE = Identifier.of(Main.MOD_ID, "textures/gui/arrow_progress.png");
    public static final Identifier CONFIG_WIDGETS_TEXTURE = Identifier.of("echoes_of_the_void", "textures/gui/config_panel_widgets.png");

    private boolean isConfigTabOpen = false;
    private final int configPanelWidth = 64;
    private final int mainPanelWidth = 176;
    private final int mainPanelHeight = 166;
    private final int configPanelHeight = 97;
    private final int tabWidth = 24;
    private final int tabHeight = 24;

    private final List<GuiSideButton> sideButtons = new ArrayList<>();

    public ResonanceTableScreen(ResonanceTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = mainPanelWidth;
        this.backgroundHeight = mainPanelHeight;
    }

    @Override
    protected void init() {
        this.x = (this.width - this.backgroundWidth) / 2;
        this.y = (this.height - this.backgroundHeight) / 2;

        super.init();
        updateSideButtons();
    }

    private void updateSideButtons() {
        sideButtons.forEach(this::remove);
        sideButtons.clear();

        if (this.isConfigTabOpen) {
            int panelX = this.x - this.configPanelWidth;
            int panelY = this.y + (this.mainPanelHeight - this.configPanelHeight) / 2 + 10;

            sideButtons.add(this.addDrawableChild(new GuiSideButton(this, panelX + 8, panelY + 8, Direction.UP)));
            sideButtons.add(this.addDrawableChild(new GuiSideButton(this, panelX + 38, panelY + 8, Direction.DOWN)));
            sideButtons.add(this.addDrawableChild(new GuiSideButton(this, panelX + 8, panelY + 39, Direction.NORTH)));
            sideButtons.add(this.addDrawableChild(new GuiSideButton(this, panelX + 38, panelY + 39, Direction.SOUTH)));
            sideButtons.add(this.addDrawableChild(new GuiSideButton(this, panelX + 8, panelY + 70, Direction.EAST)));
            sideButtons.add(this.addDrawableChild(new GuiSideButton(this, panelX + 38, panelY + 70, Direction.WEST)));
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // The tab button is now on the LEFT side to avoid conflicts with EMI/REI
        int tabX = this.x - this.tabWidth;
        int tabY = this.y + 20; // Lowered Y position

        if (mouseX >= tabX && mouseX < tabX + tabWidth && mouseY >= tabY && mouseY < tabY + tabHeight) {
            this.isConfigTabOpen = !this.isConfigTabOpen;
            // The main GUI no longer moves. We just update the buttons.
            updateSideButtons();
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        // Draw the main GUI panel at its static position
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        context.drawTexture(GUI_TEXTURE, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight, 256, 256);

        // Switch to the widget texture to draw the config panel elements
        RenderSystem.setShaderTexture(0, CONFIG_WIDGETS_TEXTURE);

        int textureWidth = 80;
        int textureHeight = 166;

        // Draw the config panel background if open
        if (this.isConfigTabOpen) {
            // Calculate the vertically centered Y position for the panel
            int panelY = this.y + (this.mainPanelHeight - this.configPanelHeight) / 2 + 10;

            // Draw the 64x97 background, sampling from the correct texture region (0, 51)
            context.drawTexture(CONFIG_WIDGETS_TEXTURE,
                    this.x - this.configPanelWidth, panelY,   // Screen position
                    0, 51,                                    // Texture position (U, V)
                    this.configPanelWidth, this.configPanelHeight, // On-screen size
                    textureWidth, textureHeight);             // Full texture file size
        }

        // Draw the tab button, now on the left and at a lower Y position
        int tabTextureX = 0;
        int tabTextureY = this.isConfigTabOpen ? 25 : 0;
        context.drawTexture(CONFIG_WIDGETS_TEXTURE, this.x - this.tabWidth, this.y + 20,
                tabTextureX, tabTextureY, this.tabWidth, this.tabHeight, 80, 166);

        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y)
    {
        if(handler.isCrafting())
        {
            context.drawTexture(ARROW_TEXTURE, x + 79, y + 35, 0, 0,
                    handler.getScaledArrowProgress(), 16, 24, 16);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);

        for (GuiSideButton btn : sideButtons) {
            btn.renderTooltip(context, this.textRenderer, mouseX, mouseY);
        }
    }
}
