package com.cudzdev.echoes_of_the_void.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class ResonatorItem extends Item {
    private final String tooltipKey;

    public ResonatorItem(Settings settings, String tooltipKey) {
        super(settings);
        this.tooltipKey = tooltipKey;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.echoes_of_the_void.glyph." + this.tooltipKey).formatted(Formatting.GRAY));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
