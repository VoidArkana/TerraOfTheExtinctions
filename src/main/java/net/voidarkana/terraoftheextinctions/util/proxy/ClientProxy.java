package net.voidarkana.terraoftheextinctions.util.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.client.renderers.*;
import net.voidarkana.terraoftheextinctions.registry.TotEEntities;
import net.voidarkana.terraoftheextinctions.registry.TotEWoodTypes;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = TerraOfTheExtinctions.MOD_ID, value = {Dist.CLIENT})
public class ClientProxy extends CommonProxy{

    public ClientProxy() {
    }

    @Override
    public void init() {
    }

    public void clientInit() {
        Sheets.addWoodType(TotEWoodTypes.OLIVE);
        Sheets.addWoodType(TotEWoodTypes.GRAPE);

//        MinecraftForge.EVENT_BUS.register(new MMClientEvents());

        TerraOfTheExtinctions.CALLBACKS.forEach(Runnable::run);
        TerraOfTheExtinctions.CALLBACKS.clear();

        EntityRenderers.register(TotEEntities.MOD_BOAT.get(), p_174010_ -> new TotEBoatRenderer(p_174010_, false));
        EntityRenderers.register(TotEEntities.MOD_CHEST_BOAT.get(), p_174010_ -> new TotEBoatRenderer(p_174010_, true));

        EntityRenderers.register(TotEEntities.BLEAK.get(), BleakRenderer::new);
        EntityRenderers.register(TotEEntities.PERCH.get(), PerchRenderer::new);
        EntityRenderers.register(TotEEntities.CANDIRU.get(), CandiruRenderer::new);
        EntityRenderers.register(TotEEntities.GAR.get(), GarRenderer::new);
    }

    @Override
    public Level getWorld() {
        return Minecraft.getInstance().level;
    }

    public Player getClientSidePlayer() {
        return Minecraft.getInstance().player;
    }

}
