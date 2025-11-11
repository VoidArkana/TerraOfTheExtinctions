package net.voidarkana.terraoftheextinctions.common.entity.animals;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.dimension.DimensionType;
import net.voidarkana.terraoftheextinctions.common.entity.animals.ai.AbstractTotEFish;
import net.voidarkana.terraoftheextinctions.common.entity.animals.ai.AnimatedAttackGoal;
import net.voidarkana.terraoftheextinctions.common.entity.animals.base.IAnimatedAttacker;
import net.voidarkana.terraoftheextinctions.registry.TotEEffects;

public class Candiru extends AbstractTotEFish implements IAnimatedAttacker {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState getIntoBodyAnimationState = new AnimationState();
    public int attackAnimationTimeout;
    public int goingIntoBodyAnimationTimeout;
    public int prevGoingIntoBodyTicks;

    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(Candiru.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> GOING_INTO_BODY_TICKS = SynchedEntityData.defineId(Candiru.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_GOING_INTO_BODY = SynchedEntityData.defineId(Candiru.class, EntityDataSerializers.BOOLEAN);

    public Candiru(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 40));
        this.goalSelector.addGoal(2, new CandiruAttackGoal(this, 1.25, true, 10, 10));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.8F)
                .add(Attributes.ATTACK_DAMAGE, 3);
    }

    public boolean doHurtTarget(Entity pEntity) {
        boolean flag = pEntity.hurt(this.damageSources().mobAttack(this),
                (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.doEnchantDamageEffects(this, pEntity);
        }

        return flag;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_ATTACKING, false);
        this.entityData.define(IS_GOING_INTO_BODY, false);
        this.entityData.define(GOING_INTO_BODY_TICKS, 0);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("IsAttacking", this.isAttacking());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setAttacking(pCompound.getBoolean("IsAttacking"));
    }

    @Override
    public boolean isAttacking() {
        return this.entityData.get(IS_ATTACKING);
    }

    @Override
    public void setAttacking(boolean attacking) {
        this.entityData.set(IS_ATTACKING, attacking);
    }

    public boolean isGoingIntoBody() {
        return this.entityData.get(IS_GOING_INTO_BODY);
    }

    public void setGoingIntoBody(boolean goingIntoBody) {
        this.entityData.set(IS_GOING_INTO_BODY, goingIntoBody);
    }

    public int getGoingIntoBodyTicks() {
        return this.entityData.get(GOING_INTO_BODY_TICKS);
    }

    public void setGoingIntoBodyTicks(int goingIntoBodyTicks) {
        this.entityData.set(GOING_INTO_BODY_TICKS, goingIntoBodyTicks);
    }

    @Override
    public int attackAnimationTimeout() {
        return this.attackAnimationTimeout;
    }

    @Override
    public void setAttackAnimationTimeout(int attackAnimationTimeout) {
        this.attackAnimationTimeout = attackAnimationTimeout;
    }

    @Override
    public void tick() {
        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }

        super.tick();

        if (this.isGoingIntoBody()){
            if (this.getGoingIntoBodyTicks() <= 0)
                this.discard();
            else {
                this.prevGoingIntoBodyTicks = this.getGoingIntoBodyTicks();
                this.setGoingIntoBodyTicks(prevGoingIntoBodyTicks--);
            }
        }

    }

    private void setupAnimationStates() {
        this.idleAnimationState.animateWhen(this.isAlive(), this.tickCount);

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 20;
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        if (this.isGoingIntoBody() && goingIntoBodyAnimationTimeout <= 0){
            goingIntoBodyAnimationTimeout = 20;
            getIntoBodyAnimationState.start(this.tickCount);
        } else {
            --this.goingIntoBodyAnimationTimeout;
        }
    }

    public void goIntoBody(){
        this.setGoingIntoBody(true);
        this.setGoingIntoBodyTicks(10);
    }

    public void customServerAiStep() {
        if (this.getMoveControl().hasWanted()) {
            double d0 = this.getMoveControl().getSpeedModifier();
            this.setPose(Pose.STANDING);
            this.setSprinting(d0 > 1D);
        } else {
            this.setPose(Pose.STANDING);
            this.setSprinting(false);
        }
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return null;
    }

    @Override
    public boolean canBeBucketed() {
        return false;
    }

    static class CandiruAttackGoal extends AnimatedAttackGoal{
        final Candiru candiru;

        public CandiruAttackGoal(Candiru pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen, int pAttackDelay, int pTicksUntilNextAttack) {
            super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen, pAttackDelay, pTicksUntilNextAttack);
            candiru = pMob;
        }

        @Override
        public void performAttack(LivingEntity pEnemy) {
            super.performAttack(pEnemy);

            if (candiru.getRandom().nextInt(10)==0){
                candiru.goIntoBody();
                pEnemy.addEffect(new MobEffectInstance(TotEEffects.CANDIRU_INFESTED.get(), 10*20*60, 0, false, false));
            }
        }
    }

    @Override
    public boolean canBeAffected(MobEffectInstance pEffectInstance) {
        return !this.isGoingIntoBody() && super.canBeAffected(pEffectInstance);
    }

    @Override
    public boolean canBeSeenAsEnemy() {
        return !this.isGoingIntoBody() && super.canBeSeenAsEnemy();
    }

    @Override
    public boolean canBeCollidedWith() {
        return !this.isGoingIntoBody() && super.canBeCollidedWith();
    }

    @Override
    public boolean canCollideWith(Entity pEntity) {
        return !this.isGoingIntoBody() && super.canCollideWith(pEntity);
    }

    @Override
    public boolean canBeHitByProjectile() {
        return !this.isGoingIntoBody() && super.canBeHitByProjectile();
    }

    @Override
    public boolean isInvulnerable() {
        return this.isGoingIntoBody() || super.isInvulnerable();
    }

    public static boolean checkCandiruSpawnConditions(EntityType<? extends WaterAnimal> pType, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.getRandom().nextBoolean()){
            return isDarkEnoughToSpawn((ServerLevelAccessor) pLevel, pPos, pRandom) && pLevel.getDifficulty() != Difficulty.PEACEFUL && checkSurfaceWaterAnimalSpawnRules(pType, pLevel, pSpawnType, pPos, pRandom);
        }else {
            return pLevel.getDifficulty() != Difficulty.PEACEFUL && checkSurfaceWaterAnimalSpawnRules(pType, pLevel, pSpawnType, pPos, pRandom);
        }
    }

    public static boolean isDarkEnoughToSpawn(ServerLevelAccessor pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.getBrightness(LightLayer.SKY, pPos) > pRandom.nextInt(32)) {
            return false;
        } else {
            DimensionType dimensiontype = pLevel.dimensionType();
            int i = dimensiontype.monsterSpawnBlockLightLimit();
            if (i < 15 && pLevel.getBrightness(LightLayer.BLOCK, pPos) > i) {
                return false;
            } else {
                int j = pLevel.getLevel().isThundering() ? pLevel.getMaxLocalRawBrightness(pPos, 10) : pLevel.getMaxLocalRawBrightness(pPos);
                return j <= dimensiontype.monsterSpawnLightTest().sample(pRandom);
            }
        }
    }
}
