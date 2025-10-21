package com.cudzdev.echoes_of_the_void.datagen;

import com.cudzdev.echoes_of_the_void.registry.ModBlocks;
import com.cudzdev.echoes_of_the_void.util.ModTags;
import dev.architectury.platform.Mod;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider  extends FabricTagProvider.BlockTagProvider{

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.ARCHITECT_STONE)
                .add(ModBlocks.ARCHITECT_STONE_STAIRS)
                .add(ModBlocks.ARCHITECT_STONE_SLAB)
                .add(ModBlocks.ARCHITECT_STONE_WALL)
                .add(ModBlocks.ARCHITECT_STONE_BRICK)
                .add(ModBlocks.POLISHED_ARCHITECT_STONE)
                .add(ModBlocks.RUNIC_CASING)
                .add(ModBlocks.INFUSED_ARCHITECT_STONE_BRICK)
                .add(ModBlocks.VESPERAN_STONE)
                .add(ModBlocks.VESPERAN_COBBLESTONE)
                .add(ModBlocks.VESPERAN_STONE_STAIRS)
                .add(ModBlocks.VESPERAN_STONE_SLAB)
                .add(ModBlocks.VESPERAN_STONE_WALL)
                .add(ModBlocks.VESPERAN_STONE_STAIRS)
                .add(ModBlocks.VESPERAN_COBBLESTONE_STAIRS)
                .add(ModBlocks.VESPERAN_COBBLESTONE_SLAB)
                .add(ModBlocks.VESPERAN_COBBLESTONE_WALL)
                .add(ModBlocks.CELENITE_BLOCK)
                .add(ModBlocks.CELENITE_CLUSTER_BLOCK)
                .add(ModBlocks.BUDDING_CELENITE_BLOCK)
                .add(ModBlocks.VESPERAN_IRON_ORE)
                .add(ModBlocks.VESPERAN_COAL_ORE)
                .add(ModBlocks.VESPERAN_COPPER_ORE)
                .add(ModBlocks.VESPERAN_GOLD_ORE)
                .add(ModBlocks.VESPERAN_LAPIS_ORE)
                .add(ModBlocks.VESPERAN_REDSTONE_ORE)
                .add(ModBlocks.VESPERAN_EMERALD_ORE)
                .add(ModBlocks.VESPERAN_DIAMOND_ORE)
                .add(ModBlocks.AETHERIUM_ORE)
                .add(ModBlocks.AETHERIUM_BLOCK)
                .add(ModBlocks.IRON_GRATING)
                .add(ModBlocks.DAMAGED_IRON_GRATING);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.GLIMMERWOOD_LOG)
                .add(ModBlocks.GLIMMERWOOD_WOOD)
                .add(ModBlocks.STRIPPED_GLIMMERWOOD_LOG)
                .add(ModBlocks.STRIPPED_GLIMMERWOOD_WOOD)
                .add(ModBlocks.STARWILLOW_LOG)
                .add(ModBlocks.STARWILLOW_WOOD)
                .add(ModBlocks.STRIPPED_STARWILLOW_LOG)
                .add(ModBlocks.STRIPPED_STARWILLOW_WOOD)
                .add(ModBlocks.STARWILLOW_PLANKS)
                .add(ModBlocks.STARWILLOW_STAIRS)
                .add(ModBlocks.STARWILLOW_SLAB)
                .add(ModBlocks.GLIMMERWOOD_PLANKS)
                .add(ModBlocks.GLIMMERWOOD_SLAB)
                .add(ModBlocks.GLIMMERWOOD_STAIRS)
                .add(ModBlocks.GLIMMERWOOD_FENCE)
                .add(ModBlocks.GLIMMERWOOD_FENCE_GATE)
                .add(ModBlocks.STARWILLOW_FENCE)
                .add(ModBlocks.STARWILLOW_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.STARWILLOW_FENCE)
                .add(ModBlocks.GLIMMERWOOD_FENCE);

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.STARWILLOW_FENCE_GATE)
                .add(ModBlocks.GLIMMERWOOD_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.ARCHITECT_STONE_WALL)
                .add(ModBlocks.VESPERAN_COBBLESTONE_WALL)
                .add(ModBlocks.VESPERAN_STONE_WALL);

        getOrCreateTagBuilder(BlockTags.LOGS)
                .add(ModBlocks.GLIMMERWOOD_LOG)
                .add(ModBlocks.STRIPPED_GLIMMERWOOD_LOG)
                .add(ModBlocks.GLIMMERWOOD_WOOD)
                .add(ModBlocks.STARWILLOW_WOOD);

        getOrCreateTagBuilder(ModTags.Blocks.C_LOGS)
                .add(ModBlocks.GLIMMERWOOD_LOG)
                .add(ModBlocks.STARWILLOW_LOG);

        getOrCreateTagBuilder(ModTags.Blocks.C_STRIPPED_LOGS)
                .add(ModBlocks.STRIPPED_GLIMMERWOOD_LOG)
                .add(ModBlocks.STRIPPED_STARWILLOW_LOG);

        getOrCreateTagBuilder(ModTags.Blocks.C_WOODS)
                .add(ModBlocks.GLIMMERWOOD_WOOD)
                .add(ModBlocks.STARWILLOW_WOOD);

        getOrCreateTagBuilder(ModTags.Blocks.C_STRIPPED_WOODS)
                .add(ModBlocks.STRIPPED_GLIMMERWOOD_WOOD)
                .add(ModBlocks.STRIPPED_STARWILLOW_WOOD);

        getOrCreateTagBuilder(ModTags.Blocks.C_PLANKS_THAT_BURN)
                .add(ModBlocks.GLIMMERWOOD_PLANKS)
                .add(ModBlocks.STARWILLOW_PLANKS);

        getOrCreateTagBuilder(ModTags.Blocks.C_ORES_COPPER)
                .add(ModBlocks.VESPERAN_COPPER_ORE);
        getOrCreateTagBuilder(ModTags.Blocks.C_ORES_COAL)
                .add(ModBlocks.VESPERAN_COAL_ORE);
        getOrCreateTagBuilder(ModTags.Blocks.C_ORES_IRON)
                .add(ModBlocks.VESPERAN_IRON_ORE);
        getOrCreateTagBuilder(ModTags.Blocks.C_ORES_GOLD)
                .add(ModBlocks.VESPERAN_GOLD_ORE);
        getOrCreateTagBuilder(ModTags.Blocks.C_ORES_EMERALD)
                .add(ModBlocks.VESPERAN_EMERALD_ORE);
        getOrCreateTagBuilder(ModTags.Blocks.C_ORES_DIAMOND)
                .add(ModBlocks.VESPERAN_DIAMOND_ORE);
        getOrCreateTagBuilder(ModTags.Blocks.C_ORES_REDSTONE)
                .add(ModBlocks.VESPERAN_REDSTONE_ORE);
        getOrCreateTagBuilder(ModTags.Blocks.C_ORES_LAPIS)
                .add(ModBlocks.VESPERAN_LAPIS_ORE);

        getOrCreateTagBuilder(ModTags.Blocks.C_MUSHROOMS)
                .add(ModBlocks.BLUE_CAVESHROOM)
                .add(ModBlocks.PINK_CAVESHROOM)
                .add(ModBlocks.GREEN_CAVESHROOM);

        getOrCreateTagBuilder(ModTags.Blocks.C_BUDDING_BLOCKS)
                .add(ModBlocks.BUDDING_CELENITE_BLOCK);

        getOrCreateTagBuilder(ModTags.Blocks.C_CLUSTERS)
                .add(ModBlocks.CELENITE_CLUSTER_BLOCK);

        getOrCreateTagBuilder(ModTags.Blocks.C_STONES)
                .add(ModBlocks.VESPERAN_STONE);

        getOrCreateTagBuilder(ModTags.Blocks.C_COBBLESTONES)
                .add(ModBlocks.VESPERAN_COBBLESTONE);

        getOrCreateTagBuilder(ModTags.Blocks.C_FLOWERS)
                .add(ModBlocks.STARGAZER_FLOWER)
                .add(ModBlocks.CRYSTAL_SPROUT);

        getOrCreateTagBuilder(ModTags.Blocks.C_FENCES_WOODEN)
                .add(ModBlocks.GLIMMERWOOD_FENCE)
                .add(ModBlocks.STARWILLOW_FENCE);

        getOrCreateTagBuilder(ModTags.Blocks.C_FENCE_GATES_WOODEN)
                .add(ModBlocks.GLIMMERWOOD_FENCE_GATE)
                .add(ModBlocks.STARWILLOW_FENCE_GATE);
    }
}
