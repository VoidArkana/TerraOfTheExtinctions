package net.voidarkana.terraoftheextinctions.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;

public class TotEModelLayers {

    public static final ModelLayerLocation OLIVE_BOAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(TerraOfTheExtinctions.MOD_ID, "boat/olive"), "main");
    public static final ModelLayerLocation OLIVE_CHEST_BOAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(TerraOfTheExtinctions.MOD_ID, "chest_boat/olive"), "main");

    private static ModelLayerLocation create(String name) {
        return new ModelLayerLocation(new ResourceLocation(TerraOfTheExtinctions.MOD_ID, name), "main");
    }
}
