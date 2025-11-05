package net.voidarkana.terraoftheextinctions.common.entity.animals.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.voidarkana.terraoftheextinctions.common.entity.animals.ai.AbstractTotEFish;
import net.voidarkana.terraoftheextinctions.common.entity.animals.ai.FollowSchoolLeaderGoal;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

public abstract class AbstractSchoolingFish extends AbstractTotEFish {

    public AbstractSchoolingFish(EntityType<? extends AbstractTotEFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(5, new FollowSchoolLeaderGoal(this));
    }

    @Nullable
    public AbstractSchoolingFish leader;
    private int schoolSize = 1;

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
        if (pSpawnData == null) {
            pSpawnData = new AbstractSchoolingFish.SchoolSpawnGroupData(this);
        } else {
            this.startFollowing(((AbstractSchoolingFish.SchoolSpawnGroupData)pSpawnData).leader);
        }

        return pSpawnData;
    }


    public int getMaxSpawnClusterSize() {
        return this.getMaxSchoolSize();
    }

    public int getMaxSchoolSize() {
        return super.getMaxSpawnClusterSize();
    }

    protected boolean canRandomSwim() {
        return !this.isFollower();
    }

    public boolean isFollower() {
        return this.leader != null && this.leader.isAlive();
    }

    public AbstractSchoolingFish startFollowing(AbstractSchoolingFish pLeader) {
        this.leader = pLeader;
        pLeader.addFollower();
        return pLeader;
    }

    public void stopFollowing() {
        this.leader.removeFollower();
        this.leader = null;
    }

    private void addFollower() {
        ++this.schoolSize;
    }

    private void removeFollower() {
        --this.schoolSize;
    }

    public boolean canBeFollowed() {
        return this.hasFollowers() && this.schoolSize < this.getMaxSchoolSize();
    }

    public void tick() {
        super.tick();
        if (this.hasFollowers() && this.level().random.nextInt(200) == 1) {
            List<? extends AbstractTotEFish> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
            if (list.size() <= 1) {
                this.schoolSize = 1;
            }
        }

    }

    public boolean hasFollowers() {
        return this.schoolSize > 1;
    }

    public boolean inRangeOfLeader() {
        return this.distanceToSqr(this.leader) <= 121.0D;
    }

    public void pathToLeader() {
        if (this.isFollower()) {
            this.getNavigation().moveTo(this.leader, 1.2D);
        }
    }

    public void addFollowers(Stream<? extends AbstractSchoolingFish> pFollowers) {
        pFollowers.limit((long)(this.getMaxSchoolSize() - this.schoolSize)).filter((p_27538_) -> {
            return p_27538_ != this;
        }).forEach((fish) -> {
            if (this.areFollowerAndLeaderCompatible(this, fish)){
                fish.startFollowing(this);
            }
        });
    }

    public boolean areFollowerAndLeaderCompatible(AbstractSchoolingFish leader, AbstractSchoolingFish follower){
        return false;
    }

    public static class SchoolSpawnGroupData implements SpawnGroupData{
        private int groupSize;
        public final AbstractSchoolingFish leader;

        public SchoolSpawnGroupData(AbstractSchoolingFish pLeader) {
            this.leader = pLeader;
        }

        public int getGroupSize() {
            return this.groupSize;
        }

        public void increaseGroupSizeByOne() {
            ++this.groupSize;
        }
    }

}
