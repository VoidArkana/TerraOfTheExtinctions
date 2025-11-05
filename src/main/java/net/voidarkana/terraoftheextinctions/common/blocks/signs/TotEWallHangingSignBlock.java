package net.voidarkana.terraoftheextinctions.common.blocks.signs;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.voidarkana.terraoftheextinctions.common.blockentity.TotEHangingSignBlockEntity;

public class TotEWallHangingSignBlock extends WallHangingSignBlock {

    public TotEWallHangingSignBlock(Properties pProperties, WoodType pType) {
        super(pProperties, pType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TotEHangingSignBlockEntity(pPos, pState);
    }
}
