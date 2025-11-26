package net.voidarkana.terraoftheextinctions.util.proxy;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;

@Mod.EventBusSubscriber(modid = TerraOfTheExtinctions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {


    public CommonProxy() {
    }

    public void init() {
    }

    public void clientInit() {
    }

    public Level getWorld() {
        return ServerLifecycleHooks.getCurrentServer().overworld();
    }

    public Player getClientSidePlayer() {
        return null;
    }

    int commonTicksUnderwater;

    public int getTicksUnderwater() {
        return this.commonTicksUnderwater;
    }

    public void setTicksUnderwater(int ticks) {
        this.commonTicksUnderwater = ticks;
    }

    public int getMaxTicksUnderwater() {
        return 20;
    }

    public boolean canPlayerBeCrushed(Player player, Level level){

        if (player instanceof ServerPlayer sPlayer && level instanceof ServerLevel sLevel){
            BlockPos pos = sPlayer.blockPosition();

            for (int i = 1; i<=6; i++){
                if (!sLevel.getBlockState(pos.above(i)).getFluidState().is(FluidTags.WATER)){
                    return !sLevel.getBlockState(pos.above(i)).isAir();
                }
            }
            return true;
        }

        return false;
    }
}
