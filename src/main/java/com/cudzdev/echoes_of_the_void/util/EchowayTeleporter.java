package com.cudzdev.echoes_of_the_void.util;

import com.cudzdev.echoes_of_the_void.Main;
import com.cudzdev.echoes_of_the_void.block.RunicCasingBlock;
import com.cudzdev.echoes_of_the_void.block.entity.StellarDialBlockEntity;
import com.cudzdev.echoes_of_the_void.item.ResonantBatteryItem;
import com.cudzdev.echoes_of_the_void.registry.ModAddresses;
import com.cudzdev.echoes_of_the_void.registry.ModBlocks;
import com.cudzdev.echoes_of_the_void.block.StellarDialPedestalBlock;
import com.cudzdev.echoes_of_the_void.registry.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Set;

public class EchowayTeleporter {

    private static final int ECHOWAY_SEARCH_RADIUS = 64;

    public static void teleportToDestination(Entity entity, EchowayNetworkState.EchowayInfo destinationInfo) {
        if (!(entity.getWorld() instanceof ServerWorld originWorld)) {
            return;
        }

        //Main.LOGGER.info("Attemping to teleport to " + destinationInfo.dimension() + " coords: " + destinationInfo.pos() + " with address " + destinationInfo.address());
        ServerWorld destinationWorld = originWorld.getServer().getWorld(destinationInfo.dimension());
        if (destinationWorld == null) {
            //Main.LOGGER.error("Teleportation failed: Destination dimension " + destinationInfo.dimension().getValue() + " not found.");
            return;
        }

        BlockPos targetPos = destinationInfo.pos();

        if (originWorld.getRegistryKey() == destinationInfo.dimension()) {
            entity.teleport(destinationWorld, targetPos.getX() + 0.5, targetPos.getY(), targetPos.getZ() + 7.5, Set.of(), entity.getYaw(), entity.getPitch());
        } else {
            BlockPos arrivalPos = findOrCreateGateAt(destinationWorld, entity.getBlockPos());
            var offset = destinationInfo.dimension() == World.OVERWORLD ? 7.5 : -7.5;
            entity.teleport(destinationWorld,
                    arrivalPos.getX() + 0.5,
                    arrivalPos.getY(),
                    arrivalPos.getZ() + offset,
                    Set.of(),
                    entity.getYaw(), entity.getPitch());
        }
    }

    private static BlockPos findOrCreateGateAt(ServerWorld world, BlockPos targetPos) {
        EchowayNetworkState networkState = EchowayNetworkState.getServerState(world);
        BlockPos closestGatePos = null;
        double minDistanceSq = -1;

        for (EchowayNetworkState.EchowayInfo info : networkState.gates.values()) {
            // Only consider gates in the correct dimension
            if (info.dimension().equals(world.getRegistryKey())) {
                Main.LOGGER.info("Checking " + info.address() + " in " + info.dimension());
                double distanceSq = info.pos().getSquaredDistance(targetPos);
                if (distanceSq < ECHOWAY_SEARCH_RADIUS * ECHOWAY_SEARCH_RADIUS) {
                    if (closestGatePos == null || distanceSq < minDistanceSq) {
                        minDistanceSq = distanceSq;
                        closestGatePos = info.pos();
                    }
                }
            }
        }

        if (closestGatePos != null) {
            return closestGatePos;
        }

        BlockPos.Mutable mutablePos = new BlockPos.Mutable(targetPos.getX(), world.getLogicalHeight(), targetPos.getZ());

        while (mutablePos.getY() > world.getBottomY()) {
            if (!world.getBlockState(mutablePos).isAir() && (world.getBlockState(mutablePos).isOf(Blocks.GRASS_BLOCK) || world.getBlockState(mutablePos).isOf(ModBlocks.GLOW_MOSS))) {
                BlockPos buildPos = mutablePos.up();

                if (buildPos.getY() < world.getBottomY() + 10) {
                    buildPos = new BlockPos(buildPos.getX(), world.getBottomY() + 10, buildPos.getZ());
                }

                buildGateFrame(world, buildPos, Direction.SOUTH);
                return buildPos;
            }
            mutablePos.move(Direction.DOWN);
        }

        BlockPos fallbackPos = new BlockPos(targetPos.getX(), 70, targetPos.getZ());
        buildGateFrame(world, fallbackPos, Direction.SOUTH);
        return fallbackPos;
    }
    public static void buildGateFrame(ServerWorld world, BlockPos pedestalPos, Direction facing) {
        BlockState stone = ModBlocks.ARCHITECT_STONE_BRICK.getDefaultState();
        BlockPos gateOrigin = pedestalPos.offset(facing.getOpposite(), 8);

        for (BlockPos pos : BlockPos.iterate(pedestalPos.add(-3, -1, -9), pedestalPos.add(3, 6, 3))) {
            if (!world.getBlockState(pos).isOf(Blocks.BEDROCK)) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }

        for (BlockPos pos : BlockPos.iterate(pedestalPos.down().add(-2, 0, -8), pedestalPos.down().add(2, 0, 2))) {
            world.setBlockState(pos, stone);
        }

        Direction right = facing.rotateYClockwise();
        Direction left = facing.rotateYCounterclockwise();
        Direction forward = facing;

        world.setBlockState(gateOrigin.offset(left), stone);
        world.setBlockState(gateOrigin, stone);
        world.setBlockState(gateOrigin.offset(right), stone);

        world.setBlockState(gateOrigin.offset(left, 2).up(1), ModBlocks.RUNIC_CASING.getDefaultState().with(RunicCasingBlock.FACING, forward));
        world.setBlockState(gateOrigin.offset(right, 2).up(1), ModBlocks.RUNIC_CASING.getDefaultState().with(RunicCasingBlock.FACING, forward));

        world.setBlockState(gateOrigin.offset(left, 2).up(2), stone);
        world.setBlockState(gateOrigin.offset(right, 2).up(2), stone);

        world.setBlockState(gateOrigin.offset(left, 2).up(3), ModBlocks.RUNIC_CASING.getDefaultState().with(RunicCasingBlock.FACING, forward));
        world.setBlockState(gateOrigin.offset(right, 2).up(3), ModBlocks.RUNIC_CASING.getDefaultState().with(RunicCasingBlock.FACING, forward));

        world.setBlockState(gateOrigin.offset(left).up(4), stone);
        world.setBlockState(gateOrigin.up(4), ModBlocks.RUNIC_CASING.getDefaultState().with(RunicCasingBlock.FACING, forward));
        world.setBlockState(gateOrigin.offset(right).up(4), stone);

        world.setBlockState(pedestalPos, ModBlocks.STELLAR_DIAL_PEDESTAL_BLOCK.getDefaultState().with(StellarDialPedestalBlock.FACING, facing));

        var blockEntity = world.getBlockEntity(pedestalPos);
        if (blockEntity instanceof StellarDialBlockEntity dialEntity) {
            ItemStack dialStack = new ItemStack(ModItems.RESONANT_BATTERY_TIER_1);
            if (dialStack.getItem() instanceof ResonantBatteryItem dialItem) {
                dialItem.setStoredEnergy(dialStack, dialItem.getEnergyCapacity(dialStack));
            }

            dialEntity.setStack(5, dialStack);
            for (int i = 0; i < 5; i++) {
                dialEntity.setStack(i, ModAddresses.OVERWORLD_ADDRESS.get(i).copy());
            }
        }
    }

    private static BlockPos rotatePos(BlockPos origin, BlockPos relative, Direction.Axis axis) {
        if (axis == Direction.Axis.X) return origin.add(relative.getX(), relative.getY(), relative.getZ());
        else return origin.add(relative.getZ(), relative.getY(), -relative.getX());
    }

    public static void activatePortalBlocks(ServerWorld world, BlockPos pedestalPos, Direction facing) {
        Direction.Axis axis = (facing.getAxis() == Direction.Axis.Z) ? Direction.Axis.X : Direction.Axis.Z;
        BlockState portalState = ModBlocks.ECHOWAY_PORTAL.getDefaultState().with(Properties.HORIZONTAL_AXIS, axis);

        BlockPos gateOrigin = pedestalPos.offset(facing.getOpposite(), 8);

        for (int yOffset = 1; yOffset <= 3; yOffset++) {
            for (int xOffset = -1; xOffset <= 1; xOffset++) {
                BlockPos targetPos;
                if (axis == Direction.Axis.X) {
                    targetPos = gateOrigin.add(xOffset, yOffset, 0);
                } else {
                    targetPos = gateOrigin.add(0, yOffset, xOffset);
                }
                world.setBlockState(targetPos, portalState);
            }
        }

    }

    public static void deactivatePortalBlocks(ServerWorld world, BlockPos pedestalPos, Direction facing) {
        BlockPos gateOrigin = pedestalPos.offset(facing.getOpposite(), 8);
        Direction.Axis axis = (facing.getAxis() == Direction.Axis.Z) ? Direction.Axis.X : Direction.Axis.Z;

        for (int yOffset = 1; yOffset <= 3; yOffset++) {
            for (int xOffset = -1; xOffset <= 1; xOffset++) {
                BlockPos targetPos;
                if (axis == Direction.Axis.X) {
                    targetPos = gateOrigin.add(xOffset, yOffset, 0);
                } else {
                    targetPos = gateOrigin.add(0, yOffset, xOffset);
                }
                world.setBlockState(targetPos, Blocks.AIR.getDefaultState());
            }
        }
    }
}
