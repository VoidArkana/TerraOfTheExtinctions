package net.voidarkana.terraoftheextinctions.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.terraoftheextinctions.registry.TotESounds;

public class FruityLeavesBlock extends LeavesBlock {

    public final RegistryObject<? extends Item> harvest;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_1;

    public FruityLeavesBlock(Properties pProperties, RegistryObject<? extends Item> pHarvest) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(DISTANCE, 7).setValue(PERSISTENT, Boolean.FALSE).setValue(WATERLOGGED, Boolean.valueOf(false)));
        this.harvest = pHarvest;
    }

    public ItemStack getCloneItemStack(BlockGetter pLevel, BlockPos pPos, BlockState pState) {
        return new ItemStack(harvest.get());
    }

    public boolean isRandomlyTicking(BlockState pState) {
        return pState.getValue(AGE) < 1 || super.isRandomlyTicking(pState);
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        int i = pState.getValue(AGE);
        if (producingFruit(pState) && i < 1 && pLevel.getRawBrightness(pPos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, pRandom.nextInt(5) == 0)) {
            BlockState blockstate = pState.setValue(AGE, Integer.valueOf(i + 1));
            pLevel.setBlock(pPos, blockstate, 2);
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(blockstate));
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
        }
        if (decaying(pState) && i == 1){
            int j = 1 + pLevel.random.nextInt(1);
            popResource(pLevel, pPos, new ItemStack(harvest.get(), j));
        }
        super.randomTick(pState, pLevel, pPos, pRandom);
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        int i = pState.getValue(AGE);
        boolean flag = i == 1;

        if (i > 0) {
            int j = 1 + pLevel.random.nextInt(1);
            popResource(pLevel, pPos, new ItemStack(harvest.get(), j));
            pLevel.playSound(null, pPos, TotESounds.TREE_LEAVES_PICK_HARVEST.get(), SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
            BlockState blockstate = pState.setValue(AGE, Integer.valueOf(0));
            pLevel.setBlock(pPos, blockstate, 2);
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(pPlayer, blockstate));
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else {
            return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE, DISTANCE, PERSISTENT, WATERLOGGED);
    }

    protected boolean producingFruit(BlockState pState) {
        return pState.getValue(DISTANCE) < 7;
    }

}
