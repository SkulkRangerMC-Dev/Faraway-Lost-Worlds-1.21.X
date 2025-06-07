package net.skulkrange.faraway.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class FarawayFoodProperties {
    public static final FoodProperties MANGO = new FoodProperties.Builder().nutrition(5).saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.HEAL), 0.5f).usingConvertsTo(FarawayItems.MANGO_SEED).build();
}
