package com.cudzdev.echoes_of_the_void.registry;

import com.cudzdev.echoes_of_the_void.Main;
import com.cudzdev.echoes_of_the_void.block.entity.ResonanceTableBlockEntity;
import com.cudzdev.echoes_of_the_void.block.entity.RunicCasingBlockEntity;
import com.cudzdev.echoes_of_the_void.block.entity.StellarDialBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<StellarDialBlockEntity> STELLAR_DIAL_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(Main.MOD_ID, "stellar_dial_be"),
            BlockEntityType.Builder.create(StellarDialBlockEntity::new,
                    ModBlocks.STELLAR_DIAL_PEDESTAL_BLOCK).build());

    public static final BlockEntityType<ResonanceTableBlockEntity> RESONANCE_TABLE_BLOCK_ENTITY_TYPE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(Main.MOD_ID, "resonance_table_be"),
            BlockEntityType.Builder.create(ResonanceTableBlockEntity::new,
                    ModBlocks.RESONANCE_TABLE).build());

    public static final BlockEntityType<RunicCasingBlockEntity> RUNIC_CASING_BLOCK_ENTITY_TYPE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(Main.MOD_ID, "runic_casing_table_be"),
            BlockEntityType.Builder.create(RunicCasingBlockEntity::new,
                    ModBlocks.RUNIC_CASING).build());

    public static void registerBlockEntities() {
        Main.LOGGER.debug("Registering Block Entities for " + Main.MOD_ID);
    }
}
