package com.cudzdev.echoes_of_the_void.entity.ai;

import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class FlyingPathNavigation extends BirdNavigation {
    public FlyingPathNavigation(MobEntity mobEntity, World world) {
        super(mobEntity, world);
    }

    // Overriding this allows the moth to fly through air blocks as if they are walkable paths.
    @Override
    protected boolean isAtValidPosition() {
        return true;
    }
}
