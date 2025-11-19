package net.voidarkana.terraoftheextinctions.common.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.common.worldgen.features.UnderwaterBlockColumnFeature;
import net.voidarkana.terraoftheextinctions.registry.TotEBlocks;

import java.util.function.Supplier;

public class TotEConfiguredFeatures {

    public static final DeferredRegister<Feature<?>> MOD_FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, TerraOfTheExtinctions.MOD_ID);

    public static final ResourceKey<ConfiguredFeature<?, ?>> OLIVE_KEY = registerKey("olive");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRAPE_KEY = registerKey("grape_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SALT_PILLAR_RANDOM_PATCH = registerKey("salt_pillar_random_patch");

    public static final RegistryObject<Feature<BlockColumnConfiguration>> UNDERWATER_COLUMN_FEATURE =
            register_feature("underwater_column_feature", () -> new UnderwaterBlockColumnFeature(BlockColumnConfiguration.CODEC));

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

        register(context, OLIVE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder
                (BlockStateProvider.simple(TotEBlocks.OLIVE_LOG.get()),
                        new StraightTrunkPlacer(4, 2, 0),
                        BlockStateProvider.simple(TotEBlocks.OLIVE_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, GRAPE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder
                (BlockStateProvider.simple(TotEBlocks.GRAPE_LOG.get()),
                        new StraightTrunkPlacer(5, 4, 1),
                        BlockStateProvider.simple(TotEBlocks.GRAPE_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());


        register(context, SALT_PILLAR_RANDOM_PATCH, Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(50, 8 ,8,
                        PlacementUtils.inlinePlaced(UNDERWATER_COLUMN_FEATURE.get(),
                                BlockColumnConfiguration.simple(BiasedToBottomInt.of(3, 5),
                                        BlockStateProvider.simple(TotEBlocks.SALT_BLOCK.get())),
                                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                BlockPredicate.matchesBlocks(Blocks.WATER))))
                ));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(TerraOfTheExtinctions.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    public static <T extends FeatureConfiguration> RegistryObject<Feature<T>> register_feature(String name, Supplier<Feature<T>> featureSupplier) {
        return MOD_FEATURES.register(name, featureSupplier);
    }

    public static void register(IEventBus eventBus){
        MOD_FEATURES.register(eventBus);
    }
}
