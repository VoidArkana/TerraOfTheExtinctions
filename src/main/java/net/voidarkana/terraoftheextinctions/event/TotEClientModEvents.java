package net.voidarkana.terraoftheextinctions.event;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;

@Mod.EventBusSubscriber(modid = TerraOfTheExtinctions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class TotEClientModEvents {

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        Player player = Minecraft.getInstance().player;
        int prevTicks;
        int crushDepth = 40;

        if (player != null){
            if (player.isUnderWater() & !player.isCreative()) {

                if (TerraOfTheExtinctions.PROXY.getTicksUnderwater() < TerraOfTheExtinctions.PROXY.getMaxTicksUnderwater() &&
                        TerraOfTheExtinctions.PROXY.getTicksUnderwater() > 0){

                    prevTicks = TerraOfTheExtinctions.PROXY.getTicksUnderwater();

                    if (player.getY() <= crushDepth && TerraOfTheExtinctions.PROXY.canPlayerBeCrushed(player, player.level())) {
                        TerraOfTheExtinctions.PROXY.setTicksUnderwater(prevTicks+1);
                    }else {
                        TerraOfTheExtinctions.PROXY.setTicksUnderwater(prevTicks-1);
                    }

                }
            }else if (TerraOfTheExtinctions.PROXY.getTicksUnderwater() > 0){
                prevTicks = TerraOfTheExtinctions.PROXY.getTicksUnderwater();

                TerraOfTheExtinctions.PROXY.setTicksUnderwater(prevTicks-1);
            }
        }

    }

}
