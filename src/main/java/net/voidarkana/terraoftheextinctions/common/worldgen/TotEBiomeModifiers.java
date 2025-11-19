package net.voidarkana.terraoftheextinctions.common.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.registry.TotETags;

public class TotEBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_SALT_PILLARS = registerKey("add_salt_pillars");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_SALT_PILLARS, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(TotETags.Biomes.IS_SALT_PILLAR_BIOME),
                HolderSet.direct(placedFeatures.getOrThrow(TotEPlacedFeatures.SALT_PILLAR_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(TerraOfTheExtinctions.MOD_ID, name));
    }
}
