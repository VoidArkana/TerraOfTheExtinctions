package net.voidarkana.terraoftheextinctions.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Bleak;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Candiru;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Perch;
import net.voidarkana.terraoftheextinctions.registry.TotEEffects;
import net.voidarkana.terraoftheextinctions.registry.TotEEntities;

@Mod.EventBusSubscriber(modid = TerraOfTheExtinctions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TotEModEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event){
        event.put(TotEEntities.BLEAK.get(), Bleak.createAttributes().build());
        event.put(TotEEntities.PERCH.get(), Perch.createAttributes().build());
        event.put(TotEEntities.CANDIRU.get(), Candiru.createAttributes().build());
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        float amount = event.getAmount();
        if (entity.hasEffect(TotEEffects.CANDIRU_INFESTED.get()) && amount > entity.getHealth()){
            Level level = entity.level();
            if (level instanceof ServerLevel sLevel){

                int e = 2 * (entity.getEffect(TotEEffects.CANDIRU_INFESTED.get()).getAmplifier() + 1);

                for(int i = 0; i != e; ++i) {
                    Candiru fish = TotEEntities.CANDIRU.get().create(sLevel);
                    fish.moveTo(Vec3.atCenterOf(entity.getOnPos()));
                    sLevel.addFreshEntity(fish);
                }
            }
        }
    }

}
