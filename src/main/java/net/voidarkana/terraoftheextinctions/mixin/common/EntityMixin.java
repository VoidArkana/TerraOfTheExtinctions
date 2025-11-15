package net.voidarkana.terraoftheextinctions.mixin.common;

import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.voidarkana.terraoftheextinctions.registry.TotEEntities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(Entity.class)
public abstract class EntityMixin extends net.minecraftforge.common.capabilities.CapabilityProvider<Entity>{
    @Shadow @Nullable public abstract LivingEntity getControllingPassenger();

    protected EntityMixin(Class<Entity> baseClass) {
        super(baseClass);
    }

    @Inject(method = "dismountsUnderwater()Z", at = @At("HEAD"), cancellable = true)
    public void dismountsUnderwater(CallbackInfoReturnable<Boolean> cir) {
        if (this.getControllingPassenger() != null){
            if (this.getControllingPassenger().getType() == TotEEntities.CANDIRU.get()){
                cir.setReturnValue(false);
            }
        }
    }

}
