package com.cudzdev.echoes_of_the_void.registry;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import java.util.List;

public class ModLootTableModifier {
    private static final List<RegistryKey<LootTable>> STELLAR_GLYPH_TABLES = List.of(
            LootTables.ABANDONED_MINESHAFT_CHEST,
            LootTables.ANCIENT_CITY_CHEST,
            LootTables.BASTION_TREASURE_CHEST,
            LootTables.END_CITY_TREASURE_CHEST,
            LootTables.JUNGLE_TEMPLE_CHEST,
            LootTables.SIMPLE_DUNGEON_CHEST,
            LootTables.DESERT_PYRAMID_CHEST,
            LootTables.HERO_OF_THE_VILLAGE_CARTOGRAPHER_GIFT_GAMEPLAY,
            LootTables.WOODLAND_MANSION_CHEST,
            LootTables.VILLAGE_CARTOGRAPHER_CHEST,
            LootTables.NETHER_BRIDGE_CHEST,
            LootTables.SHIPWRECK_MAP_CHEST,
            LootTables.STRONGHOLD_CORRIDOR_CHEST,
            LootTables.STRONGHOLD_CROSSING_CHEST,
            LootTables.STRONGHOLD_LIBRARY_CHEST,
            LootTables.FISHING_JUNK_GAMEPLAY
    );

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {

            if (source.isBuiltin() && STELLAR_GLYPH_TABLES.contains(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.60f))
                        .with(ItemEntry.builder(ModItems.PRIME_GLYPH_BADGE))
                        .with(ItemEntry.builder(ModItems.PRIME_GLYPH_I))
                        .with(ItemEntry.builder(ModItems.PRIME_GLYPH_S))
                        .with(ItemEntry.builder(ModItems.PRIME_GLYPH_T))
                        .with(ItemEntry.builder(ModItems.PRIME_GLYPH_XX))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)).build());

                tableBuilder.pool(poolBuilder);
            }
        });

        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {

            if (source.isBuiltin() && STELLAR_GLYPH_TABLES.contains(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.45f))
                        .with(ItemEntry.builder(ModItems.STELLAR_GLYPH_8))
                        .with(ItemEntry.builder(ModItems.STELLAR_GLYPH_O))
                        .with(ItemEntry.builder(ModItems.STELLAR_GLYPH_R))
                        .with(ItemEntry.builder(ModItems.STELLAR_GLYPH_SLASH))
                        .with(ItemEntry.builder(ModItems.STELLAR_GLYPH_W))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 5.0f)).build());

                tableBuilder.pool(poolBuilder);
            }
        });
    }
}
