package net.voidarkana.terraoftheextinctions.event;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.client.TotEModelLayers;
import net.voidarkana.terraoftheextinctions.client.models.BleakModel;
import net.voidarkana.terraoftheextinctions.client.models.CandiruModel;
import net.voidarkana.terraoftheextinctions.client.models.GarModel;
import net.voidarkana.terraoftheextinctions.client.models.PerchModel;
import net.voidarkana.terraoftheextinctions.registry.TotEBlockEntities;

@Mod.EventBusSubscriber(modid = TerraOfTheExtinctions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
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
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(TotEBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(TotEBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
}
