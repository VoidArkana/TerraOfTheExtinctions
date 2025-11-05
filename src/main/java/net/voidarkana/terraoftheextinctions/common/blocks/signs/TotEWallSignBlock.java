package net.voidarkana.terraoftheextinctions.common.blocks.signs;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.voidarkana.terraoftheextinctions.common.blockentity.TotESignBlockEntity;

public class TotEWallSignBlock extends WallSignBlock {

    public TotEWallSignBlock(Properties pProperties, WoodType pType) {
        super(pProperties, pType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TotESignBlockEntity(pPos, pState);
    }
}
