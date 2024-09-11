package net.okamiz.atlasinfection.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.okamiz.atlasinfection.AtlasInfectionMod;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AtlasInfectionMod.MOD_ID);

    public static final DeferredItem<Item> INFECTED_POWDER = ITEMS.registerSimpleItem("infected_powder");

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
