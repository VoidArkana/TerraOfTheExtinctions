package net.voidarkana.terraoftheextinctions.client;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.client.renderers.TotEBoatRenderer;
import net.voidarkana.terraoftheextinctions.registry.TotEEntities;

@Mod.EventBusSubscriber(modid = TerraOfTheExtinctions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent e) {
        EntityRenderers.register(TotEEntities.MOD_BOAT.get(), p_174010_ -> new TotEBoatRenderer(p_174010_, false));
        EntityRenderers.register(TotEEntities.MOD_CHEST_BOAT.get(), p_174010_ -> new TotEBoatRenderer(p_174010_, true));
    }
}
