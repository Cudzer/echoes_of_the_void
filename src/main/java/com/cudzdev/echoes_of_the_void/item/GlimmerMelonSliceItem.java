package com.cudzdev.echoes_of_the_void.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;

public class GlimmerMelonSliceItem extends Item {
    public static final FoodComponent FOOD_COMPONENT = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.3f)
            .build();

    public GlimmerMelonSliceItem(Settings settings) {
        super(settings.food(FOOD_COMPONENT));
    }
}
