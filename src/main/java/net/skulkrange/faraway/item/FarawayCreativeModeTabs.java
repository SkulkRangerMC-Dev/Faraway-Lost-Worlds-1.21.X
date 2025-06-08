package net.skulkrange.faraway.item;

import net.skulkrange.faraway.FarawayLWMod;
import net.skulkrange.faraway.block.FarawayBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class FarawayCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FarawayLWMod.MOD_ID);

    public static final Supplier<CreativeModeTab> TROPIC_ITEMS_TAB = CREATIVE_MODE_TAB.register("tropic_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(FarawayItems.MANGO.get()))
                    .title(Component.translatable("creativetab.faraway.tropic_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(FarawayItems.RESIN);
                        output.accept(FarawayItems.MANGO);
                        output.accept(FarawayItems.MANGO_SEED);
                        output.accept(FarawayItems.COCONUT);
                        output.accept(FarawayItems.COCONUT_SHELL);
                        output.accept(FarawayItems.FRESH_COCONUT_SHELL);
                    }).build());

    public static final Supplier<CreativeModeTab> TROPIC_BLOCK_TAB = CREATIVE_MODE_TAB.register("tropic_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(FarawayBlocks.RESIN_BLOCK))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(FarawayLWMod.MOD_ID, "tropic_items_tab"))
                    .title(Component.translatable("creativetab.faraway.tropic_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(FarawayBlocks.RESIN_BLOCK);
                        output.accept(FarawayBlocks.RESIN_ORE);

                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}