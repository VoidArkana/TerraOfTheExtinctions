package net.voidarkana.terraoftheextinctions.common.entity.animals;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.voidarkana.terraoftheextinctions.common.entity.animals.ai.AbstractTotEFish;
import net.voidarkana.terraoftheextinctions.common.entity.animals.ai.AnimatedAttackGoal;
import net.voidarkana.terraoftheextinctions.common.entity.animals.base.IAnimatedAttacker;
import net.voidarkana.terraoftheextinctions.network.TotEMessages;
import net.voidarkana.terraoftheextinctions.network.messages.MessageCandiruMountPlayer;
import net.voidarkana.terraoftheextinctions.registry.TotEEffects;
import net.voidarkana.terraoftheextinctions.registry.TotEEntities;
import org.jetbrains.annotations.Nullable;

public class Candiru extends AbstractTotEFish implements IAnimatedAttacker {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState getIntoBodyAnimationState = new AnimationState();
    public int attackAnimationTimeout;
    public int goingIntoBodyAnimationTimeout;
    public int prevGoingIntoBodyTicks;
    private int digTime = 0;

    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(Candiru.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> GOING_INTO_BODY_TICKS = SynchedEntityData.defineId(Candiru.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_GOING_INTO_BODY = SynchedEntityData.defineId(Candiru.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_ATTACHED = SynchedEntityData.defineId(Candiru.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> WANTS_TO_GO_IN = SynchedEntityData.defineId(Candiru.class, EntityDataSerializers.BOOLEAN);

    public Candiru(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 40));
        this.goalSelector.addGoal(2, new CandiruAttackGoal(this, 1.25, true, 10, 10));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true,
                (living)->{
                    return living.getMobType() != MobType.UNDEAD && living.getMobType() != MobType.ARTHROPOD
                            && living.getType() != (TotEEntities.CANDIRU.get()) && living.getType() != (EntityType.WARDEN)
                            && living.getType() != (EntityType.ENDER_DRAGON);
                }));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.8F)
                .add(Attributes.ATTACK_DAMAGE, 2);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_ATTACKING, false);
        this.entityData.define(IS_GOING_INTO_BODY, false);
        this.entityData.define(GOING_INTO_BODY_TICKS, 0);
        this.entityData.define(IS_ATTACHED, false);
        this.entityData.define(WANTS_TO_GO_IN, false);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("IsAttacking", this.isAttacking());
        pCompound.putBoolean("WantsToGoIn", this.wantsToGoIn());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setAttacking(pCompound.getBoolean("IsAttacking"));
        this.setWantsToGoIn(pCompound.getBoolean("WantsToGoIn"));
    }

    @Override
    public boolean isAttacking() {
        return this.entityData.get(IS_ATTACKING);
    }

    @Override
    public void setAttacking(boolean attacking) {
        this.entityData.set(IS_ATTACKING, attacking);
    }

    public boolean isAttached() {
        return this.entityData.get(IS_ATTACHED);
    }

    public void setAttached(boolean attached) {
        this.entityData.set(IS_ATTACHED, attached);
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

    public boolean wantsToGoIn() {
        return this.entityData.get(WANTS_TO_GO_IN);
    }

    public void setWantsToGoIn(boolean wantsToGoIn) {
        this.entityData.set(WANTS_TO_GO_IN, wantsToGoIn);
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

        if (this.getTarget()!=null && !this.wantsToGoIn()){
            this.setWantsToGoIn(this.getRandom().nextInt(50)==0 || this.getTarget().getHealth() < this.getTarget().getMaxHealth()/2);
        }

        if (this.wantsToGoIn() && this.getTarget()==null && !this.isPassenger()){
            this.setWantsToGoIn(false);
        }

        if (this.isGoingIntoBody()){

            if (this.getGoingIntoBodyTicks() <= 1){

                if (this.getVehicle() instanceof LivingEntity living){
                    if (living.hasEffect(TotEEffects.CANDIRU_INFESTED.get())){

                        int duration = living.getEffect(TotEEffects.CANDIRU_INFESTED.get()).getDuration();
                        int amplifier = living.getEffect(TotEEffects.CANDIRU_INFESTED.get()).getAmplifier()+1;
                        living.removeEffect(TotEEffects.CANDIRU_INFESTED.get());

                        living.addEffect(new MobEffectInstance(TotEEffects.CANDIRU_INFESTED.get(),
                                duration, amplifier, false, true));
                    }else{
                        living.addEffect(new MobEffectInstance(TotEEffects.CANDIRU_INFESTED.get(), 10*20*60, 0, false, true));
                    }
                }

                this.discard();
            } else {
                this.prevGoingIntoBodyTicks = this.getGoingIntoBodyTicks();
                this.setGoingIntoBodyTicks(this.prevGoingIntoBodyTicks-1);
            }
        }

        if (this.getGoingIntoBodyTicks()>0 && !this.isPassenger()){
            this.setGoingIntoBodyTicks(0);
            this.setGoingIntoBody(false);
        }

        if (this.isAttached() && !this.isPassenger()){
            this.setAttached(false);
        }

        if (!this.level().isClientSide){
            if (this.isAttached()) {
                if (digTime < 0)
                    digTime = 0;

                digTime++;
            } else {
                digTime = 0;
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

        if (this.getGoingIntoBodyTicks()>0 && goingIntoBodyAnimationTimeout <= 0){
            goingIntoBodyAnimationTimeout = 20;
            getIntoBodyAnimationState.start(this.tickCount);
        } else {
            --this.goingIntoBodyAnimationTimeout;
        }

        if (goingIntoBodyAnimationTimeout == 0 && getIntoBodyAnimationState.isStarted()){
            this.getIntoBodyAnimationState.stop();
        }
    }

    public void goIntoBody(){
        this.setGoingIntoBody(true);
        this.setGoingIntoBodyTicks(11);
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

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.COD_HURT;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    public class CandiruAttackGoal extends AnimatedAttackGoal{
        private final Candiru candiru;

        public CandiruAttackGoal(Candiru pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen, int pAttackDelay, int pTicksUntilNextAttack) {
            super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen, pAttackDelay, pTicksUntilNextAttack);
            this.candiru = pMob;
        }

        @Override
        public void performAttack(LivingEntity pEnemy) {
            super.performAttack(pEnemy);

            if (candiru.wantsToGoIn() && !isBeingBiten(pEnemy)){
                candiru.startRiding(pEnemy, true);
                candiru.setAttached(true);
                if (!candiru.level().isClientSide) {
                    TotEMessages.sendMSGToAll(new MessageCandiruMountPlayer(candiru.getId(), candiru.getTarget().getId()));
                }
            }
        }

        public boolean isBeingBiten(Entity entity) {
            for (Entity e : entity.getPassengers()) {
                if (e instanceof Candiru) {
                    return true;
                }
            }
            return false;
        }
    }

    public static final float STARTING_ANGLE = 0.0174532925F;

    public void rideTick() {
        final Entity entity = this.getVehicle();
        if (this.canUpdate())
            this.tick();

        if (this.isPassenger() && !entity.isAlive()) {
            this.stopRiding();
        } else {
            this.setDeltaMovement(Vec3.ZERO);
            if (this.isPassenger() && this.isAttached()) {
                if (entity instanceof final LivingEntity livingEntity) {
                    this.yBodyRot = livingEntity.yBodyRot;
                    this.setYRot(livingEntity.getYRot());
                    this.yHeadRot = livingEntity.yHeadRot;
                    this.yRotO = livingEntity.yHeadRot;
                    final float radius = 0.7F;
                    final float angle = (STARTING_ANGLE * livingEntity.yBodyRot);
                    final double extraX = radius * Mth.sin(Mth.PI + angle);
                    final double extraZ = radius * Mth.cos(angle);

                    this.setPos(entity.getX() + extraX,
                            Math.max(entity.getY() + entity.getEyeHeight() * 0.25F, entity.getY()),
                            entity.getZ() + extraZ);

                    if (!entity.isAlive() || (entity instanceof Player && ((Player) entity).isCreative())) {
                        this.removeVehicle();
                    }
                    if (!this.level().isClientSide) {


                        if (digTime % 20 == 0 && this.isAlive() && digTime <= 100 && livingEntity.getHealth() > livingEntity.getMaxHealth()/2) {
                            if (entity.hurt(this.damageSources().mobAttack(this), 1.0F)) {
                                this.gameEvent(GameEvent.EAT);
                                this.playSound(SoundEvents.DOLPHIN_EAT, this.getSoundVolume(), this.getVoicePitch());
                            }
                        }

                        if (digTime == 100 || (digTime % 20 == 0 && livingEntity.getHealth() < livingEntity.getMaxHealth()/2)) {
                            this.goIntoBody();
                        }
                    }
                }
            }
        }
    }
}
