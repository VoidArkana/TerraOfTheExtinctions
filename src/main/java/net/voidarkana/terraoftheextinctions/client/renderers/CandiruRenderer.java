package net.voidarkana.terraoftheextinctions.client.renderers;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.client.TotEModelLayers;
import net.voidarkana.terraoftheextinctions.client.models.CandiruModel;
import net.voidarkana.terraoftheextinctions.client.models.PerchModel;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Candiru;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Perch;

public class CandiruRenderer extends MobRenderer<Candiru, CandiruModel<Candiru>> {

    public CandiruRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CandiruModel<>(pContext.bakeLayer(TotEModelLayers.CANDIRU_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(Candiru pEntity) {
        return new ResourceLocation(TerraOfTheExtinctions.MOD_ID,"textures/entity/candiru.png");
    }
}
