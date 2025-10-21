package com.cudzdev.echoes_of_the_void.datagen;

import com.cudzdev.echoes_of_the_void.registry.ModBlocks;
import com.cudzdev.echoes_of_the_void.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        addDrop(ModBlocks.ARCHITECT_STONE);
        addDrop(ModBlocks.ARCHITECT_STONE_WALL);
        addDrop(ModBlocks.ARCHITECT_STONE);
        addDrop(ModBlocks.ARCHITECT_STONE_BRICK);
        addDrop(ModBlocks.ARCHITECT_STONE_SLAB, slabDrops(ModBlocks.ARCHITECT_STONE_SLAB));
        addDrop(ModBlocks.ARCHITECT_STONE_STAIRS);

        addDrop(ModBlocks.RUNIC_CASING);
        addDrop(ModBlocks.INFUSED_ARCHITECT_STONE_BRICK);
        addDrop(ModBlocks.POLISHED_ARCHITECT_STONE);
        addDrop(ModBlocks.GLOW_MOSS);
        addDrop(ModBlocks.GLOW_MOSS_CARPET);

        addDropWithSilkTouch(ModBlocks.VESPERAN_STONE, ModBlocks.VESPERAN_COBBLESTONE);
        addDrop(ModBlocks.VESPERAN_COBBLESTONE);
        addDrop(ModBlocks.VESPERAN_STONE_SLAB, slabDrops(ModBlocks.VESPERAN_STONE_SLAB));
        addDrop(ModBlocks.VESPERAN_STONE_STAIRS);
        addDrop(ModBlocks.VESPERAN_STONE_WALL);
        addDrop(ModBlocks.VESPERAN_COBBLESTONE_SLAB, slabDrops(ModBlocks.VESPERAN_COBBLESTONE_SLAB));
        addDrop(ModBlocks.VESPERAN_COBBLESTONE_STAIRS);
        addDrop(ModBlocks.VESPERAN_COBBLESTONE_WALL);

        addDrop(ModBlocks.VESPERAN_IRON_ORE, oreDrops(ModBlocks.VESPERAN_IRON_ORE, Items.RAW_IRON));
        addDrop(ModBlocks.VESPERAN_COAL_ORE, oreDrops(ModBlocks.VESPERAN_COAL_ORE, Items.COAL));
        addDrop(ModBlocks.VESPERAN_COPPER_ORE, oreDrops(ModBlocks.VESPERAN_COPPER_ORE, Items.RAW_COPPER));
        addDrop(ModBlocks.VESPERAN_GOLD_ORE, oreDrops(ModBlocks.VESPERAN_GOLD_ORE, Items.RAW_GOLD));
        addDrop(ModBlocks.VESPERAN_LAPIS_ORE, oreDrops(ModBlocks.VESPERAN_LAPIS_ORE, Items.LAPIS_LAZULI));
        addDrop(ModBlocks.VESPERAN_REDSTONE_ORE, oreDrops(ModBlocks.VESPERAN_REDSTONE_ORE, Items.REDSTONE));
        addDrop(ModBlocks.VESPERAN_EMERALD_ORE, oreDrops(ModBlocks.VESPERAN_EMERALD_ORE, Items.EMERALD));
        addDrop(ModBlocks.VESPERAN_DIAMOND_ORE, oreDrops(ModBlocks.VESPERAN_DIAMOND_ORE, Items.DIAMOND));
        addDrop(ModBlocks.AETHERIUM_ORE, oreDrops(ModBlocks.AETHERIUM_ORE, ModItems.AETHERIUM_CRYSTAL));

        addDrop(ModBlocks.CELENITE_CLUSTER_BLOCK, oreDrops(ModBlocks.CELENITE_CLUSTER_BLOCK, ModItems.CELENITE_CRYSTAL));
        addDrop(ModBlocks.CELENITE_BLOCK);
        addDrop(ModBlocks.BUDDING_CELENITE_BLOCK, dropsWithSilkTouch(ModBlocks.BUDDING_CELENITE_BLOCK));
        addDrop(ModBlocks.CELENITE_TUBE);
        addDrop(ModBlocks.AETHERIUM_BLOCK);


        addDrop(ModBlocks.GLIMMERWOOD_LOG);
        addDrop(ModBlocks.GLIMMERWOOD_WOOD);
        addDrop(ModBlocks.STRIPPED_GLIMMERWOOD_LOG);
        addDrop(ModBlocks.STRIPPED_GLIMMERWOOD_WOOD);
        addDrop(ModBlocks.GLIMMERWOOD_PLANKS);
        addDrop(ModBlocks.GLIMMERWOOD_SAPLING);
        addDrop(ModBlocks.GLIMMERWOOD_STAIRS);
        addDrop(ModBlocks.GLIMMERWOOD_FENCE);
        addDrop(ModBlocks.GLIMMERWOOD_FENCE_GATE);
        addDrop(ModBlocks.GLIMMERWOOD_SLAB, slabDrops(ModBlocks.GLIMMERWOOD_SLAB));
        addDrop(ModBlocks.GLIMMERWOOD_LEAVES, leavesDrops(ModBlocks.GLIMMERWOOD_LEAVES, ModBlocks.GLIMMERWOOD_SAPLING, 0.0625f));

        addDrop(ModBlocks.STARWILLOW_LOG);
        addDrop(ModBlocks.STARWILLOW_WOOD);
        addDrop(ModBlocks.STRIPPED_STARWILLOW_LOG);
        addDrop(ModBlocks.STRIPPED_STARWILLOW_WOOD);
        addDrop(ModBlocks.STARWILLOW_PLANKS);
        addDrop(ModBlocks.STARWILLOW_SAPLING);
        addDrop(ModBlocks.STARWILLOW_STAIRS);
        addDrop(ModBlocks.STARWILLOW_FENCE);
        addDrop(ModBlocks.STARWILLOW_FENCE_GATE);
        addDrop(ModBlocks.STARWILLOW_SLAB, slabDrops(ModBlocks.STARWILLOW_SLAB));
        addDrop(ModBlocks.STARWILLOW_LEAVES, leavesDrops(ModBlocks.STARWILLOW_LEAVES, ModBlocks.STARWILLOW_SAPLING, 0.0625f));

        addDrop(ModBlocks.STARGAZER_FLOWER);
        addDrop(ModBlocks.CRYSTAL_SPROUT);
        addDrop(ModBlocks.BLUE_CAVESHROOM, dropsWithShears(ModBlocks.BLUE_CAVESHROOM));
        addDrop(ModBlocks.GREEN_CAVESHROOM, dropsWithShears(ModBlocks.GREEN_CAVESHROOM));
        addDrop(ModBlocks.PINK_CAVESHROOM, dropsWithShears(ModBlocks.PINK_CAVESHROOM));
        addDrop(ModBlocks.HANGING_BLUE_CAVESHROOM, dropsWithShears(ModBlocks.BLUE_CAVESHROOM));
        addDrop(ModBlocks.HANGING_GREEN_CAVESHROOM, dropsWithShears(ModBlocks.GREEN_CAVESHROOM));
        addDrop(ModBlocks.HANGING_PINK_CAVESHROOM, dropsWithShears(ModBlocks.PINK_CAVESHROOM));
        addDrop(ModBlocks.AETHERIUM_ROOTS, dropsWithShears(ModBlocks.AETHERIUM_ROOTS));

        addDropWithSilkTouch(ModBlocks.BLUE_CAVESHROOM_BLOCK, ModBlocks.BLUE_CAVESHROOM);
        addDropWithSilkTouch(ModBlocks.GREEN_CAVESHROOM_BLOCK, ModBlocks.GREEN_CAVESHROOM);
        addDropWithSilkTouch(ModBlocks.PINK_CAVESHROOM_BLOCK, ModBlocks.PINK_CAVESHROOM);

        addDrop(ModBlocks.AETHERIUM_GEL_BLOCK);
        addDrop(ModBlocks.IRON_GRATING);
        addDrop(ModBlocks.DAMAGED_IRON_GRATING);
    }
}
