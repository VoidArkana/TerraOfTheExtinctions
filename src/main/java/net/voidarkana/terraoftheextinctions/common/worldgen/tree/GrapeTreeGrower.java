package net.voidarkana.terraoftheextinctions.common.worldgen.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.voidarkana.terraoftheextinctions.common.worldgen.TotEConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

public class GrapeTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
        return TotEConfiguredFeatures.OLIVE_KEY;
    }

}
