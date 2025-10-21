package com.cudzdev.echoes_of_the_void.item;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import team.reborn.energy.api.base.SimpleEnergyItem;

import java.util.List;

public class ResonantBatteryItem extends Item implements SimpleEnergyItem {
    private final long CAPACITY;
    private final long MAX_TRANSFER;

    public ResonantBatteryItem(Settings settings, long capacity, long maxTransfer) {
        super(settings.maxCount(1));

        this.CAPACITY = capacity;
        this.MAX_TRANSFER = maxTransfer;
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return getStoredEnergy(stack) > 0;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return (int) Math.round(((double) getStoredEnergy(stack) / CAPACITY) * 13.0);
    }

    @Override
    public long getEnergyCapacity(ItemStack itemStack) {
        return CAPACITY;
    }

    @Override
    public void onCraft(ItemStack stack, World world) {
        if (!world.isClient) {
            // Set the stored energy to the maximum capacity.
            setStoredEnergy(stack, getEnergyCapacity(stack));
        }
        super.onCraft(stack, world);
    }

    @Override
    public long getEnergyMaxInput(ItemStack itemStack) {
        return MAX_TRANSFER;
    }

    @Override
    public long getEnergyMaxOutput(ItemStack itemStack) {
        return MAX_TRANSFER;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (!Screen.hasShiftDown()) {
            tooltip.add(Text.literal("Hold <Shift> for Details").formatted(Formatting.GRAY));
        } else {
            long currentEnergy = getStoredEnergy(stack);
            long maxEnergy = getEnergyCapacity(stack);
            tooltip.add(Text.literal("Energy: ")
                    .append(Text.literal(String.format("%,d / %,d RF", currentEnergy, maxEnergy))
                            .formatted(Formatting.AQUA)));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}
