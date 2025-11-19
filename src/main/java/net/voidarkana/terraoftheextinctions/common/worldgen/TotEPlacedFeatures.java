package net.voidarkana.terraoftheextinctions.common.worldgen;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;

import java.util.List;

public class TotEPlacedFeatures {

    public static final ResourceKey<PlacedFeature> SALT_PILLAR_PLACED_KEY = registerKey("salt_pillars");
    public static final ResourceKey<PlacedFeature> OLIVE_TREE_PLACED_KEY = registerKey("olive_tree");
    public static final ResourceKey<PlacedFeature> GRAPE_TREE_PLACED_KEY = registerKey("grape_tree");

    private static final PlacementModifier TREE_THRESHOLD = SurfaceWaterDepthFilter.forMaxDepth(0);

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, SALT_PILLAR_PLACED_KEY, configuredFeatures.getOrThrow(TotEConfiguredFeatures.SALT_PILLAR_RANDOM_PATCH),
                saltPillarsPlacement());

        register(context, OLIVE_TREE_PLACED_KEY, configuredFeatures.getOrThrow(TotEConfiguredFeatures.OLIVE_KEY),
                treePlacement(RarityFilter.onAverageOnceEvery(100)));
        register(context, GRAPE_TREE_PLACED_KEY, configuredFeatures.getOrThrow(TotEConfiguredFeatures.GRAPE_KEY),
                treePlacement(RarityFilter.onAverageOnceEvery(100)));
    }

    private static List<PlacementModifier> saltPillarsPlacement() {
        return List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, CountPlacement.of(3), BiomeFilter.biome());
    }

    public static List<PlacementModifier> treePlacement(PlacementModifier pPlacement, Block pSaplingBlock) {
        return treePlacementBase(pPlacement).add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(pSaplingBlock.defaultBlockState(), BlockPos.ZERO))).build();
    }

    public static List<PlacementModifier> treePlacement(PlacementModifier pPlacement) {
        return treePlacementBase(pPlacement).build();
    }

    private static ImmutableList.Builder<PlacementModifier> treePlacementBase(PlacementModifier pPlacement) {
        return ImmutableList.<PlacementModifier>builder().add(pPlacement).add(InSquarePlacement.spread()).add(TREE_THRESHOLD).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR).add(BiomeFilter.biome());
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
