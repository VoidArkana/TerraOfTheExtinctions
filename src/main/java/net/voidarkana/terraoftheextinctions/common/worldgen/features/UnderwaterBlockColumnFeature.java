package net.voidarkana.terraoftheextinctions.common.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.BlockColumnFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;

public class UnderwaterBlockColumnFeature extends BlockColumnFeature {
    public UnderwaterBlockColumnFeature(Codec<BlockColumnConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BlockColumnConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        BlockPos origin = pContext.origin();
        BlockPos top = pContext.origin().above().above();
        BlockPos bottom = pContext.origin().below();

        return worldgenlevel.getBlockState(bottom).isSolid() && worldgenlevel.getBlockState(origin).is(Blocks.WATER) && worldgenlevel.getBlockState(top).isAir() && super.place(pContext);
    }
}
