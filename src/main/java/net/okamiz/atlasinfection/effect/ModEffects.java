package net.okamiz.atlasinfection.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.okamiz.atlasinfection.AtlasInfectionMod;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, AtlasInfectionMod.MOD_ID);

    public static final Holder<MobEffect> INFECTED_EFFECT = MOB_EFFECTS.register("infected",
            () -> new InfectedEffect(MobEffectCategory.HARMFUL, 10092790));

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }

}
