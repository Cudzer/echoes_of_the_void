package com.cudzdev.echoes_of_the_void.datagen;

import com.cudzdev.echoes_of_the_void.registry.ModBlocks;
import com.cudzdev.echoes_of_the_void.registry.ModItems;
import com.cudzdev.echoes_of_the_void.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        registerShapedRecipes(exporter);
        registerShapelessRecipes(exporter);
        registerFurnaceRecipes(exporter);
    }

    private void registerFurnaceRecipes(RecipeExporter exporter) {
        offerSmelting(exporter,
                List.of(ModBlocks.VESPERAN_COBBLESTONE),
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.VESPERAN_STONE,
                0.1f,
                200,
                "vesperan_stone"
        );
    }

    private void registerShapedRecipes(RecipeExporter exporter)
    {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ARCHITECT_STONE, 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .input('A', Items.STONE)
                .input('B', ModItems.INFUSED_AMETHYST_CRYSTAL)
                .criterion(hasItem(ModItems.INFUSED_AMETHYST_CRYSTAL), conditionsFromItem(ModItems.INFUSED_AMETHYST_CRYSTAL))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.ARCHITECT_STONE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ARCHITECT_STONE_BRICK, 4)
                .pattern("AA")
                .pattern("AA")
                .input('A', ModBlocks.ARCHITECT_STONE)
                .criterion(hasItem(ModBlocks.ARCHITECT_STONE), conditionsFromItem(ModBlocks.ARCHITECT_STONE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.ARCHITECT_STONE_BRICK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ARCHITECT_STONE_STAIRS, 6)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .input('A', ModBlocks.ARCHITECT_STONE)
                .criterion(hasItem(ModBlocks.ARCHITECT_STONE), conditionsFromItem(ModBlocks.ARCHITECT_STONE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.ARCHITECT_STONE_STAIRS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ARCHITECT_STONE_SLAB, 6)
                .pattern("AAA")
                .input('A', ModBlocks.ARCHITECT_STONE)
                .criterion(hasItem(ModBlocks.ARCHITECT_STONE), conditionsFromItem(ModBlocks.ARCHITECT_STONE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.ARCHITECT_STONE_SLAB)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.POLISHED_ARCHITECT_STONE, 2)
                .pattern("AA")
                .input('A', ModBlocks.ARCHITECT_STONE)
                .criterion(hasItem(ModBlocks.ARCHITECT_STONE), conditionsFromItem(ModBlocks.ARCHITECT_STONE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.POLISHED_ARCHITECT_STONE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ARCHITECT_STONE_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', ModBlocks.ARCHITECT_STONE)
                .criterion(hasItem(ModBlocks.ARCHITECT_STONE), conditionsFromItem(ModBlocks.ARCHITECT_STONE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.ARCHITECT_STONE_WALL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.RUNIC_CASING, 1)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .input('B', ModBlocks.ARCHITECT_STONE_BRICK)
                .input('A', Items.GOLD_INGOT)
                .criterion(hasItem(ModBlocks.ARCHITECT_STONE_BRICK), conditionsFromItem(ModBlocks.ARCHITECT_STONE_BRICK))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.RUNIC_CASING)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.STELLAR_DIAL_PEDESTAL_BLOCK, 1)
                .pattern("CDC")
                .pattern("ABA")
                .pattern("AAA")
                .input('A', ModBlocks.ARCHITECT_STONE_BRICK)
                .input('B', ModItems.INFUSED_AMETHYST_CRYSTAL)
                .input('C', Blocks.GLASS)
                .input('D', Blocks.STONE_BUTTON)
                .criterion(hasItem(ModBlocks.ARCHITECT_STONE_BRICK), conditionsFromItem(ModBlocks.ARCHITECT_STONE_BRICK))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.STELLAR_DIAL_PEDESTAL_BLOCK)));


        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.RESONANCE_TABLE, 1)
                .pattern("AAA")
                .pattern("DBD")
                .pattern("CCC")
                .input('A', Items.AMETHYST_SHARD)
                .input('B', Items.IRON_BLOCK)
                .input('C', Items.SMOOTH_STONE)
                .input('D', Items.REDSTONE)
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.RESONANCE_TABLE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CELENITE_TUBE, 8)
                .pattern("AAA")
                .pattern("BBB")
                .pattern("AAA")
                .input('A', Blocks.GLASS)
                .input('B', ModItems.CELENITE_CRYSTAL)
                .criterion(hasItem(ModItems.CELENITE_CRYSTAL), conditionsFromItem(ModItems.CELENITE_CRYSTAL))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.CELENITE_TUBE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.AETHERIUM_BLOCK, 1)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .input('A', ModItems.AETHERIUM_CRYSTAL)
                .criterion(hasItem(ModItems.AETHERIUM_CRYSTAL), conditionsFromItem(ModItems.AETHERIUM_CRYSTAL))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.AETHERIUM_BLOCK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RESONANT_BATTERY_TIER_1, 1)
                .pattern("ABA")
                .pattern("ABA")
                .pattern("ABA")
                .input('A', Items.IRON_INGOT)
                .input('B', ModItems.INFUSED_AMETHYST_CRYSTAL)
                .criterion(hasItem(ModItems.INFUSED_AMETHYST_CRYSTAL), conditionsFromItem(ModItems.INFUSED_AMETHYST_CRYSTAL))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.RESONANT_BATTERY_TIER_1)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RESONANT_BATTERY_TIER_2, 1)
                .pattern("ABA")
                .pattern("ABA")
                .pattern("ABA")
                .input('A', Items.IRON_INGOT)
                .input('B', ModItems.INFUSED_CELENITE_CRYSTAL)
                .criterion(hasItem(ModItems.INFUSED_CELENITE_CRYSTAL), conditionsFromItem(ModItems.INFUSED_CELENITE_CRYSTAL))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.RESONANT_BATTERY_TIER_2)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.IRON_GRATING, 16)
                .pattern("AAA")
                .pattern("   ")
                .pattern("AAA")
                .input('A', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.IRON_GRATING)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.GLIMMERWOOD_STAIRS, 6)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .input('A', ModBlocks.GLIMMERWOOD_PLANKS)
                .criterion(hasItem(ModBlocks.GLIMMERWOOD_PLANKS), conditionsFromItem(ModBlocks.GLIMMERWOOD_PLANKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.GLIMMERWOOD_STAIRS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLIMMERWOOD_WOOD, 3)
                .pattern("LL")
                .pattern("LL")
                .input('L', ModBlocks.GLIMMERWOOD_LOG)
                .criterion(hasItem(ModBlocks.GLIMMERWOOD_LOG), conditionsFromItem(ModBlocks.GLIMMERWOOD_LOG))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.GLIMMERWOOD_FENCE, 6)
                .pattern("ABA")
                .pattern("ABA")
                .input('A', ModBlocks.GLIMMERWOOD_PLANKS)
                .input('B', Items.STICK)
                .criterion(hasItem(ModBlocks.GLIMMERWOOD_PLANKS), conditionsFromItem(ModBlocks.GLIMMERWOOD_PLANKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.GLIMMERWOOD_FENCE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.GLIMMERWOOD_FENCE_GATE, 6)
                .pattern("ABA")
                .pattern("ABA")
                .input('B', ModBlocks.GLIMMERWOOD_PLANKS)
                .input('A', Items.STICK)
                .criterion(hasItem(ModBlocks.GLIMMERWOOD_PLANKS), conditionsFromItem(ModBlocks.GLIMMERWOOD_PLANKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.GLIMMERWOOD_FENCE_GATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.GLIMMERWOOD_SLAB, 6)
                .pattern("AAA")
                .input('A', ModBlocks.GLIMMERWOOD_PLANKS)
                .criterion(hasItem(ModBlocks.GLIMMERWOOD_PLANKS), conditionsFromItem(ModBlocks.GLIMMERWOOD_PLANKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.GLIMMERWOOD_SLAB)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.STARWILLOW_STAIRS, 6)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .input('A', ModBlocks.STARWILLOW_PLANKS)
                .criterion(hasItem(ModBlocks.STARWILLOW_PLANKS), conditionsFromItem(ModBlocks.STARWILLOW_PLANKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.STARWILLOW_STAIRS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.STARWILLOW_SLAB, 6)
                .pattern("AAA")
                .input('A', ModBlocks.STARWILLOW_PLANKS)
                .criterion(hasItem(ModBlocks.STARWILLOW_PLANKS), conditionsFromItem(ModBlocks.STARWILLOW_PLANKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.STARWILLOW_SLAB)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STARWILLOW_WOOD, 3)
                .pattern("LL")
                .pattern("LL")
                .input('L', ModBlocks.STARWILLOW_LOG)
                .criterion(hasItem(ModBlocks.STARWILLOW_LOG), conditionsFromItem(ModBlocks.STARWILLOW_LOG))
                .offerTo(exporter);


        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.STARWILLOW_FENCE, 6)
                .pattern("ABA")
                .pattern("ABA")
                .input('A', ModBlocks.STARWILLOW_PLANKS)
                .input('B', Items.STICK)
                .criterion(hasItem(ModBlocks.STARWILLOW_PLANKS), conditionsFromItem(ModBlocks.STARWILLOW_PLANKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.STARWILLOW_FENCE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.STARWILLOW_FENCE_GATE, 6)
                .pattern("ABA")
                .pattern("ABA")
                .input('B', ModBlocks.STARWILLOW_PLANKS)
                .input('A', Items.STICK)
                .criterion(hasItem(ModBlocks.STARWILLOW_PLANKS), conditionsFromItem(ModBlocks.STARWILLOW_PLANKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.STARWILLOW_FENCE_GATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.VESPERAN_COBBLESTONE_STAIRS, 6)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .input('A', ModBlocks.VESPERAN_COBBLESTONE)
                .criterion(hasItem(ModBlocks.VESPERAN_COBBLESTONE), conditionsFromItem(ModBlocks.VESPERAN_COBBLESTONE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.VESPERAN_COBBLESTONE_STAIRS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.VESPERAN_COBBLESTONE_SLAB, 6)
                .pattern("AAA")
                .input('A', ModBlocks.VESPERAN_COBBLESTONE)
                .criterion(hasItem(ModBlocks.VESPERAN_COBBLESTONE), conditionsFromItem(ModBlocks.VESPERAN_COBBLESTONE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.VESPERAN_COBBLESTONE_SLAB)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.VESPERAN_COBBLESTONE_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', ModBlocks.VESPERAN_COBBLESTONE)
                .criterion(hasItem(ModBlocks.VESPERAN_COBBLESTONE), conditionsFromItem(ModBlocks.VESPERAN_COBBLESTONE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.VESPERAN_COBBLESTONE_WALL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.VESPERAN_STONE_STAIRS, 6)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .input('A', ModBlocks.VESPERAN_STONE)
                .criterion(hasItem(ModBlocks.VESPERAN_STONE), conditionsFromItem(ModBlocks.VESPERAN_STONE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.VESPERAN_STONE_STAIRS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.VESPERAN_STONE_SLAB, 6)
                .pattern("AAA")
                .input('A', ModBlocks.VESPERAN_STONE)
                .criterion(hasItem(ModBlocks.VESPERAN_STONE), conditionsFromItem(ModBlocks.VESPERAN_STONE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.VESPERAN_STONE_SLAB)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.VESPERAN_STONE_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', ModBlocks.VESPERAN_STONE)
                .criterion(hasItem(ModBlocks.VESPERAN_STONE), conditionsFromItem(ModBlocks.VESPERAN_STONE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.VESPERAN_STONE_WALL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CELENITE_BLOCK, 1)
                .pattern("AA")
                .pattern("AA")
                .input('A', ModItems.CELENITE_CRYSTAL)
                .criterion(hasItem(ModItems.CELENITE_CRYSTAL), conditionsFromItem(ModItems.CELENITE_CRYSTAL))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.CELENITE_BLOCK)));
    }

    private void registerShapelessRecipes(RecipeExporter exporter)
    {
        offerPlanksRecipe(exporter, ModBlocks.GLIMMERWOOD_PLANKS, ModTags.Items.GLIMMERWOOD_LOGS, 4);
        offerPlanksRecipe(exporter, ModBlocks.STARWILLOW_PLANKS, ModTags.Items.STARWILLOW_LOGS, 4);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GLIMMER_MELON_SEEDS, 1)
                .input(ModItems.GLIMMER_MELON_SLICE)
                .criterion(hasItem(ModItems.GLIMMER_MELON_SLICE), conditionsFromItem(ModItems.GLIMMER_MELON_SLICE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.DAMAGED_IRON_GRATING, 1)
                .input(ModBlocks.IRON_GRATING)
                .criterion(hasItem(ModBlocks.IRON_GRATING), conditionsFromItem(ModBlocks.IRON_GRATING))
                .offerTo(exporter);

        offerGlyphDuplicationRecipe(exporter, ModItems.STELLAR_GLYPH_8);
        offerGlyphDuplicationRecipe(exporter, ModItems.STELLAR_GLYPH_O);
        offerGlyphDuplicationRecipe(exporter, ModItems.STELLAR_GLYPH_R);
        offerGlyphDuplicationRecipe(exporter, ModItems.STELLAR_GLYPH_SLASH);
        offerGlyphDuplicationRecipe(exporter, ModItems.STELLAR_GLYPH_W);
        offerGlyphDuplicationRecipe(exporter, ModItems.PRIME_GLYPH_BADGE);
        offerGlyphDuplicationRecipe(exporter, ModItems.PRIME_GLYPH_I);
        offerGlyphDuplicationRecipe(exporter, ModItems.PRIME_GLYPH_S);
        offerGlyphDuplicationRecipe(exporter, ModItems.PRIME_GLYPH_T);
        offerGlyphDuplicationRecipe(exporter, ModItems.PRIME_GLYPH_XX);
    }

    private void offerGlyphDuplicationRecipe(RecipeExporter exporter, Item glyph) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, glyph, 2)
                .input(Items.AMETHYST_SHARD)
                .input(Items.STONE)
                .input(glyph)
                .criterion(hasItem(glyph), conditionsFromItem(glyph))
                .offerTo(exporter, Identifier.of(getRecipeName(glyph) + "_duplication"));
    }
}


