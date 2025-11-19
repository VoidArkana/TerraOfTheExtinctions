package net.voidarkana.terraoftheextinctions.common.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;

import java.util.List;

public class TotEPlacedFeatures {

    public static final ResourceKey<PlacedFeature> SALT_PILLAR_PLACED_KEY = registerKey("salt_pillars");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, SALT_PILLAR_PLACED_KEY, configuredFeatures.getOrThrow(TotEConfiguredFeatures.SALT_PILLAR_RANDOM_PATCH),
                saltPillarsPlacement());

    }

    private static List<PlacementModifier> saltPillarsPlacement() {
        return List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, CountPlacement.of(3), BiomeFilter.biome());
    }

    public static void register(BootstapContext<PlacedFeature> pContext,
                                ResourceKey<PlacedFeature> pKey,
                                Holder<ConfiguredFeature<?, ?>> pConfiguredFeatures,
                                PlacementModifier... pPlacements) {
        register(pContext, pKey, pConfiguredFeatures, List.of(pPlacements));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(TerraOfTheExtinctions.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context,
                                 ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
