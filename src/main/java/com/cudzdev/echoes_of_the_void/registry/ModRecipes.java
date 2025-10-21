package com.cudzdev.echoes_of_the_void.registry;

import com.cudzdev.echoes_of_the_void.Main;
import com.cudzdev.echoes_of_the_void.recipe.ResonanceRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static final RecipeSerializer<ResonanceRecipe> RESONANCE_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(Main.MOD_ID, "resonance"),
                    new ResonanceRecipe.Serializer()
    );

    public static final RecipeType<ResonanceRecipe> RESONANCE_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(Main.MOD_ID, "resonance"),
            new RecipeType<ResonanceRecipe>() {
                @Override
                public String toString() {
                    return "resonance";
                }
            }
    );

    public static void registerRecipes(){

    }
}
