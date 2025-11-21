package net.voidarkana.terraoftheextinctions.client.renderers;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.client.TotEModelLayers;
import net.voidarkana.terraoftheextinctions.client.models.BleakModel;
import net.voidarkana.terraoftheextinctions.client.models.GarModel;
import net.voidarkana.terraoftheextinctions.common.entity.animals.AlligatorGar;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Bleak;

public class GarRenderer extends MobRenderer<AlligatorGar, GarModel<AlligatorGar>> {

    public GarRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new GarModel<>(pContext.bakeLayer(TotEModelLayers.GAR_LAYER)), 0.35f);
    }

    @Override
    public ResourceLocation getTextureLocation(AlligatorGar pEntity) {
        return new ResourceLocation(TerraOfTheExtinctions.MOD_ID,"textures/entity/gar/alligator_gar_"
                + (pEntity.isMale() ? "male" : "female") + ".png");
    }
}
