package com.cudzdev.echoes_of_the_void.compat.rei;

import com.cudzdev.echoes_of_the_void.registry.ModBlocks;
import com.cudzdev.echoes_of_the_void.gui.ResonanceTableScreen;
import com.cudzdev.echoes_of_the_void.registry.ModRecipes;
import com.cudzdev.echoes_of_the_void.recipe.ResonanceRecipe;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;

public class EchoesOfTheVoidModREIClient implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new ResonanceTableCategory());

        registry.addWorkstations(ResonanceTableCategory.RESONANCE_TABLE, EntryStacks.of(ModBlocks.RESONANCE_TABLE));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(ResonanceRecipe.class, ModRecipes.RESONANCE_TYPE, ResonanceTableDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen ->
            new Rectangle(((screen.width - 172) / 2) + 78,
                    ((screen.height - 166) / 2) + 30, 20, 25),
                ResonanceTableScreen.class,
                ResonanceTableCategory.RESONANCE_TABLE);
    }
}
