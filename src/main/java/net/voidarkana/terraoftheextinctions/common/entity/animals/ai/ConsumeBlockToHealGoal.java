package net.voidarkana.terraoftheextinctions.common.entity.animals.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ConsumeBlockToHealGoal extends MoveToBlockGoal {
    private final WaterAnimal creature;
    private final TagKey<Block> block;
    private final int healAmount;

//    public void start() {
//        this.creature.level().broadcastEntityEvent(this.creature, (byte)10);
//    }

    @Override
    public boolean canUse() {
        return creature.getHealth() < creature.getMaxHealth() && this.findNearestBlock();
    }

    @Override
    public boolean canContinueToUse() {
        return creature.getHealth() < creature.getMaxHealth() && super.canContinueToUse();
    }

    public ConsumeBlockToHealGoal(WaterAnimal animal, TagKey<Block> pBlock, int pHealAmount, double pSpeedModifier) {
        super(animal, pSpeedModifier, 16, 16);
        this.creature = animal;
        this.block = pBlock;
        this.healAmount = pHealAmount;
    }

    public void tick() {
        BlockPos blockpos = this.getMoveToTarget();
        if (blockpos.closerToCenterThan(this.mob.position(), this.acceptedDistance()) || this.creature.level().getBlockState(this.creature.blockPosition()).is(block)) {

            this.reachedTarget = true;

            this.creature.setDeltaMovement(this.creature.getDeltaMovement().scale(0.25));

            if (this.creature.getNavigation().getPath() != null) {
                this.creature.getNavigation().stop();
            }

            Level level = this.creature.level();
            BlockPos blockpos2 = this.creature.level().getBlockState(this.creature.blockPosition()).is(block) ? this.creature.blockPosition() : this.blockPos;

            level.destroyBlock(blockpos2, false, this.creature);
            level.playSound(null, blockpos2, SoundEvents.GENERIC_EAT, SoundSource.NEUTRAL, 1, level.getRandom().nextFloat() * 0.4F + 0.8F);

            this.creature.heal(healAmount);


            this.nextStartTick = 10;

            --this.tryTicks;
        } else {
            this.reachedTarget = false;
            ++this.tryTicks;

            if (this.shouldRecalculatePath()) {
                this.mob.getNavigation().moveTo((double)((float)blockpos.getX()) + 0.5D, (double)blockpos.getY()-0.25D, (double)((float)blockpos.getZ()) + 0.5D, this.speedModifier);
            }

//            this.creature.getLookControl().setLookAt((double)this.blockPos.getX() + 0.5D, this.blockPos.getY(), (double)this.blockPos.getZ() + 0.5D, 10.0F, (float)this.creature.getMaxHeadXRot());
        }

    }

    @Override
    public double acceptedDistance() {
        return 0.9D;
    }

    public void stop() {
        this.nextStartTick = 10;
    }

    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos);
        return (blockstate.is(this.block));
    }

}
