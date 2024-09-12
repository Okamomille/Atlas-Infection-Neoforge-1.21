package net.okamiz.atlasinfection.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.okamiz.atlasinfection.AtlasInfectionMod;
import net.okamiz.atlasinfection.block.ModBlocks;

import java.util.function.Supplier;

public class ModItemGroup {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AtlasInfectionMod.MOD_ID);

    public static final Supplier<CreativeModeTab> ATLAS_INFECTION_TAB =
            CREATIVE_MODE_TABS.register("atlas_infection_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemgroup.atlasinfection.atlas_infection_tab"))
                    .icon(() -> new ItemStack(ModItems.INFECTED_POWDER.get()))
                    .displayItems((pParameters, output) -> {
                        output.accept(ModItems.INFECTED_POWDER);



                        output.accept(ModBlocks.PROTECTED_QUARTZ_BLOCK);
                        output.accept(ModBlocks.ATLAS_HEART);
                        output.accept(ModBlocks.LIVING_BLOCK);
                        output.accept(ModBlocks.HARD_LIVING_BLOCK);
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
