package net.voidarkana.terraoftheextinctions.client.renderers;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.client.TotEModelLayers;
import net.voidarkana.terraoftheextinctions.client.models.PerchModel;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Perch;

public class PerchRenderer extends MobRenderer<Perch, PerchModel<Perch>> {

    public PerchRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new PerchModel<>(pContext.bakeLayer(TotEModelLayers.PERCH_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(Perch pEntity) {
        return new ResourceLocation(TerraOfTheExtinctions.MOD_ID,"textures/entity/perch.png");
    }
}
