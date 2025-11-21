package net.voidarkana.terraoftheextinctions.common.entity.animals.ai;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.level.GameRules;
import net.voidarkana.terraoftheextinctions.common.entity.animals.base.EggLayingFish;

public class EggLayingFishBreedGoal extends FishBreedGoal {
    public final EggLayingFish turtle;

    public EggLayingFishBreedGoal(EggLayingFish pTurtle, double pSpeedModifier) {
        super(pTurtle, pSpeedModifier);
        this.turtle = pTurtle;
    }

    public boolean canUse() {
        return super.canUse() && !this.turtle.hasEgg();
    }

    protected void breed() {
        ServerPlayer serverplayer = this.animal.getLoveCause();
        if (serverplayer == null && this.partner.getLoveCause() != null) {
            serverplayer = this.partner.getLoveCause();
        }

        if (serverplayer != null) {
            serverplayer.awardStat(Stats.ANIMALS_BRED);
            CriteriaTriggers.BRED_ANIMALS.trigger(serverplayer, (instance) -> true);
        }

        this.turtle.setHasEgg(true);
        this.animal.setAge(6000);
        this.partner.setAge(6000);
        this.animal.resetLove();
        this.partner.resetLove();
        RandomSource randomsource = this.animal.getRandom();
        if (this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            this.level.addFreshEntity(new ExperienceOrb(this.level, this.animal.getX(), this.animal.getY(), this.animal.getZ(), randomsource.nextInt(7) + 1));
        }

    }
}
