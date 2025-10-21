package com.cudzdev.echoes_of_the_void.registry;

import com.cudzdev.echoes_of_the_void.Main;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import team.reborn.energy.api.base.SimpleEnergyItem;

public class ModItemGroup {
    public static final ItemGroup EOTV_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Main.MOD_ID, "echoes_of_the_void_group"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.echoes_of_the_void"))
                    .icon(() -> new ItemStack(ModItems.PRIME_GLYPH_XX))
                    .entries((displayContext, entries) -> {
                        // Items
                        entries.add(ModItems.INFUSED_AMETHYST_CRYSTAL);
                        entries.add(ModItems.INFUSED_CELENITE_CRYSTAL);

                        ItemStack chargedTier1Battery = new ItemStack(ModItems.RESONANT_BATTERY_TIER_1);

                        if (chargedTier1Battery.getItem() instanceof SimpleEnergyItem energyItem) {
                            energyItem.setStoredEnergy(chargedTier1Battery, energyItem.getEnergyCapacity(chargedTier1Battery));
                        }

                        entries.add(chargedTier1Battery);

                        ItemStack chargedTier2Battery = new ItemStack(ModItems.RESONANT_BATTERY_TIER_2);

                        if (chargedTier2Battery.getItem() instanceof SimpleEnergyItem energyItem) {
                            energyItem.setStoredEnergy(chargedTier2Battery, energyItem.getEnergyCapacity(chargedTier2Battery));
                        }
                        entries.add(chargedTier2Battery);

                        entries.add(ModItems.PRIME_GLYPH_XX);
                        entries.add(ModItems.PRIME_GLYPH_T);
                        entries.add(ModItems.PRIME_GLYPH_S);
                        entries.add(ModItems.PRIME_GLYPH_I);
                        entries.add(ModItems.PRIME_GLYPH_BADGE);
                        //entries.add(ModItems.BLANK_GLYPH);
                        entries.add(ModItems.STELLAR_GLYPH_O);
                        entries.add(ModItems.STELLAR_GLYPH_SLASH);
                        entries.add(ModItems.STELLAR_GLYPH_R);
                        entries.add(ModItems.STELLAR_GLYPH_W);
                        entries.add(ModItems.STELLAR_GLYPH_8);
                        entries.add(ModItems.AETHERIUM_CRYSTAL);
                        entries.add(ModItems.CELENITE_CRYSTAL);
                        entries.add(ModItems.BOTTLED_AETHERIUM_GEL);
                        entries.add(ModItems.GLIMMER_MELON_SLICE);
                        entries.add(ModItems.GLIMMER_MELON_SEEDS);

                        // Blocks
                        entries.add(ModBlocks.IRON_GRATING);
                        entries.add(ModBlocks.DAMAGED_IRON_GRATING);
                        entries.add(ModBlocks.ARCHITECT_STONE_BRICK);
                        entries.add(ModBlocks.INFUSED_ARCHITECT_STONE_BRICK);
                        entries.add(ModBlocks.RUNIC_CASING);
                        entries.add(ModBlocks.STELLAR_DIAL_PEDESTAL_BLOCK);
                        entries.add(ModBlocks.RESONANCE_TABLE);
                        entries.add(ModBlocks.ARCHITECT_STONE);
                        entries.add(ModBlocks.ARCHITECT_STONE_STAIRS);
                        entries.add(ModBlocks.ARCHITECT_STONE_SLAB);
                        entries.add(ModBlocks.ARCHITECT_STONE_WALL);
                        entries.add(ModBlocks.POLISHED_ARCHITECT_STONE);
                        entries.add(ModBlocks.GLIMMERWOOD_LOG);
                        entries.add(ModBlocks.GLIMMERWOOD_WOOD);
                        entries.add(ModBlocks.STRIPPED_GLIMMERWOOD_WOOD);
                        entries.add(ModBlocks.STRIPPED_GLIMMERWOOD_LOG);
                        entries.add(ModBlocks.GLIMMERWOOD_PLANKS);
                        entries.add(ModBlocks.GLIMMERWOOD_STAIRS);
                        entries.add(ModBlocks.GLIMMERWOOD_SLAB);
                        entries.add(ModBlocks.GLIMMERWOOD_FENCE);
                        entries.add(ModBlocks.GLIMMERWOOD_FENCE_GATE);
                        entries.add(ModBlocks.GLIMMERWOOD_LEAVES);
                        entries.add(ModBlocks.GLIMMERWOOD_SAPLING);
                        entries.add(ModBlocks.GLOW_MOSS);
                        entries.add(ModBlocks.GLOW_MOSS_CARPET);
                        entries.add(ModBlocks.STARGAZER_FLOWER);
                        entries.add(ModBlocks.CRYSTAL_SPROUT);
                        entries.add(ModBlocks.VESPERAN_STONE);
                        entries.add(ModBlocks.VESPERAN_COBBLESTONE);
                        entries.add(ModBlocks.VESPERAN_STONE_STAIRS);
                        entries.add(ModBlocks.VESPERAN_STONE_SLAB);
                        entries.add(ModBlocks.VESPERAN_STONE_WALL);
                        entries.add(ModBlocks.VESPERAN_COBBLESTONE_SLAB);
                        entries.add(ModBlocks.VESPERAN_COBBLESTONE_STAIRS);
                        entries.add(ModBlocks.VESPERAN_COBBLESTONE_WALL);
                        entries.add(ModBlocks.GLIMMER_MELON);

                        entries.add(ModBlocks.VESPERAN_IRON_ORE);
                        entries.add(ModBlocks.VESPERAN_COAL_ORE);
                        entries.add(ModBlocks.VESPERAN_COPPER_ORE);
                        entries.add(ModBlocks.VESPERAN_GOLD_ORE);
                        entries.add(ModBlocks.VESPERAN_LAPIS_ORE);
                        entries.add(ModBlocks.VESPERAN_REDSTONE_ORE);
                        entries.add(ModBlocks.VESPERAN_EMERALD_ORE);
                        entries.add(ModBlocks.VESPERAN_DIAMOND_ORE);
                        entries.add(ModBlocks.AETHERIUM_ORE);

                        entries.add(ModBlocks.CELENITE_BLOCK);
                        entries.add(ModBlocks.BUDDING_CELENITE_BLOCK);
                        entries.add(ModBlocks.CELENITE_CLUSTER_BLOCK);

                        entries.add(ModBlocks.STARWILLOW_LOG);
                        entries.add(ModBlocks.STARWILLOW_WOOD);
                        entries.add(ModBlocks.STRIPPED_STARWILLOW_WOOD);
                        entries.add(ModBlocks.STRIPPED_STARWILLOW_LOG);
                        entries.add(ModBlocks.STARWILLOW_PLANKS);
                        entries.add(ModBlocks.STARWILLOW_LEAVES);
                        entries.add(ModBlocks.STARWILLOW_SAPLING);
                        entries.add(ModBlocks.STARWILLOW_STAIRS);
                        entries.add(ModBlocks.STARWILLOW_SLAB);
                        entries.add(ModBlocks.STARWILLOW_FENCE);
                        entries.add(ModBlocks.STARWILLOW_FENCE_GATE);

                        entries.add(ModBlocks.BLUE_CAVESHROOM);
                        entries.add(ModBlocks.GREEN_CAVESHROOM);
                        entries.add(ModBlocks.PINK_CAVESHROOM);
                        entries.add(ModBlocks.HANGING_BLUE_CAVESHROOM);
                        entries.add(ModBlocks.HANGING_GREEN_CAVESHROOM);
                        entries.add(ModBlocks.HANGING_PINK_CAVESHROOM);
                        entries.add(ModBlocks.AETHERIUM_ROOTS);

                        entries.add(ModBlocks.BLUE_CAVESHROOM_BLOCK);
                        entries.add(ModBlocks.GREEN_CAVESHROOM_BLOCK);
                        entries.add(ModBlocks.PINK_CAVESHROOM_BLOCK);
                        entries.add(ModBlocks.CELENITE_TUBE);
                        entries.add(ModBlocks.AETHERIUM_BLOCK);
                        entries.add(ModBlocks.AETHERIUM_GEL_BLOCK);
                    })
                    .build());

    public static void registerItemGroups() {
        Main.LOGGER.debug("Registering Item Groups for " + Main.MOD_ID);
    }
}
