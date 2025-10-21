package com.cudzdev.echoes_of_the_void.compat.rei;

import com.cudzdev.echoes_of_the_void.recipe.ResonanceRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.recipe.RecipeEntry;

import java.util.List;

public class ResonanceTableDisplay extends BasicDisplay {

    public ResonanceTableDisplay(RecipeEntry<ResonanceRecipe> recipe) {
        super(EntryIngredients.ofIngredients(recipe.value().getIngredients()),
              List.of(EntryIngredient.of(EntryStacks.of(recipe.value().getResult(null)))));
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return ResonanceTableCategory.RESONANCE_TABLE;
    }
}
