package net.voidarkana.terraoftheextinctions.common.entity.animals.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.voidarkana.terraoftheextinctions.common.blocks.FishRoeBlock;
import net.voidarkana.terraoftheextinctions.common.entity.animals.base.EggLayingFish;
import net.voidarkana.terraoftheextinctions.registry.TotEBlocks;

import java.util.function.Supplier;

public class LayRoeGoal extends MoveToBlockGoal {
    private final EggLayingFish turtle;
    private final TagKey<Block> block;
    private final Supplier<Block> roe;

    public LayRoeGoal(EggLayingFish pTurtle, double pSpeedModifier, TagKey<Block> pBlock, Supplier<Block> pRoe) {
        super(pTurtle, pSpeedModifier, 20, 20);
        this.turtle = pTurtle;
        this.block = pBlock;
        this.roe = pRoe;
    }

    public boolean canUse() {
        return this.turtle.hasEgg() && super.canUse();
    }

    public boolean canContinueToUse() {
        return super.canContinueToUse() && this.turtle.hasEgg();
    }

    public void tick() {
        super.tick();
        BlockPos blockpos = this.turtle.blockPosition();
        if (this.turtle.isInWater() && this.isReachedTarget()) {
            if (this.turtle.layEggCounter < 1) {
                this.turtle.setLayingEgg(true);
            } else if (this.turtle.layEggCounter > 20) {
                Level level = this.turtle.level();
                level.playSound(null, blockpos, SoundEvents.TURTLE_LAY_EGG, SoundSource.BLOCKS, 0.3F, 0.9F + level.random.nextFloat() * 0.2F);
                BlockPos blockpos1 = this.blockPos.above();
                BlockState blockstate = roe.get().defaultBlockState();
                level.setBlock(blockpos1, blockstate, 3);
                level.gameEvent(GameEvent.BLOCK_PLACE, blockpos1, GameEvent.Context.of(this.turtle, blockstate));
                this.turtle.setHasEgg(false);
                this.turtle.setLayingEgg(false);
                this.turtle.setInLoveTime(600);
            }

            if (this.turtle.isLayingEgg()) {
                ++this.turtle.layEggCounter;
            }
        }
    }

    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        return (pLevel.getBlockState(pPos.above()).is(Blocks.WATER)) && pLevel.getBlockState(pPos).is(block);
    }
}
