package net.skulkrange.faraway.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class FarawayFoodProperties {
    public static final FoodProperties MANGO = new FoodProperties.Builder().nutrition(5).saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.HEAL), 0.5f).usingConvertsTo(FarawayItems.MANGO_SEED.get()).build();

    public static final FoodProperties COCONUT_SHELL = new FoodProperties.Builder().nutrition(2).saturationModifier(0)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 10), 1f).usingConvertsTo(FarawayItems.COCONUT_SHELL.get()).build();
}
