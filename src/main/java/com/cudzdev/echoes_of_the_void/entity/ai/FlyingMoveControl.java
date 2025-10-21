package com.cudzdev.echoes_of_the_void.entity.ai;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.MathHelper;

public class FlyingMoveControl extends MoveControl {
    private final int maxPitchChange;
    private final boolean noGravity;

    public FlyingMoveControl(MobEntity entity, int maxPitchChange, boolean noGravity) {
        super(entity);
        this.maxPitchChange = maxPitchChange;
        this.noGravity = noGravity;
    }

    @Override
    public void tick() {
        if (this.state == State.MOVE_TO) {
            // Mark the state as waiting if we're very close to the target
            if (this.entity.getBlockPos().getSquaredDistance(targetX, targetY, targetZ) < 1.0D) {
                this.state = State.WAIT;
                return;
            }

            // Calculate the direction vector towards the target
            double dX = this.targetX - this.entity.getX();
            double dY = this.targetY - this.entity.getY();
            double dZ = this.targetZ - this.entity.getZ();
            double distance = Math.sqrt(dX * dX + dY * dY + dZ * dZ);

            // Normalize the direction vector
            dY /= distance;

            // Calculate the desired pitch (up/down rotation) and yaw (left/right rotation)
            float f = (float) (MathHelper.atan2(dZ, dX) * 57.2957763671875D) - 90.0F;
            this.entity.setYaw(this.wrapDegrees(this.entity.getYaw(), f, 90.0F));
            this.entity.bodyYaw = this.entity.getYaw();

            float speed = (float) (this.speed * this.entity.getAttributeValue(net.minecraft.entity.attribute.EntityAttributes.GENERIC_MOVEMENT_SPEED));

            // Set the entity's speed
            this.entity.setMovementSpeed(speed * MathHelper.clamp((float)distance / 10f, 0.1f, 1f));

            // Adjust the pitch, ensuring it doesn't change too quickly
            this.entity.setPitch(wrapDegrees(this.entity.getPitch(), (float) (-(MathHelper.atan2(dY, Math.sqrt(dX * dX + dZ * dZ)) * 57.2957763671875D)), (float) this.maxPitchChange));

            // Apply movement
            if (noGravity) {
                this.entity.setNoGravity(true);
            }
        } else {
            this.entity.setMovementSpeed(0.0F);
        }
    }
}
