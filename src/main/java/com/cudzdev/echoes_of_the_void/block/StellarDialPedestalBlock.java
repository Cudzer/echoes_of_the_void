package com.cudzdev.echoes_of_the_void.block;

import com.cudzdev.echoes_of_the_void.Main;
import com.cudzdev.echoes_of_the_void.registry.ModBlockEntities;
import com.cudzdev.echoes_of_the_void.block.entity.StellarDialBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class StellarDialPedestalBlock extends BlockWithEntity{

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public StellarDialPedestalBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof StellarDialBlockEntity) {
                ((StellarDialBlockEntity) blockEntity).onBreak();
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.STELLAR_DIAL_BLOCK_ENTITY, StellarDialBlockEntity::tick);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new StellarDialBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof StellarDialBlockEntity dialEntity) {
                if (dialEntity.lazyValidate()) {
                    if (player instanceof ServerPlayerEntity serverPlayer) {
                        Identifier advancementId = Identifier.of(Main.MOD_ID, "activate_stellar_pedestal");
                        AdvancementEntry advancement = serverPlayer.server.getAdvancementLoader().get(advancementId);
                        if (advancement != null) {
                            serverPlayer.getAdvancementTracker().grantCriterion(advancement, "impossible");
                        }
                    }
                    player.openHandledScreen((NamedScreenHandlerFactory) blockEntity);
                } else {
                    player.sendMessage(Text.literal("Gate structure is incomplete or damaged."), true);
                }
            }
        }
        return ActionResult.SUCCESS;
    }
}
