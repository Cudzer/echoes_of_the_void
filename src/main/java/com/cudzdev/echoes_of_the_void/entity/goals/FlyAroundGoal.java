package com.cudzdev.echoes_of_the_void.entity.goals;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

public class FlyAroundGoal extends Goal {

    private final PathAwareEntity mob;

    public FlyAroundGoal(PathAwareEntity mob) {
        this.mob = mob;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    @Override
    public boolean canStart() {
        // This goal can start if the mob is not currently moving.
        return mob.getNavigation().isIdle();
    }

    @Override
    public void start() {
        // When the goal starts, find a random target position and start moving towards it.
        Vec3d target = this.findRandomTarget();
        if (target != null) {
            this.mob.getNavigation().startMovingTo(target.x, target.y, target.z, 1.0D);
        }
    }

    @Override
    public boolean shouldContinue() {
        // Continue this goal as long as the mob is still navigating.
        return this.mob.getNavigation().isFollowingPath();
    }

    private Vec3d findRandomTarget() {
        // Find a random position in a 10x7x10 box around the mob.
        Vec3d randomDirection = this.mob.getRotationVec(0.0F).multiply(10.0D);
        Vec3d targetPos = this.mob.getPos().add(randomDirection);

        for(int i = 0; i < 10; ++i) {
            Vec3d randomPos = Vec3d.ofBottomCenter(this.mob.getBlockPos().add(
                    this.mob.getRandom().nextInt(21) - 10,
                    this.mob.getRandom().nextInt(7) - 3,
                    this.mob.getRandom().nextInt(21) - 10
            ));

            if (this.mob.getWorld().isAir(net.minecraft.util.math.BlockPos.ofFloored(randomPos))) {
                return randomPos;
            }
        }

        return null;
    }
}
