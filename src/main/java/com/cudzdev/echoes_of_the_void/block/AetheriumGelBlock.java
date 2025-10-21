package com.cudzdev.echoes_of_the_void.block;

import com.cudzdev.echoes_of_the_void.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AetheriumGelBlock extends Block {
    public AetheriumGelBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(Hand.MAIN_HAND);
        if (itemStack.isOf(Items.GLASS_BOTTLE)) {
            if (!world.isClient) {
                // Consume the glass bottle
                itemStack.decrement(1);
                // Give the player the gel bottle
                player.getInventory().offerOrDrop(new ItemStack(ModItems.BOTTLED_AETHERIUM_GEL));
                // Consume the block
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
                // Play a sound effect
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}
