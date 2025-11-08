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

    public void start() {
        this.creature.level().broadcastEntityEvent(this.creature, (byte)10);
    }

    @Override
    public boolean canUse() {
        return creature.getHealth() < creature.getMaxHealth() && shouldPanic();
    }

    public ConsumeBlockToHealGoal(WaterAnimal animal, TagKey<Block> pBlock) {
        super(animal, 1.25F, 16);
        this.creature = animal;
        this.block = pBlock;
    }

    protected boolean shouldPanic() {
        return this.mob.getLastHurtByMob() != null || this.mob.isFreezing() || this.mob.isOnFire();
    }

    public void tick() {
        super.tick();
        this.creature.getLookControl().setLookAt((double)this.blockPos.getX() + 0.5D, (double)(this.blockPos.getY() + 1), (double)this.blockPos.getZ() + 0.5D, 10.0F, (float)this.creature.getMaxHeadXRot());
        if (this.isReachedTarget()) {

            if (this.creature.getNavigation().getPath() != null) {
                this.creature.getNavigation().stop();
            }

            Level level = this.creature.level();
            BlockPos blockpos = this.blockPos;

            level.destroyBlock(blockpos, false, this.creature);
            level.playSound(null, blockpos, SoundEvents.GENERIC_EAT, SoundSource.NEUTRAL, 1, level.getRandom().nextFloat() * 0.4F + 0.8F);

            this.nextStartTick = 10;
        }
    }

    public void stop() {
        this.nextStartTick = 10;
    }

    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos);
        return (blockstate.is(this.block)) && (pLevel.isEmptyBlock(pPos.above()) || pLevel.isWaterAt(pPos.above()));
    }

}
