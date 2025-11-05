package net.voidarkana.terraoftheextinctions.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.voidarkana.terraoftheextinctions.registry.TotEBlockEntities;

public class TotESignBlockEntity extends SignBlockEntity {

    public TotESignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(TotEBlockEntities.MOD_SIGN.get(), pPos, pBlockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return TotEBlockEntities.MOD_SIGN.get();
    }
}
