package com.cudzdev.echoes_of_the_void.compat.rei;

import com.cudzdev.echoes_of_the_void.Main;
import com.cudzdev.echoes_of_the_void.registry.ModBlocks;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class ResonanceTableCategory implements DisplayCategory<BasicDisplay> {

    public static final Identifier TEXTURE = Identifier.of(Main.MOD_ID, "textures/gui/resonance_table/resonance_table_gui.png");
    public static final CategoryIdentifier<ResonanceTableDisplay> RESONANCE_TABLE = CategoryIdentifier.of(Main.MOD_ID, "resonance_table");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return RESONANCE_TABLE;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("block.echoes_of_the_void.resonance_table");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.RESONANCE_TABLE.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 175, 82)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 56, startPoint.y + 17))
                .entries(display.getInputEntries().get(0)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 56, startPoint.y + 53))
                .entries(display.getInputEntries().get(1)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 116, startPoint.y + 35))
                .entries(display.getOutputEntries().get(0)).markOutput());

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}
