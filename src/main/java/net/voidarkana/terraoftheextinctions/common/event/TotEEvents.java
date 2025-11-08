package net.voidarkana.terraoftheextinctions.common.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Bleak;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Perch;
import net.voidarkana.terraoftheextinctions.registry.TotEEntities;

@Mod.EventBusSubscriber(modid = TerraOfTheExtinctions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TotEEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event){

        event.put(TotEEntities.BLEAK.get(), Bleak.createAttributes().build());
        event.put(TotEEntities.PERCH.get(), Perch.createAttributes().build());

    }

}
