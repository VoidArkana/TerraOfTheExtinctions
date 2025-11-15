package net.voidarkana.terraoftheextinctions.common.effects;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Candiru;
import net.voidarkana.terraoftheextinctions.registry.TotEDamageTypes;
import net.voidarkana.terraoftheextinctions.registry.TotEEffects;
import net.voidarkana.terraoftheextinctions.registry.TotEEntities;

import java.util.ArrayList;
import java.util.List;

public class CandiruEffect extends MobEffect {

    private boolean spawnCandiru = false;

    public CandiruEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        Level level = pLivingEntity.level();
        Candiru fish = TotEEntities.CANDIRU.get().create(level);

        pLivingEntity.hurt(level.damageSources().source(TotEDamageTypes.CANDIRU_INFESTATION, pLivingEntity), 1.0F);
        pLivingEntity.gameEvent(GameEvent.ENTITY_DAMAGE);

        if (this.spawnCandiru){
            pLivingEntity.hurt(level.damageSources().source(TotEDamageTypes.CANDIRU_INFESTATION, pLivingEntity), 7.0F);

            if (pLivingEntity.isAlive())
                this.spawnCandiru(pLivingEntity);
        }
    }

    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        if (pDuration>5)
            this.spawnCandiru = false;
        else if (!this.spawnCandiru)
            this.spawnCandiru = true;
        return pDuration % 100 == 0 || pDuration == 5;
    }

    public void spawnCandiru(LivingEntity entity){
        Level level = entity.level();
        if (level instanceof ServerLevel sLevel){
            int e = 2 * (entity.getEffect(TotEEffects.CANDIRU_INFESTED.get()).getAmplifier() + 1);

            for(int i = 0; i != e; ++i) {
                Candiru fish = TotEEntities.CANDIRU.get().create(sLevel);
                fish.moveTo(Vec3.atCenterOf(entity.getOnPos()).add(0, 1, 0));
                sLevel.addFreshEntity(fish);
            }
        }
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(Items.DEBUG_STICK));
        return ret;
    }
}
