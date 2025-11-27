package net.voidarkana.terraoftheextinctions.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.client.TotEModelLayers;
import net.voidarkana.terraoftheextinctions.client.models.*;
import net.voidarkana.terraoftheextinctions.registry.TotEBlockEntities;

@Mod.EventBusSubscriber(modid = TerraOfTheExtinctions.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TotEEventBusClientEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TotEModelLayers.OLIVE_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(TotEModelLayers.OLIVE_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(TotEModelLayers.GRAPE_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(TotEModelLayers.GRAPE_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(TotEModelLayers.BLEAK_LAYER, BleakModel::createBodyLayer);
        event.registerLayerDefinition(TotEModelLayers.PERCH_LAYER, PerchModel::createBodyLayer);
        event.registerLayerDefinition(TotEModelLayers.CANDIRU_LAYER, CandiruModel::createBodyLayer);
        event.registerLayerDefinition(TotEModelLayers.GAR_LAYER, GarModel::createBodyLayer);
        event.registerLayerDefinition(TotEModelLayers.COELACANTH_LAYER, CoelacanthModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(TotEBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(TotEBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        Player player = Minecraft.getInstance().player;
        int prevTicks;
        int crushDepth = 40;

        if (player != null){
            if (player.isUnderWater()) {

                if (TerraOfTheExtinctions.PROXY.getTicksUnderwater() < TerraOfTheExtinctions.PROXY.getMaxTicksUnderwater() &&
                        TerraOfTheExtinctions.PROXY.getTicksUnderwater() > 0){

                    prevTicks = TerraOfTheExtinctions.PROXY.getTicksUnderwater();

                    if (player.getY() <= crushDepth && TerraOfTheExtinctions.PROXY.canPlayerBeCrushed(player, player.level())) {
                        if (prevTicks<TerraOfTheExtinctions.PROXY.getMaxTicksUnderwater())
                            TerraOfTheExtinctions.PROXY.setTicksUnderwater(prevTicks+1);
                    }else {
                        TerraOfTheExtinctions.PROXY.setTicksUnderwater(prevTicks-1);
                    }

                }
            }else if (TerraOfTheExtinctions.PROXY.getTicksUnderwater() > 0){
                prevTicks = TerraOfTheExtinctions.PROXY.getTicksUnderwater();

                TerraOfTheExtinctions.PROXY.setTicksUnderwater(prevTicks-1);
            }
        }

    }
}
