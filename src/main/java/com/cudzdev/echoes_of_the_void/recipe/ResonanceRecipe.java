package com.cudzdev.echoes_of_the_void.recipe;

import com.cudzdev.echoes_of_the_void.registry.ModRecipes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record ResonanceRecipe(Ingredient crystalItem, Ingredient redstoneInput, ItemStack output) implements Recipe<ResonanceRecipeInput> {

    @Override
    public DefaultedList<Ingredient> getIngredients()
    {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.crystalItem);
        list.add(this.redstoneInput);
        return list;
    }



    @Override
    public boolean matches(ResonanceRecipeInput input, World world) {
        if(world.isClient()){
            return false;
        }

        return crystalItem.test(input.getStackInSlot(0)) && redstoneInput.test(input.getStackInSlot(1));
    }

    @Override
    public ItemStack craft(ResonanceRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.RESONANCE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.RESONANCE_TYPE;
    }

    public static class Serializer implements RecipeSerializer<ResonanceRecipe>{

        public static final MapCodec<ResonanceRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("crystalIngredient").forGetter(ResonanceRecipe::crystalItem),
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("redstoneIngredient").forGetter(ResonanceRecipe::redstoneInput),
                ItemStack.CODEC.fieldOf("result").forGetter(ResonanceRecipe::output)
        ).apply(inst, ResonanceRecipe::new));

        public static final PacketCodec<RegistryByteBuf, ResonanceRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, ResonanceRecipe::crystalItem,
                        Ingredient.PACKET_CODEC, ResonanceRecipe::redstoneInput,
                        ItemStack.PACKET_CODEC, ResonanceRecipe::output,
                        ResonanceRecipe::new);

        @Override
        public MapCodec<ResonanceRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, ResonanceRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
