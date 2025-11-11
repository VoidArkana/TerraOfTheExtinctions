package net.voidarkana.terraoftheextinctions.common.effects;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Candiru;
import net.voidarkana.terraoftheextinctions.registry.TotEEffects;
import net.voidarkana.terraoftheextinctions.registry.TotEEntities;

public class CandiruEffect extends MobEffect {

    private LivingEntity entity;

    public CandiruEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        Level level = pLivingEntity.level();
        Candiru fish = TotEEntities.CANDIRU.get().create(level);

        pLivingEntity.hurt(pLivingEntity.damageSources().mobAttack(fish), 1.0F);

        if (this.entity == null)
            this.entity = pLivingEntity;
    }

    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        if (pDuration == 1)
            spawnCandiru();
        return pDuration % 100 == 0;
    }

    public void spawnCandiru(){
        Level level = this.entity.level();
        if (level instanceof ServerLevel sLevel){

            int e = 2 * (this.entity.getEffect(TotEEffects.CANDIRU_INFESTED.get()).getAmplifier() + 1);

            for(int i = 0; i != e; ++i) {
                Candiru fish = TotEEntities.CANDIRU.get().create(sLevel);
                fish.moveTo(Vec3.atCenterOf(this.entity.getOnPos()));
                sLevel.addFreshEntity(fish);
            }
        }

        Candiru fish = TotEEntities.CANDIRU.get().create(level);
        this.entity.hurt(this.entity.damageSources().mobAttack(fish), 1.0F);
    }
}
