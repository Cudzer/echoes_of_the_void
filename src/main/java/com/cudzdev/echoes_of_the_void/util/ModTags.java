package com.cudzdev.echoes_of_the_void.util;

import com.cudzdev.echoes_of_the_void.Main;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Items {
        // This creates a unique tag key for our group of resonant crystals.
        public static final TagKey<Item> RESONANT_CRYSTALS =
                createTag("resonant_crystals");

        public static final TagKey<Item> PRIME_GLYPHS =
                createTag("prime_glyphs");

        public static final TagKey<Item> ALL_GLYPHS =
                createTag("all_glyphs");

        public static final TagKey<Item> GLIMMERWOOD_LOGS =
                createTag("glimmerwood_logs");

        public static final TagKey<Item> STARWILLOW_LOGS =
                createTag("starwillow_logs");

        public static final TagKey<Item> C_GEMS = createTag("c", "gems");
        public static final TagKey<Item> C_SEEDS = createTag("c", "seeds");
        public static final TagKey<Item> C_FOODS_FRUIT = createTag("c", "foods/fruit");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, name));
        }

        private static TagKey<Item> createTag(String namespace, String path) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(namespace, path));
        }

        public static void registerTags(){

        }
    }

    public static class Blocks {
        public static final TagKey<Block> VESPERA_SOIL =
                createTag("vespera_soil");

        public static final TagKey<Block> C_LOGS = createTag("c", "logs");
        public static final TagKey<Block> C_WOODS = createTag("c", "woods");
        public static final TagKey<Block> C_STRIPPED_LOGS = createTag("c", "stripped_logs");
        public static final TagKey<Block> C_STRIPPED_WOODS = createTag("c", "stripped_woods");
        public static final TagKey<Block> C_PLANKS_THAT_BURN = createTag("c", "planks_that_burn");
        public static final TagKey<Block> C_ORES_COPPER = createTag("c", "ores/copper");
        public static final TagKey<Block> C_ORES_COAL = createTag("c", "ores/coal");
        public static final TagKey<Block> C_ORES_IRON = createTag("c", "ores/iron");
        public static final TagKey<Block> C_ORES_GOLD = createTag("c", "ores/gold");
        public static final TagKey<Block> C_ORES_EMERALD = createTag("c", "ores/emerald");
        public static final TagKey<Block> C_ORES_DIAMOND = createTag("c", "ores/diamond");
        public static final TagKey<Block> C_ORES_REDSTONE = createTag("c", "ores/redstone");
        public static final TagKey<Block> C_ORES_LAPIS = createTag("c", "ores/lapis");
        public static final TagKey<Block> C_MUSHROOMS = createTag("c", "mushrooms");
        public static final TagKey<Block> C_BUDDING_BLOCKS = createTag("c", "budding_blocks");
        public static final TagKey<Block> C_CLUSTERS = createTag("c", "clusters");
        public static final TagKey<Block> C_FLOWERS = createTag("c", "flowers");
        public static final TagKey<Block> C_FENCES_WOODEN = createTag("c", "fences/wooden");
        public static final TagKey<Block> C_FENCE_GATES_WOODEN = createTag("c", "fence_gates/wooden");

        public static final TagKey<Block> C_STONES = createTag("c", "stones");
        public static final TagKey<Block> C_COBBLESTONES = createTag("c", "cobblestones");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Main.MOD_ID, name));
        }

        private static TagKey<Block> createTag(String namespace, String path) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(namespace, path));
        }

        public static void registerTags(){

        }
    }
}
