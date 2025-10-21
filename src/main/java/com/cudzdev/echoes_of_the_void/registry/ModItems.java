package com.cudzdev.echoes_of_the_void.registry;

import com.cudzdev.echoes_of_the_void.Main;
import com.cudzdev.echoes_of_the_void.item.BottledAetheriumGelItem;
import com.cudzdev.echoes_of_the_void.item.GlimmerMelonSliceItem;
import com.cudzdev.echoes_of_the_void.item.ResonantBatteryItem;
import com.cudzdev.echoes_of_the_void.item.ResonatorItem;
import net.minecraft.block.Blocks;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {

    public static final RegistryKey<Item> GLIMMER_MELON_SEEDS_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "glimmer_melon_seeds"));

    public static final Item INFUSED_AMETHYST_CRYSTAL = registerItem("infused_amethyst_crystal",
            new Item(new Item.Settings()
                    .rarity(Rarity.COMMON)));
    public static final Item INFUSED_CELENITE_CRYSTAL = registerItem("infused_celenite_crystal",
            new Item(new Item.Settings()
                    .rarity(Rarity.COMMON)));
    public static final Item CELENITE_CRYSTAL = registerItem("celenite_crystal",
            new Item(new Item.Settings()
                    .rarity(Rarity.COMMON)));
    public static final Item RESONANT_BATTERY_TIER_1 = registerItem("resonant_battery_tier_1",
            new ResonantBatteryItem(new Item.Settings()
                    .maxCount(1)
                    .fireproof()
                    .rarity(Rarity.EPIC), 20000, 1000));
    public static final Item RESONANT_BATTERY_TIER_2 = registerItem("resonant_battery_tier_2",
            new ResonantBatteryItem(new Item.Settings()
                    .maxCount(1)
                    .fireproof()
                    .rarity(Rarity.EPIC), 50000, 5000));
    public static final Item BLANK_GLYPH = registerItem("blank_glyph",
            new Item(new Item.Settings()
                    .rarity(Rarity.COMMON)));

    //PRIME GLYPH REGISTRATION
    public static final Item PRIME_GLYPH_XX = registerItem("prime_glyph_xx",
            new ResonatorItem(new Item.Settings()
                    .rarity(Rarity.RARE), "prime_xx"));
    public static final Item PRIME_GLYPH_T = registerItem("prime_glyph_t",
            new ResonatorItem(new Item.Settings()
                    .rarity(Rarity.RARE), "prime_t"));
    public static final Item PRIME_GLYPH_S = registerItem("prime_glyph_s",
            new ResonatorItem(new Item.Settings()
                    .rarity(Rarity.RARE), "prime_s"));
    public static final Item PRIME_GLYPH_I = registerItem("prime_glyph_i",
            new ResonatorItem(new Item.Settings()
                    .rarity(Rarity.RARE), "prime_i"));
    public static final Item PRIME_GLYPH_BADGE = registerItem("prime_glyph_badge",
            new ResonatorItem(new Item.Settings()
                    .rarity(Rarity.RARE), "prime_badge"));

    //STELLAR GLYPH REGISTRATION
    public static final Item STELLAR_GLYPH_O = registerItem("stellar_glyph_o",
            new ResonatorItem(new Item.Settings()
                    .rarity(Rarity.UNCOMMON), "stellar_o"));
    public static final Item STELLAR_GLYPH_SLASH = registerItem("stellar_glyph_slash",
            new ResonatorItem(new Item.Settings()
                    .rarity(Rarity.UNCOMMON), "stellar_slash"));
    public static final Item STELLAR_GLYPH_R = registerItem("stellar_glyph_r",
            new ResonatorItem(new Item.Settings()
                    .rarity(Rarity.UNCOMMON), "stellar_r"));
    public static final Item STELLAR_GLYPH_W = registerItem("stellar_glyph_w",
            new ResonatorItem(new Item.Settings()
                    .rarity(Rarity.UNCOMMON), "stellar_w"));
    public static final Item STELLAR_GLYPH_8 = registerItem("stellar_glyph_8",
            new ResonatorItem(new Item.Settings()
                    .rarity(Rarity.UNCOMMON), "stellar_8"));

    public static final Item AETHERIUM_CRYSTAL = registerItem("aetherium_crystal",
            new Item(new Item.Settings()
                    .rarity(Rarity.COMMON)));

    public static final Item GLIMMER_MELON_SLICE = registerItem("glimmer_melon_slice",
            new GlimmerMelonSliceItem(new Item.Settings()));

    public static final Item GLIMMER_MELON_SEEDS = registerItem("glimmer_melon_seeds",
            new AliasedBlockItem(ModBlocks.GLIMMER_MELON_STEM, new Item.Settings()));

    public static final Item BOTTLED_AETHERIUM_GEL = registerItem("bottled_aetherium_gel",
            new BottledAetheriumGelItem(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Main.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Main.LOGGER.debug("Registering Mod Items for " + Main.MOD_ID);
    }
}
