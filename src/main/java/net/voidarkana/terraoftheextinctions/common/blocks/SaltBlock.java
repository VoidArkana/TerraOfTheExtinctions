package net.voidarkana.terraoftheextinctions.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.voidarkana.terraoftheextinctions.registry.TotEBlocks;

public class SaltBlock extends Block {

    public static final int GROWTH_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();

    public SaltBlock(Properties pProperties) {
        super(pProperties);
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (this.isTouchingWater(pLevel, pPos) || this.isBaseTouchingWater(pLevel, pPos)){

            if (pRandom.nextInt(GROWTH_CHANCE) == 0) {
                Direction direction = DIRECTIONS[pRandom.nextInt(DIRECTIONS.length)];
                BlockPos blockpos = pPos.relative(direction);
                BlockState blockstate = pLevel.getBlockState(blockpos);
                Block block = null;
                if (canClusterGrowAtState(blockstate)) {
                    block = TotEBlocks.SMALL_SALT_BUD.get();
                } else if (blockstate.is(TotEBlocks.SMALL_SALT_BUD.get()) && blockstate.getValue(SaltClusterBlock.FACING) == direction) {
                    block = TotEBlocks.MEDIUM_SALT_BUD.get();
                } else if (blockstate.is(TotEBlocks.MEDIUM_SALT_BUD.get()) && blockstate.getValue(SaltClusterBlock.FACING) == direction) {
                    block = TotEBlocks.LARGE_SALT_BUD.get();
                } else if (blockstate.is(TotEBlocks.LARGE_SALT_BUD.get()) && blockstate.getValue(SaltClusterBlock.FACING) == direction) {
                    block = TotEBlocks.SALT_CRYSTAL.get();
                }

                if (block != null) {
                    BlockState blockstate1 = block.defaultBlockState().setValue(SaltClusterBlock.FACING, direction).setValue(SaltClusterBlock.WATERLOGGED, Boolean.valueOf(blockstate.getFluidState().getType() == Fluids.WATER));
                    pLevel.setBlockAndUpdate(blockpos, blockstate1);
                }
            }

        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    public boolean isTouchingWater(ServerLevel pLevel, BlockPos pPos){

        for (int x = -1; x < 1; x++){
            if (x != 0 && pLevel.getBlockState(pPos.relative(Direction.Axis.X, x)).is(Blocks.WATER))
                    return true;
        }

        for (int y = -1; y < 1; y++){
            if (y != 0 && pLevel.getBlockState(pPos.relative(Direction.Axis.Y, y)).is(Blocks.WATER))
                    return true;
        }

        for (int z = -1; z < 1; z++){
            if (z != 0 && pLevel.getBlockState(pPos.relative(Direction.Axis.Z, z)).is(Blocks.WATER))
                return true;
        }

        return false;
    }

    public boolean isBaseTouchingWater(ServerLevel pLevel, BlockPos pPos){

        for (int y = -1; y >= -5; y--){
            if (!pLevel.getBlockState(pPos.relative(Direction.Axis.Y, y)).is(TotEBlocks.SALT_BLOCK.get()))
                return false;
            else if (isTouchingWater(pLevel, pPos.relative(Direction.Axis.Y, y)))
                return true;
        }

        return false;
    }

    public static boolean canClusterGrowAtState(BlockState pState) {
        return pState.isAir() || pState.is(Blocks.WATER) && pState.getFluidState().getAmount() == 8;
    }
}
