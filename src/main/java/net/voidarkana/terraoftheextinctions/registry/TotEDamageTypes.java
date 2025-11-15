package net.voidarkana.terraoftheextinctions.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;

public class TotEDamageTypes {
    public static final ResourceKey<DamageType> CANDIRU_INFESTATION = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(TerraOfTheExtinctions.MOD_ID, "candiru_infestation"));
}
