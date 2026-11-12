package com.example.spearfeatures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SpearFeatures.MOD_ID)
public final class SpearFeatures {
    public static final String MOD_ID = "spearfeatures";

    public SpearFeatures() {
        SpearItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
