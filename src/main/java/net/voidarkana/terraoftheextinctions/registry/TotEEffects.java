package net.voidarkana.terraoftheextinctions.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.common.effects.CandiruEffect;

public class TotEEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TerraOfTheExtinctions.MOD_ID);

    public static final RegistryObject<MobEffect> CANDIRU_INFESTED = MOB_EFFECTS.register("candiru_infested",
            ()-> new CandiruEffect(MobEffectCategory.HARMFUL, 0xAE2012));

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
