package com.cudzdev.echoes_of_the_void.datagen;

import com.cudzdev.echoes_of_the_void.Main;
import com.cudzdev.echoes_of_the_void.registry.ModBlocks;
import com.cudzdev.echoes_of_the_void.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.MultifaceGrowthBlock;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider{
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool architectStonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.ARCHITECT_STONE);
        BlockStateModelGenerator.BlockTexturePool vesperanStonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.VESPERAN_STONE);
        BlockStateModelGenerator.BlockTexturePool vesperanCobblestonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.VESPERAN_COBBLESTONE);
        BlockStateModelGenerator.BlockTexturePool starWillowPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.STARWILLOW_PLANKS);
        BlockStateModelGenerator.BlockTexturePool glimmerwoodPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.GLIMMERWOOD_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ARCHITECT_STONE_BRICK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.INFUSED_ARCHITECT_STONE_BRICK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_ARCHITECT_STONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GLOW_MOSS);

        architectStonePool.stairs(ModBlocks.ARCHITECT_STONE_STAIRS);
        architectStonePool.slab(ModBlocks.ARCHITECT_STONE_SLAB);
        architectStonePool.wall(ModBlocks.ARCHITECT_STONE_WALL);

        vesperanStonePool.stairs(ModBlocks.VESPERAN_STONE_STAIRS);
        vesperanStonePool.slab(ModBlocks.VESPERAN_STONE_SLAB);
        vesperanStonePool.wall(ModBlocks.VESPERAN_STONE_WALL);

        vesperanCobblestonePool.stairs(ModBlocks.VESPERAN_COBBLESTONE_STAIRS);
        vesperanCobblestonePool.slab(ModBlocks.VESPERAN_COBBLESTONE_SLAB);
        vesperanCobblestonePool.wall(ModBlocks.VESPERAN_COBBLESTONE_WALL);

        starWillowPool.stairs(ModBlocks.STARWILLOW_STAIRS);
        starWillowPool.slab(ModBlocks.STARWILLOW_SLAB);
        starWillowPool.fence(ModBlocks.STARWILLOW_FENCE);
        starWillowPool.fenceGate(ModBlocks.STARWILLOW_FENCE_GATE);

        glimmerwoodPool.stairs(ModBlocks.GLIMMERWOOD_STAIRS);
        glimmerwoodPool.slab(ModBlocks.GLIMMERWOOD_SLAB);
        glimmerwoodPool.fence(ModBlocks.GLIMMERWOOD_FENCE);
        glimmerwoodPool.fenceGate(ModBlocks.GLIMMERWOOD_FENCE_GATE);


        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VESPERAN_IRON_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VESPERAN_COAL_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VESPERAN_COPPER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VESPERAN_GOLD_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VESPERAN_LAPIS_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VESPERAN_REDSTONE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VESPERAN_EMERALD_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VESPERAN_DIAMOND_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.AETHERIUM_ORE);

        blockStateModelGenerator.registerLog(ModBlocks.GLIMMERWOOD_LOG).log(ModBlocks.GLIMMERWOOD_LOG).wood(ModBlocks.GLIMMERWOOD_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_GLIMMERWOOD_LOG).log(ModBlocks.STRIPPED_GLIMMERWOOD_LOG).wood(ModBlocks.STRIPPED_GLIMMERWOOD_WOOD);
        blockStateModelGenerator.registerSingleton(ModBlocks.GLIMMERWOOD_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerTintableCross(ModBlocks.GLIMMERWOOD_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerLog(ModBlocks.STARWILLOW_LOG).log(ModBlocks.STARWILLOW_LOG).wood(ModBlocks.STARWILLOW_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_STARWILLOW_LOG).log(ModBlocks.STRIPPED_STARWILLOW_LOG).wood(ModBlocks.STRIPPED_STARWILLOW_WOOD);
        blockStateModelGenerator.registerSingleton(ModBlocks.STARWILLOW_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerTintableCross(ModBlocks.STARWILLOW_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CELENITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.AETHERIUM_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BUDDING_CELENITE_BLOCK);

        blockStateModelGenerator.registerAmethyst(ModBlocks.CELENITE_CLUSTER_BLOCK);

        blockStateModelGenerator.registerTintableCross(ModBlocks.BLUE_CAVESHROOM, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerTintableCross(ModBlocks.GREEN_CAVESHROOM, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerTintableCross(ModBlocks.PINK_CAVESHROOM, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerMushroomBlock(ModBlocks.BLUE_CAVESHROOM_BLOCK);
        blockStateModelGenerator.registerMushroomBlock(ModBlocks.GREEN_CAVESHROOM_BLOCK);
        blockStateModelGenerator.registerMushroomBlock(ModBlocks.PINK_CAVESHROOM_BLOCK);

        registerGlowLichenStyleBlock(blockStateModelGenerator, ModBlocks.HANGING_BLUE_CAVESHROOM);
        registerGlowLichenStyleBlock(blockStateModelGenerator, ModBlocks.HANGING_GREEN_CAVESHROOM);
        registerGlowLichenStyleBlock(blockStateModelGenerator, ModBlocks.HANGING_PINK_CAVESHROOM);
        registerGlowLichenStyleBlock(blockStateModelGenerator, ModBlocks.AETHERIUM_ROOTS);

        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.RUNIC_CASING);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.STELLAR_DIAL_PEDESTAL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RESONANCE_TABLE);

        blockStateModelGenerator.registerTintableCross(ModBlocks.STARGAZER_FLOWER, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerTintableCross(ModBlocks.CRYSTAL_SPROUT, BlockStateModelGenerator.TintType.NOT_TINTED);

        TextureMap carpetTextureMap = TextureMap.wool(ModBlocks.GLOW_MOSS);
        Identifier carpetModelId = Models.CARPET.upload(ModBlocks.GLOW_MOSS_CARPET, carpetTextureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerSimpleState(ModBlocks.GLOW_MOSS_CARPET);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GLIMMER_MELON);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.AETHERIUM_GEL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.IRON_GRATING);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DAMAGED_IRON_GRATING);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        Model glyphParentModel = new Model(
                Optional.of(Identifier.of(Main.MOD_ID, "item/blank_glyph")),
                Optional.empty(),
                TextureKey.LAYER0);

        itemModelGenerator.register(ModItems.PRIME_GLYPH_BADGE, glyphParentModel);
        itemModelGenerator.register(ModItems.PRIME_GLYPH_I, glyphParentModel);
        itemModelGenerator.register(ModItems.PRIME_GLYPH_S, glyphParentModel);
        itemModelGenerator.register(ModItems.PRIME_GLYPH_T, glyphParentModel);
        itemModelGenerator.register(ModItems.PRIME_GLYPH_XX, glyphParentModel);

        itemModelGenerator.register(ModItems.STELLAR_GLYPH_8, glyphParentModel);
        itemModelGenerator.register(ModItems.STELLAR_GLYPH_O, glyphParentModel);
        itemModelGenerator.register(ModItems.STELLAR_GLYPH_R, glyphParentModel);
        itemModelGenerator.register(ModItems.STELLAR_GLYPH_W, glyphParentModel);
        itemModelGenerator.register(ModItems.STELLAR_GLYPH_SLASH, glyphParentModel);

        itemModelGenerator.register(ModItems.RESONANT_BATTERY_TIER_1, Models.GENERATED);
        itemModelGenerator.register(ModItems.RESONANT_BATTERY_TIER_2, Models.GENERATED);
        itemModelGenerator.register(ModItems.INFUSED_AMETHYST_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.INFUSED_CELENITE_CRYSTAL, Models.GENERATED);

        itemModelGenerator.register(ModItems.AETHERIUM_CRYSTAL, Models.GENERATED);

        //itemModelGenerator.register(ModBlocks.CELENITE_CLUSTER_BLOCK.asItem(), Models.GENERATED);

        itemModelGenerator.register(ModItems.CELENITE_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLIMMER_MELON_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLIMMER_MELON_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BOTTLED_AETHERIUM_GEL, Models.GENERATED);
    }

    private void registerGlowLichenStyleBlock(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        Identifier modelId = blockStateModelGenerator.createSubModel(block, "", Models.CROSS, TextureMap::cross);

        MultipartBlockStateSupplier multipart = MultipartBlockStateSupplier.create(block);

        for (Direction direction : Direction.values()) {
            multipart.with(When.create().set(MultifaceGrowthBlock.getProperty(direction), true),
                    BlockStateVariant.create()
                            .put(VariantSettings.MODEL, modelId)
                            .put(VariantSettings.Y, getYRotation(direction))
                            .put(VariantSettings.X, getXRotation(direction))
            );
        }

        blockStateModelGenerator.blockStateCollector.accept(multipart);
    }

    private VariantSettings.Rotation getXRotation(Direction direction) {
        return switch (direction) {
            case DOWN -> VariantSettings.Rotation.R180;
            case NORTH, SOUTH, WEST, EAST -> VariantSettings.Rotation.R90;
            default -> VariantSettings.Rotation.R0; // UP
        };
    }

    private VariantSettings.Rotation getYRotation(Direction direction) {
        return switch (direction) {
            case EAST -> VariantSettings.Rotation.R90;
            case SOUTH -> VariantSettings.Rotation.R180;
            case WEST -> VariantSettings.Rotation.R270;
            default -> VariantSettings.Rotation.R0; // NORTH, UP, DOWN
        };
    }
}
