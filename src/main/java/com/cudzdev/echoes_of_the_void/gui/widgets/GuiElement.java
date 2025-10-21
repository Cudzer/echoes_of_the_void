package com.cudzdev.echoes_of_the_void.gui.widgets;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;

public class GuiElement implements Drawable, Element, Selectable {
    protected final HandledScreen<?> screen;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean isFocused;

    public GuiElement(HandledScreen<?> screen, int x, int y, int width, int height, boolean isFocused) {
        this.screen = screen;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isFocused = isFocused;
    }

    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // To be implemented by subclasses
    }

    @Override
    public void setFocused(boolean focused) {
        this.isFocused = focused;
    }

    @Override
    public boolean isFocused() {
        return isFocused;
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= this.x && mouseX < (this.x + this.width) && mouseY >= this.y && mouseY < (this.y + this.height);
    }

    @Override
    public SelectionType getType() {
        return SelectionType.NONE;
    }

    @Override
    public void appendNarrations(NarrationMessageBuilder builder) {

    }
}
