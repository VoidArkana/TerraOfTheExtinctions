package net.voidarkana.terraoftheextinctions.common.entity.animals;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
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

public class Perch extends EggLayingFish implements IAnimatedAttacker {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout;

    public Perch(EntityType<? extends EggLayingFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.8F)
                .add(Attributes.ATTACK_DAMAGE, 3);
    }

    public static final Ingredient FOOD_ITEMS = Ingredient.of(TotETags.Items.PERCH_FOOD);
    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(Perch.class, EntityDataSerializers.BOOLEAN);

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(TotETags.Items.PERCH_FOOD);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D));
        this.goalSelector.addGoal(1, new ConsumeBlockToHealGoal(this, TotETags.Blocks.PERCH_FOOD, 4, 1.1D));
        this.goalSelector.addGoal(1, new LayRoeGoal(this, 1.0D,
                TotETags.Blocks.PERCH_ROE_NESTS, TotEBlocks.PERCH_ROE));

        this.goalSelector.addGoal(2, new EggLayingFishBreedGoal(this, 1.0D));

        this.goalSelector.addGoal(3, new AnimatedAttackGoal(this,  1.25F, true, 10, 10));
        this.goalSelector.addGoal(4, new TemptGoal(this, 2D, FOOD_ITEMS, false));

        this.randomSwimmingGoal = new RandomSwimmingGoal(this, 1.0D, 30);
        this.goalSelector.addGoal(4, randomSwimmingGoal);

        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, (entity) -> {
            return entity instanceof Bleak;
        }));
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

        if (pReason == MobSpawnType.BUCKET && pDataTag != null && pDataTag.contains("Age", 3)) {
            if (pDataTag.contains("Age")) {
                this.setAge(pDataTag.getInt("Age"));
            }
        }

        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public boolean hurt(DamageSource pSource, float pAmount) {
        if (this.level().isClientSide) {
            return false;
        } else {
            if (!pSource.is(DamageTypeTags.AVOIDS_GUARDIAN_THORNS) && !pSource.is(DamageTypes.THORNS)) {
                Entity entity = pSource.getDirectEntity();
                if (entity instanceof LivingEntity) {
                    LivingEntity livingentity = (LivingEntity)entity;
                    livingentity.hurt(this.damageSources().thorns(this), 2.0F);
                }
            }
            return super.hurt(pSource, pAmount);
        }
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(TotEItems.PERCH_BUCKET.get());
    }

    @Override
    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        if (this.hasEgg() || this.getHealth()<this.getMaxHealth())
            return super.getWalkTargetValue(pPos, pLevel);

        return this.getRandom().nextBoolean() ? this.getSurfacePathfindingFavor(pPos, pLevel) : this.isAquaticPlantBlock(pLevel.getBlockState(pPos)) ? 10f : super.getWalkTargetValue(pPos, pLevel);
    }

    float getSurfacePathfindingFavor(BlockPos pos, LevelReader world) {
        int y = Math.abs(world.getSeaLevel()-2) - pos.getY();
        return 1f / (y == 0 ? 1 : y);
    }

    boolean isAquaticPlantBlock(BlockState pState){
        return pState.is(TotETags.Blocks.AQUATIC_PLANTS);
    }

    @Override
    public boolean canFloat() {
        return false;
    }
}
