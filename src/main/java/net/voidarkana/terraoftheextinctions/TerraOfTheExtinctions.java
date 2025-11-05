package net.voidarkana.terraoftheextinctions;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.voidarkana.terraoftheextinctions.client.ClientEvents;
import net.voidarkana.terraoftheextinctions.registry.*;

@Mod(TerraOfTheExtinctions.MOD_ID)
public class TerraOfTheExtinctions
{
    public static final String MOD_ID = "terraoftheextinctions";

    public TerraOfTheExtinctions()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        TotEItems.ITEMS.register(bus);
        TotEBlocks.registerBlocks(bus);
        TotEEntities.register(bus);
        TotESounds.register(bus);
        TotEBlockEntities.register(bus);
        TotEConfiguredFeatures.register(bus);
        TotECreativeTab.register(bus);

        MinecraftForge.EVENT_BUS.register(this);

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(()->{

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(TotEBlocks.OLIVE_SAPLING.getId(), TotEBlocks.POTTED_OLIVE_SAPLING);

            ComposterBlock.COMPOSTABLES.put(TotEBlocks.OLIVE_SAPLING.get().asItem(), 0.4F);
            ComposterBlock.COMPOSTABLES.put(TotEBlocks.OLIVE_LEAVES.get().asItem(), 0.4F);
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
    }


    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){

        }
    }
}
