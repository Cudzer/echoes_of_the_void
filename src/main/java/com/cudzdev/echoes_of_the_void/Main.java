package com.cudzdev.echoes_of_the_void;

import com.cudzdev.echoes_of_the_void.registry.*;
import com.cudzdev.echoes_of_the_void.entity.GlimmerMothEntity;
import com.cudzdev.echoes_of_the_void.sound.ModSounds;
import com.cudzdev.echoes_of_the_void.util.ModRegistries;
import com.cudzdev.echoes_of_the_void.util.ModTags;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
    public static final String MOD_ID = "echoes_of_the_void";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Echoes of the Void...");

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModBlockEntities.registerBlockEntities();
        ModConfiguredFeatures.register();
        ModPlacedFeatures.register();
        ModRecipes.registerRecipes();
        ModDimensions.register();
        ModScreenHandlers.registerAllScreenHandlers();
        ModRegistries.registerModStuffs();

        ModItemGroup.registerItemGroups();
        ModTags.Items.registerTags();
        ModTags.Blocks.registerTags();
        ModSounds.registerSounds();
        ModLootTableModifier.modifyLootTables();

        FabricDefaultAttributeRegistry.register(ModEntities.GLIMMER_MOTH, GlimmerMothEntity.setAttributes());
        ModMessages.registerC2SPackets();
    }
}
