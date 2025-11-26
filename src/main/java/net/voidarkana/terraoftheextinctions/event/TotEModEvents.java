package net.voidarkana.terraoftheextinctions.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.common.entity.animals.*;
import net.voidarkana.terraoftheextinctions.registry.TotEDamageTypes;
import net.voidarkana.terraoftheextinctions.registry.TotEEffects;
import net.voidarkana.terraoftheextinctions.registry.TotEEntities;

@Mod.EventBusSubscriber(modid = TerraOfTheExtinctions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TotEModEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event){
        event.put(TotEEntities.BLEAK.get(), Bleak.createAttributes().build());
        event.put(TotEEntities.PERCH.get(), Perch.createAttributes().build());
        event.put(TotEEntities.CANDIRU.get(), Candiru.createAttributes().build());
        event.put(TotEEntities.GAR.get(), AlligatorGar.createAttributes().build());
        event.put(TotEEntities.COELACANTH.get(), Coelacanth.createAttributes().build());
    }

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(TotEEffects.CANDIRU_INFESTED.get())){
            Level level = entity.level();
            if (level instanceof ServerLevel sLevel){

                int e = 2 * (entity.getEffect(TotEEffects.CANDIRU_INFESTED.get()).getAmplifier() + 1);

                for(int i = 0; i != e; ++i) {
                    Candiru fish = TotEEntities.CANDIRU.get().create(sLevel);
                    fish.moveTo(Vec3.atCenterOf(entity.getOnPos()).add(0, 1, 0));
                    sLevel.addFreshEntity(fish);
                }
            }
        }
    }

    @SubscribeEvent
    public void serverTick(TickEvent.PlayerTickEvent event){
        Player player = event.player;
        final int crushDepth = 40;

        if (player.isUnderWater() && !player.isCreative() && player.level() instanceof ServerLevel level){
            if (player.getY() <= crushDepth && TerraOfTheExtinctions.PROXY.canPlayerBeCrushed(player, level)){
                if (player.tickCount % 20 == 0){
                    player.hurt(level.damageSources().source(TotEDamageTypes.BAROTRAUMA, player), 2);
                }
            }
        }
    }

}
