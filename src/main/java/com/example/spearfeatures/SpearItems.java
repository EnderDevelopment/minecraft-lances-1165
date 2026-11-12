package com.example.spearfeatures;

import com.example.spearfeatures.item.SpearItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(modid = SpearFeatures.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class SpearItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpearFeatures.MOD_ID);
    private static final Item.Properties SPEAR_PROPERTIES = new Item.Properties().group(ItemGroup.COMBAT);

    public static final RegistryObject<Item> WOODEN_SPEAR = register("wooden_spear", effects(new EffectInstance(Effects.WEAKNESS, 40, 0)));
    public static final RegistryObject<Item> STONE_SPEAR = register("stone_spear", effects(new EffectInstance(Effects.SPEED, 40, 0)));
    public static final RegistryObject<Item> IRON_SPEAR = register("iron_spear", effects(new EffectInstance(Effects.RESISTANCE, 40, 0)));
    public static final RegistryObject<Item> DIAMOND_SPEAR = register("diamond_spear", effects(new EffectInstance(Effects.STRENGTH, 40, 0)));
    public static final RegistryObject<Item> NETHERITE_SPEAR = register("netherite_spear", effects(
            new EffectInstance(Effects.WEAKNESS, 40, 3),
            new EffectInstance(Effects.SPEED, 40, 3),
            new EffectInstance(Effects.RESISTANCE, 40, 3),
            new EffectInstance(Effects.STRENGTH, 40, 3)));

    private SpearItems() {
    }

    private static RegistryObject<Item> register(String name, List<EffectInstance> effects) {
        return ITEMS.register(name, () -> new SpearItem(SPEAR_PROPERTIES.maxStackSize(1).maxDamage(250), effects));
    }

    private static List<EffectInstance> effects(EffectInstance... effects) {
        return Collections.unmodifiableList(Arrays.asList(effects));
    }
}
