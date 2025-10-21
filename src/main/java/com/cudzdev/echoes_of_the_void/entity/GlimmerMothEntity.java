package com.cudzdev.echoes_of_the_void.entity;

import com.cudzdev.echoes_of_the_void.entity.ai.FlyingMoveControl;
import com.cudzdev.echoes_of_the_void.entity.ai.FlyingPathNavigation;
import com.cudzdev.echoes_of_the_void.entity.goals.FlyAroundGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class GlimmerMothEntity extends PathAwareEntity implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public GlimmerMothEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        // We will make it fly
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.navigation = new FlyingPathNavigation(this, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return PathAwareEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0D)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.6f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0D);
    }

    @Override
    protected void initGoals() {
        // AI goals have priorities, lower numbers are higher priority
        this.goalSelector.add(1, new FlyAroundGoal(this)); // Custom simple flying goal
        // You could add more goals here, like LookAtEntityGoal or TemptGoal
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.isLogicalSideForUpdatingMovement()) {
            if (this.isTouchingWater()) {
                this.updateVelocity(0.02F, movementInput);
                this.move(net.minecraft.entity.MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.8F));
            } else if (this.isInLava()) {
                this.updateVelocity(0.02F, movementInput);
                this.move(net.minecraft.entity.MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.5F));
            } else {
                this.updateVelocity(this.getMovementSpeed(), movementInput);
                this.move(net.minecraft.entity.MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.91F));
            }
        }
        this.updateLimbs(false);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        // The AnimationController is the heart of the animation system.
        // It needs a name and a predicate that tells it when to play which animation.
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    // The predicate is called every tick to determine the animation state.
    private <E extends GeoEntity> PlayState predicate(AnimationState<E> event) {
        // For our simple moth, it's always flying. So we just play the fly animation.
        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.glimmer_moth.fly"));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
