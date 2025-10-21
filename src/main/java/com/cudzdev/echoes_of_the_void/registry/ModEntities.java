package com.cudzdev.echoes_of_the_void.registry;

import com.cudzdev.echoes_of_the_void.Main;
import com.cudzdev.echoes_of_the_void.entity.GlimmerMothEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<GlimmerMothEntity> GLIMMER_MOTH = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(Main.MOD_ID, "glimmer_moth"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GlimmerMothEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 0.8f)).build());
}
