package net.voidarkana.terraoftheextinctions.client.renderers;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.client.TotEModelLayers;
import net.voidarkana.terraoftheextinctions.client.models.BleakModel;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Bleak;

public class BleakRenderer extends MobRenderer<Bleak, BleakModel<Bleak>> {

    public BleakRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BleakModel<>(pContext.bakeLayer(TotEModelLayers.BLEAK_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(Bleak pEntity) {
        return new ResourceLocation(TerraOfTheExtinctions.MOD_ID,"textures/entity/bleak/bleak_"+pEntity.getVariant()
                + (pEntity.isLeader() ? "_leader" : "") + ".png");
    }
}
