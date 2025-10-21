package com.cudzdev.echoes_of_the_void.registry;

import com.cudzdev.echoes_of_the_void.Main;
import com.cudzdev.echoes_of_the_void.block.*;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {

    public static final WoodType STARWILLOW_WOOD_TYPE = new WoodType(Main.MOD_ID + ":starwillow", BlockSetType.OAK);
    public static final WoodType GLIMMERWOOD_WOOD_TYPE = new WoodType(Main.MOD_ID + ":glimmerwood", BlockSetType.OAK);

    public static final RegistryKey<Block> GLIMMER_MELON_KEY = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Main.MOD_ID, "glimmer_melon"));
    public static final RegistryKey<Block> GLIMMER_MELON_STEM_KEY = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Main.MOD_ID, "glimmer_melon_stem"));
    public static final RegistryKey<Block> ATTACHED_GLIMMER_MELON_STEM_KEY = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Main.MOD_ID, "attached_glimmer_melon_stem"));

    //REGISTER BLOCKS HERE
    public static final Block STELLAR_DIAL_PEDESTAL_BLOCK  = registerBlock("stellar_dial_pedestal",
            new StellarDialPedestalBlock(AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK)
                    .strength(22.0f, 1200.0f)
                    .nonOpaque()
                    .requiresTool()), true);

    public static final Block RESONANCE_TABLE  = registerBlock("resonance_table",
            new ResonanceTableBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
                    .strength(22.0f, 9.0f)
                    .nonOpaque()
                    .requiresTool()), true);

    public static final Block ARCHITECT_STONE_BRICK = registerBlock("architect_stone_brick",
            new Block(AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK)
                    .strength(22.0f, 9.0f)
                    .requiresTool()), true);

    public static final Block RUNIC_CASING = registerBlock("runic_casing",
            new RunicCasingBlock(AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK)
                    .strength(22.0f, 9.0f)
                    .requiresTool()
                    .luminance(state -> state.get(RunicCasingBlock.LIT) ? 15 : 0)), true);

    public static final Block ARCHITECT_STONE = registerBlock("architect_stone",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE)
                    .requiresTool()), true);
    public static final Block ARCHITECT_STONE_STAIRS = registerBlock("architect_stone_stairs",
            new StairsBlock(ARCHITECT_STONE.getDefaultState(), AbstractBlock.Settings.copy(ARCHITECT_STONE).requiresTool()), true);

    public static final Block ARCHITECT_STONE_SLAB = registerBlock("architect_stone_slab",
            new SlabBlock(AbstractBlock.Settings.copy(ARCHITECT_STONE).requiresTool()), true);

    public static final Block ARCHITECT_STONE_WALL = registerBlock("architect_stone_wall",
            new WallBlock(AbstractBlock.Settings.copy(ARCHITECT_STONE)), true);

    public static final Block POLISHED_ARCHITECT_STONE = registerBlock("polished_architect_stone",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)
                    .requiresTool()), true);
    public static final Block INFUSED_ARCHITECT_STONE_BRICK = registerBlock("infused_architect_stone_brick",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)
                    .requiresTool()), true);


    public static final Block GLIMMERWOOD_LOG = registerBlock("glimmerwood_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG).strength(2.5f)), true);

    public static final Block GLIMMERWOOD_LEAVES = registerBlock("glimmerwood_leaves",
            new LeavesBlock(AbstractBlock.Settings.create()
                    .strength(0.2f) // Standard leaf hardness
                    .sounds(BlockSoundGroup.GRASS)
                    .nonOpaque() // Allows light to pass through
                    .allowsSpawning((state, world, pos, type) -> false) // No mobs on leaves
                    .solidBlock((state, world, pos) -> false) // Not a solid block
                    .suffocates((state, world, pos) -> false) // Doesn't suffocate
                    .blockVision((state, world, pos) -> false) // Doesn't block vision
                    .burnable()), true);

    // The sapling needs its own class to know how to grow the tree
    public static final Block GLIMMERWOOD_SAPLING = registerBlock("glimmerwood_sapling",
            new GlimmerwoodSaplingBlock(ModSaplingGenerators.GLIMMERWOOD, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)
                    .noCollision().ticksRandomly().breakInstantly()
                    .sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY)), true);

    public static final Block STRIPPED_GLIMMERWOOD_LOG = registerBlock("stripped_glimmerwood_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG)), true);

    public static final Block GLIMMERWOOD_WOOD = registerBlock("glimmerwood_wood",
            new PillarBlock(AbstractBlock.Settings.copy(ModBlocks.GLIMMERWOOD_LOG)), true);

    public static final Block STRIPPED_GLIMMERWOOD_WOOD = registerBlock("stripped_glimmerwood_wood",
            new PillarBlock(AbstractBlock.Settings.copy(ModBlocks.STRIPPED_GLIMMERWOOD_LOG)), true);

    public static final Block GLIMMERWOOD_PLANKS = registerBlock("glimmerwood_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)), true);

    public static final Block GLIMMERWOOD_STAIRS = registerBlock("glimmerwood_stairs",
            new StairsBlock(GLIMMERWOOD_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(GLIMMERWOOD_PLANKS).requiresTool()), true);

    public static final Block GLIMMERWOOD_SLAB = registerBlock("glimmerwood_slab",
            new SlabBlock(AbstractBlock.Settings.copy(GLIMMERWOOD_PLANKS).requiresTool()), true);

    public static final Block GLIMMERWOOD_FENCE = registerBlock("glimmerwood_fence",
            new FenceBlock(AbstractBlock.Settings.copy(GLIMMERWOOD_PLANKS).requiresTool()), true);

    public static final Block GLIMMERWOOD_FENCE_GATE = registerBlock("glimmerwood_fence_gate",
            new FenceGateBlock(GLIMMERWOOD_WOOD_TYPE, AbstractBlock.Settings.copy(GLIMMERWOOD_PLANKS).requiresTool()), true);

    public static final Block STARWILLOW_LOG = registerBlock("starwillow_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG).strength(2.5f)), true);

    public static final Block STARWILLOW_WOOD = registerBlock("starwillow_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD)), true);

    public static final Block STRIPPED_STARWILLOW_WOOD = registerBlock("stripped_starwillow_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)), true);

    public static final Block STARWILLOW_LEAVES = registerBlock("starwillow_leaves",
            new LeavesBlock(AbstractBlock.Settings.create()
                    .strength(0.2f) // Standard leaf hardness
                    .sounds(BlockSoundGroup.GRASS)
                    .nonOpaque() // Allows light to pass through
                    .allowsSpawning((state, world, pos, type) -> false) // No mobs on leaves
                    .solidBlock((state, world, pos) -> false) // Not a solid block
                    .suffocates((state, world, pos) -> false) // Doesn't suffocate
                    .blockVision((state, world, pos) -> false) // Doesn't block vision
                    .burnable()), true);

    public static final Block STARWILLOW_SAPLING = registerBlock("starwillow_sapling",
            new StarWillowSaplingBlock(ModSaplingGenerators.STARWILLOW, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)
                    .noCollision().ticksRandomly().breakInstantly()
                    .sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY)), true);

    public static final Block STRIPPED_STARWILLOW_LOG = registerBlock("stripped_starwillow_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG)), true);

    public static final Block STARWILLOW_PLANKS = registerBlock("starwillow_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)), true);

    public static final Block STARWILLOW_STAIRS = registerBlock("starwillow_stairs",
            new StairsBlock(STARWILLOW_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(STARWILLOW_PLANKS).requiresTool()), true);

    public static final Block STARWILLOW_SLAB = registerBlock("starwillow_slab",
            new SlabBlock(AbstractBlock.Settings.copy(STARWILLOW_PLANKS).requiresTool()), true);

    public static final Block STARWILLOW_FENCE = registerBlock("starwillow_fence",
            new FenceBlock(AbstractBlock.Settings.copy(STARWILLOW_PLANKS).requiresTool()), true);

    public static final Block STARWILLOW_FENCE_GATE = registerBlock("starwillow_fence_gate",
            new FenceGateBlock(STARWILLOW_WOOD_TYPE, AbstractBlock.Settings.copy(STARWILLOW_PLANKS).requiresTool()), true);

    public static final Block GLOW_MOSS = registerBlock("glow_moss",
            new Block(AbstractBlock.Settings.copy((Blocks.MOSS_BLOCK))), true);

    public static final Block STARGAZER_FLOWER = registerBlock("stargazer",
            new FlowerBlock(
                    // 1. The stew effect: We'll use the vanilla Glowing effect.
                    net.minecraft.entity.effect.StatusEffects.GLOWING,
                    // 2. The effect duration in ticks (8 seconds * 20 ticks/second)
                    160,
                    // 3. The block's settings: no collision, breaks instantly, etc.
                    AbstractBlock.Settings.create()
                            .noCollision()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.GRASS)
                            .pistonBehavior(PistonBehavior.DESTROY)
            ), true);

    public static final Block CRYSTAL_SPROUT = registerBlock("crystal_sprout",
            new FlowerBlock(
                    StatusEffects.LUCK,
                    160,
                    AbstractBlock.Settings.create()
                            .noCollision()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.GRASS)
                            .pistonBehavior(PistonBehavior.DESTROY)
            ), true);

    public static final Block BLUE_CAVESHROOM = registerBlock("blue_caveshroom",
            new MushroomPlantBlock(ModConfiguredFeatures.HUGE_BLUE_CAVESHROOM_KEY,
                    AbstractBlock.Settings.copy(Blocks.RED_MUSHROOM).luminance(state -> 6).nonOpaque()), true);

    public static final Block GREEN_CAVESHROOM = registerBlock("green_caveshroom",
            new MushroomPlantBlock(ModConfiguredFeatures.HUGE_GREEN_CAVESHROOM_KEY,
                    AbstractBlock.Settings.copy(Blocks.RED_MUSHROOM).luminance(state -> 6).nonOpaque()), true);

    public static final Block PINK_CAVESHROOM = registerBlock("pink_caveshroom",
            new MushroomPlantBlock(ModConfiguredFeatures.HUGE_PINK_CAVESHROOM_KEY,
                    AbstractBlock.Settings.copy(Blocks.RED_MUSHROOM).luminance(state -> 6).nonOpaque()), true);

    public static final Block HANGING_BLUE_CAVESHROOM = registerBlock("hanging_blue_caveshroom",
            new GlowLichenBlock(AbstractBlock.Settings.copy(Blocks.GLOW_LICHEN).luminance(state -> 5)), true);
    public static final Block HANGING_GREEN_CAVESHROOM = registerBlock("hanging_green_caveshroom",
            new GlowLichenBlock(AbstractBlock.Settings.copy(Blocks.GLOW_LICHEN).luminance(state -> 5)), true);
    public static final Block HANGING_PINK_CAVESHROOM = registerBlock("hanging_pink_caveshroom",
            new GlowLichenBlock(AbstractBlock.Settings.copy(Blocks.GLOW_LICHEN).luminance(state -> 5)), true);

    public static final Block BLUE_CAVESHROOM_BLOCK = registerBlock("blue_caveshroom_block",
            new MushroomBlock(AbstractBlock.Settings.copy(Blocks.RED_MUSHROOM_BLOCK).luminance(state -> 8)), true);
    public static final Block GREEN_CAVESHROOM_BLOCK = registerBlock("green_caveshroom_block",
            new MushroomBlock(AbstractBlock.Settings.copy(Blocks.RED_MUSHROOM_BLOCK).luminance(state -> 8)), true);
    public static final Block PINK_CAVESHROOM_BLOCK = registerBlock("pink_caveshroom_block",
            new MushroomBlock(AbstractBlock.Settings.copy(Blocks.RED_MUSHROOM_BLOCK).luminance(state -> 8)), true);

    public static final Block AETHERIUM_ROOTS = registerBlock("aetherium_roots",
            new GlowLichenBlock(AbstractBlock.Settings.copy(Blocks.GLOW_LICHEN).luminance(state -> 7)), true);

    public static final Block GLOW_MOSS_CARPET = registerBlock("glow_moss_carpet",
            new CarpetBlock(AbstractBlock.Settings.create()
                    .strength(0.1f)
                    .sounds(BlockSoundGroup.MOSS_CARPET)
                    .nonOpaque() // This is important for blocks that aren't full cubes
            ), true);

    public static final Block VESPERAN_STONE = registerBlock("vesperan_stone",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE)), true);
    public static final Block VESPERAN_COBBLESTONE = registerBlock("vesperan_cobblestone",
            new Block(AbstractBlock.Settings.copy(Blocks.COBBLESTONE)), true);
    public static final Block VESPERAN_STONE_STAIRS = registerBlock("vesperan_stone_stairs",
            new StairsBlock(VESPERAN_STONE.getDefaultState(), AbstractBlock.Settings.copy(VESPERAN_STONE).requiresTool()), true);
    public static final Block VESPERAN_STONE_SLAB = registerBlock("vesperan_stone_slab",
            new SlabBlock(AbstractBlock.Settings.copy(VESPERAN_STONE).requiresTool()), true);
    public static final Block VESPERAN_STONE_WALL = registerBlock("vesperan_stone_wall",
            new WallBlock(AbstractBlock.Settings.copy(VESPERAN_STONE).requiresTool()), true);
    public static final Block VESPERAN_COBBLESTONE_STAIRS = registerBlock("vesperan_cobblestone_stairs",
            new StairsBlock(VESPERAN_COBBLESTONE.getDefaultState(), AbstractBlock.Settings.copy(VESPERAN_COBBLESTONE).requiresTool()), true);
    public static final Block VESPERAN_COBBLESTONE_SLAB = registerBlock("vesperan_cobblestone_slab",
            new SlabBlock(AbstractBlock.Settings.copy(VESPERAN_COBBLESTONE).requiresTool()), true);
    public static final Block VESPERAN_COBBLESTONE_WALL = registerBlock("vesperan_cobblestone_wall",
            new WallBlock(AbstractBlock.Settings.copy(VESPERAN_COBBLESTONE).requiresTool()), true);


    public static final Block VESPERAN_IRON_ORE = registerBlock("vesperan_iron_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(0, 0),
                    AbstractBlock.Settings.create()
                            .strength(3.0f, 3.0f)
                            .requiresTool()
            ), true);
    public static final Block VESPERAN_COAL_ORE = registerBlock("vesperan_coal_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 10),
                    AbstractBlock.Settings.copy(Blocks.COAL_ORE)
                            .requiresTool()
            ), true);
    public static final Block VESPERAN_COPPER_ORE = registerBlock("vesperan_copper_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(0, 0),
                    AbstractBlock.Settings.copy(Blocks.COPPER_ORE)
                            .requiresTool()
            ), true);
    public static final Block VESPERAN_GOLD_ORE = registerBlock("vesperan_gold_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(0, 0),
                    AbstractBlock.Settings.copy(Blocks.GOLD_ORE)
                            .requiresTool()
            ), true);
    public static final Block VESPERAN_LAPIS_ORE = registerBlock("vesperan_lapis_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(4, 12),
                    AbstractBlock.Settings.copy(Blocks.LAPIS_ORE)
                            .requiresTool()
            ), true);
    public static final Block VESPERAN_REDSTONE_ORE = registerBlock("vesperan_redstone_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(0, 0),
                    AbstractBlock.Settings.create()
                            .strength(3.5f, 3.5f)
                            .requiresTool()
            ), true);
    public static final Block VESPERAN_EMERALD_ORE = registerBlock("vesperan_emerald_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(5, 13),
                    AbstractBlock.Settings.copy(Blocks.EMERALD_ORE)
                            .requiresTool()
            ), true);
    public static final Block VESPERAN_DIAMOND_ORE = registerBlock("vesperan_diamond_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(6, 14),
                    AbstractBlock.Settings.copy(Blocks.DIAMOND_ORE)
                            .requiresTool()
            ), true);
    public static final Block AETHERIUM_ORE = registerBlock("aetherium_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(6, 14),
                    AbstractBlock.Settings.copy(Blocks.DIAMOND_ORE)
                            .requiresTool()
            ), true);

    public static final Block ECHOWAY_PORTAL = registerBlock("echoway_portal",
            new EchowayPortalBlock(AbstractBlock.Settings.create()
                    .noCollision() // Players can walk through it
                    .strength(-1.0f) // Unbreakable
                    .luminance(state -> 15) // Emits light
                    .nonOpaque()
            ), false);

    public static final Block CELENITE_TUBE = registerBlock("celenite_tube",
            new CeleniteTubeBlock(AbstractBlock.Settings.create()
                    .strength(0.5f) // Fairly easy to break
                    .luminance(state -> 15) // Emits a bright light, just under max
                    .nonOpaque()
                    .sounds(BlockSoundGroup.AMETHYST_CLUSTER) // A nice, crystalline sound
            ), true);

    public static final Block CELENITE_BLOCK = registerBlock("celenite_block",
            new Block(AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK)), true);
    public static final Block BUDDING_CELENITE_BLOCK = registerBlock("budding_celenite",
            new BuddingCeleniteBlock(AbstractBlock.Settings.copy(Blocks.BUDDING_AMETHYST).ticksRandomly()), true);
    public static final Block CELENITE_CLUSTER_BLOCK = registerBlock("celenite_cluster",
            new CeleniteClusterBlock(AbstractBlock.Settings.copy(Blocks.AMETHYST_CLUSTER).nonOpaque()), true);

    public static final Block AETHERIUM_BLOCK = registerBlock("aetherium_block",
            new Block(AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK)), true);

    public static final Block GLIMMER_MELON = registerBlock("glimmer_melon",
            new Block(AbstractBlock.Settings.copy(Blocks.MELON).luminance(state -> 7)), true);

    public static final Block GLIMMER_MELON_STEM = registerBlock("glimmer_melon_stem",
            new GlimmerMelonStemBlock(GLIMMER_MELON_KEY, ATTACHED_GLIMMER_MELON_STEM_KEY, ModItems.GLIMMER_MELON_SEEDS_KEY,
                    AbstractBlock.Settings.copy(Blocks.MELON_STEM)), false);

    public static final Block ATTACHED_GLIMMER_MELON_STEM = registerBlock("attached_glimmer_melon_stem",
            new AttachedGlimmerMelonStemBlock(GLIMMER_MELON_KEY, GLIMMER_MELON_STEM_KEY, ModItems.GLIMMER_MELON_SEEDS_KEY,
                    AbstractBlock.Settings.copy(Blocks.ATTACHED_MELON_STEM)), false);

    public static final Block AETHERIUM_GEL_BLOCK = registerBlock("aetherium_gel_block",
            new AetheriumGelBlock(AbstractBlock.Settings.copy(Blocks.SLIME_BLOCK).luminance(state -> 8).nonOpaque()), true);

    public static final Block IRON_GRATING = registerBlock("iron_grating",
            new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)), true);
    public static final Block DAMAGED_IRON_GRATING = registerBlock("damaged_iron_grating",
            new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)), true);



    private static Block registerBlock(String name, Block block, boolean registerItem) {
        if(registerItem)
        {
            registerBlockItem(name, block);
        }
        return Registry.register(Registries.BLOCK, Identifier.of(Main.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(Main.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        Main.LOGGER.debug("Registering Mod Blocks for " + Main.MOD_ID);
    }
}
