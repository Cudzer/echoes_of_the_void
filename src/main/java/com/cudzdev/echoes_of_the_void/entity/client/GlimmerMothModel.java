package com.cudzdev.echoes_of_the_void.entity.client;

import com.cudzdev.echoes_of_the_void.Main;
import com.cudzdev.echoes_of_the_void.entity.GlimmerMothEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GlimmerMothModel extends GeoModel<GlimmerMothEntity> {
    @Override
    public Identifier getModelResource(GlimmerMothEntity animatable) {
        return Identifier.of(Main.MOD_ID, "geo/glimmer_moth.geo.json");
    }

    @Override
    public Identifier getTextureResource(GlimmerMothEntity animatable) {
        return Identifier.of(Main.MOD_ID, "textures/entity/glimmer_moth.png");
    }

    @Override
    public Identifier getAnimationResource(GlimmerMothEntity animatable) {
        return Identifier.of(Main.MOD_ID, "animations/glimmer_moth.animation.json");
    }
}
