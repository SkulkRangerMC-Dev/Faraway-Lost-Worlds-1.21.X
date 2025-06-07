package net.skulkrange.faraway.item;

import net.skulkrange.faraway.FarawayLWMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FarawayItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FarawayLWMod.MOD_ID);

    public static final DeferredItem<Item> RESIN = ITEMS.register("resin",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MANGO = ITEMS.register("mango",
            () -> new Item(new Item.Properties().food(FarawayFoodProperties.MANGO)));
    public static final DeferredItem<Item> MANGO_SEED = ITEMS.register("mango_seed",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}