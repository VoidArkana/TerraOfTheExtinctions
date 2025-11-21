package net.voidarkana.terraoftheextinctions.common.entity.animals;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.voidarkana.terraoftheextinctions.common.entity.animals.ai.AnimatedAttackGoal;
import net.voidarkana.terraoftheextinctions.common.entity.animals.ai.ConsumeBlockToHealGoal;
import net.voidarkana.terraoftheextinctions.common.entity.animals.ai.EggLayingFishBreedGoal;
import net.voidarkana.terraoftheextinctions.common.entity.animals.ai.LayRoeGoal;
import net.voidarkana.terraoftheextinctions.common.entity.animals.base.BreedableWaterAnimal;
import net.voidarkana.terraoftheextinctions.common.entity.animals.base.EggLayingFish;
import net.voidarkana.terraoftheextinctions.common.entity.animals.base.IAnimatedAttacker;
import net.voidarkana.terraoftheextinctions.registry.TotEBlocks;
import net.voidarkana.terraoftheextinctions.registry.TotEEntities;
import net.voidarkana.terraoftheextinctions.registry.TotEItems;
import net.voidarkana.terraoftheextinctions.registry.TotETags;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class AlligatorGar extends EggLayingFish implements IAnimatedAttacker, NeutralMob {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout;

    public AlligatorGar(EntityType<? extends EggLayingFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 15.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6F)
                .add(Attributes.ATTACK_DAMAGE, 6);
    }

    public static final Ingredient FOOD_ITEMS = Ingredient.of(TotETags.Items.GAR_FOOD);
    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(AlligatorGar.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_MALE = SynchedEntityData.defineId(AlligatorGar.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(AlligatorGar.class, EntityDataSerializers.INT);

    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    @javax.annotation.Nullable
    private UUID persistentAngerTarget;

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(TotETags.Items.GAR_FOOD);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new LayRoeGoal(this, 1.0D,
                TotETags.Blocks.GAR_ROE_NESTS, TotEBlocks.GAR_ROE));

        this.goalSelector.addGoal(2, new GarBreedGoal(this, 1.0D));

        this.goalSelector.addGoal(3, new AnimatedAttackGoal(this,  1.25F, true, 10, 10));
        this.goalSelector.addGoal(4, new TemptGoal(this, 2D, FOOD_ITEMS, false));

        this.randomSwimmingGoal = new RandomSwimmingGoal(this, 1.0D, 30);
        this.goalSelector.addGoal(4, randomSwimmingGoal);

        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, (entity) -> {
            return entity instanceof Bleak || entity instanceof Perch;
        }));

        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, (entity) -> {
            return entity instanceof Bleak || entity instanceof Perch;
        }));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)));
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
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
        this.entityData.define(IS_MALE, false);
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("IsAttacking", this.isAttacking());
        pCompound.putBoolean("IsMale", this.isMale());
        this.addPersistentAngerSaveData(pCompound);
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setAttacking(pCompound.getBoolean("IsAttacking"));
        this.setIsMale(pCompound.getBoolean("IsMale"));
        this.readPersistentAngerSaveData(this.level(), pCompound);
    }

    @Override
    public boolean canBeBucketed() {
        return false;
    }

    public boolean isMale() {
        return this.entityData.get(IS_MALE);
    }

    public void setIsMale(boolean isMale) {
        this.entityData.set(IS_MALE, isMale);
    }

    @Override
    public boolean isAttacking() {
        return this.entityData.get(IS_ATTACKING);
    }

    @Override
    public void setAttacking(boolean pFromBucket) {
        this.entityData.set(IS_ATTACKING, pFromBucket);
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
    public boolean canMate(BreedableWaterAnimal pOtherAnimal) {
        AlligatorGar mate = (AlligatorGar) pOtherAnimal;
        return this.isMale() != mate.isMale();
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
    public @Nullable BreedableWaterAnimal getBreedOffspring(ServerLevel pLevel, BreedableWaterAnimal pOtherParent) {
        return TotEEntities.PERCH.get().create(pLevel);
    }

    @Override
    public void saveToBucketTag(ItemStack bucket) {
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        compoundnbt.putFloat("Health", this.getHealth());
        compoundnbt.putInt("Age", this.getAge());

        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
    }

    @Override
    public void tick() {
        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }
        super.tick();
    }

    private void setupAnimationStates() {
        this.idleAnimationState.animateWhen(this.isAlive(), this.tickCount);

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 20;
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }
    }

    @Override
    public void loadFromBucketTag(CompoundTag pTag) {
        Bucketable.loadDefaultDataFromBucketTag(this, pTag);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {

        this.setIsMale(this.random.nextBoolean());

        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return null;
    }

    @Override
    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        return this.getDepthPathfindingFavor(pPos, pLevel);
    }

    float getDepthPathfindingFavor(BlockPos pos, LevelReader world) {
        int y = pos.getY() + Math.abs(world.getMinBuildHeight()) + 30;
        return 1f / (y < 0 ? 1 : y);
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    @Override
    public void setRemainingPersistentAngerTime(int pTime) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, pTime);
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID pTarget) {
        this.persistentAngerTarget = pTarget;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    public static class GarBreedGoal extends EggLayingFishBreedGoal{

        final AlligatorGar gar;

        public GarBreedGoal(EggLayingFish pTurtle, double pSpeedModifier) {
            super(pTurtle, pSpeedModifier);
            this.gar = (AlligatorGar) pTurtle;
        }

        protected void breed() {
            if (!this.gar.isMale()){
                super.breed();
            }
        }
    }
}
