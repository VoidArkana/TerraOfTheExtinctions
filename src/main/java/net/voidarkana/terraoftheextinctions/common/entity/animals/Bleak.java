package net.voidarkana.terraoftheextinctions.common.entity.animals;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.voidarkana.terraoftheextinctions.common.entity.animals.ai.FollowSchoolLeaderGoal;
import net.voidarkana.terraoftheextinctions.common.entity.animals.base.AbstractSchoolingFish;

import javax.annotation.Nullable;

public class Bleak extends AbstractSchoolingFish {

    @Nullable
    public RandomSwimmingGoal randomSwimmingGoal;
    public final AnimationState idleAnimationState = new AnimationState();

    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Bleak.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_LEADER = SynchedEntityData.defineId(Bleak.class, EntityDataSerializers.BOOLEAN);

    public Bleak(EntityType<? extends AbstractSchoolingFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.8F);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D));
        this.goalSelector.addGoal(2, new FollowSchoolLeaderGoal(this));
        this.randomSwimmingGoal = new RandomSwimmingGoal(this, 1.0D, 10);
        this.goalSelector.addGoal(4, randomSwimmingGoal);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(IS_LEADER, false);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getVariant());
        compound.putBoolean("IsLeader", this.isLeader());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(compound.getInt("Variant"));
        this.setIsLeader(compound.getBoolean("IsLeader"));
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public boolean isLeader() {
        return this.entityData.get(IS_LEADER);
    }

    public void setIsLeader(boolean isLeader) {
        this.entityData.set(IS_LEADER, isLeader);
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
    }

    public boolean isFollower() {
        Bleak leaderFish;
        if (this.leader != null && this.leader.isAlive()){
            leaderFish = (Bleak) this.leader;

            return leaderFish.getVariant()==this.getVariant()
                    && leaderFish.isLeader() && !this.isLeader();
        }else {
            return false;
        }
    }

    @Override
    public boolean canBeFollowed() {
        return this.isLeader() && super.canBeFollowed();
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @org.jetbrains.annotations.Nullable SpawnGroupData pSpawnData, @org.jetbrains.annotations.Nullable CompoundTag pDataTag) {

        super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);

        int variant;

        if (pReason == MobSpawnType.SPAWN_EGG){
            variant = this.getRandom().nextBoolean() ? 0 : 1;
            this.setIsLeader(this.getRandom().nextInt(5)==0);
        }else {
            if (pSpawnData == null) {
                pSpawnData = new AbstractSchoolingFish.SchoolSpawnGroupData(this);
            } else {
                this.startFollowing(((AbstractSchoolingFish.SchoolSpawnGroupData)pSpawnData).leader);
            }

            if (pLevel.getBiome(this.blockPosition()).is(BiomeTags.IS_OCEAN)){
                variant = 1;
            }else {
                variant = 0;
            }

            if (pSpawnData instanceof SchoolSpawnGroupData fish$fishgroupdata){
                this.setIsLeader(false);
                this.startFollowing(fish$fishgroupdata.leader);
            }else {
                this.setIsLeader(true);
                pSpawnData = new AbstractSchoolingFish.SchoolSpawnGroupData(this);
            }
        }

        this.setVariant(variant);

        return pSpawnData;
    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        return super.mobInteract(pPlayer, pHand);
    }

    public int getMaxSpawnClusterSize() {
        return 10;
    }

    public int getMaxSchoolSize() {
        return 50;
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return null;
    }
}
