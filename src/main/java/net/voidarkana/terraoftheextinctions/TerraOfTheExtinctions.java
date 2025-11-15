package net.voidarkana.terraoftheextinctions;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.voidarkana.terraoftheextinctions.common.entity.TotEEntityPlacements;
import net.voidarkana.terraoftheextinctions.event.TotEModEvents;
import net.voidarkana.terraoftheextinctions.network.TotEMessages;
import net.voidarkana.terraoftheextinctions.registry.*;
import net.voidarkana.terraoftheextinctions.util.proxy.ClientProxy;
import net.voidarkana.terraoftheextinctions.util.proxy.CommonProxy;

import java.util.ArrayList;
import java.util.List;

@Mod(TerraOfTheExtinctions.MOD_ID)
public class TerraOfTheExtinctions
{
    public static final String MOD_ID = "terraoftheextinctions";
    public static final List<Runnable> CALLBACKS = new ArrayList<>();
    public static CommonProxy PROXY = (CommonProxy) DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public TerraOfTheExtinctions()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        TotEItems.ITEMS.register(bus);
        TotEBlocks.registerBlocks(bus);
        TotEEntities.register(bus);
        TotESounds.register(bus);
        TotEBlockEntities.register(bus);
        TotEEffects.register(bus);
        TotEConfiguredFeatures.register(bus);
        TotECreativeTab.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new TotEModEvents());

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        TotEMessages.register();
        event.enqueueWork(()->{
            TotEEntityPlacements.entityPlacement();

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(TotEBlocks.OLIVE_SAPLING.getId(), TotEBlocks.POTTED_OLIVE_SAPLING);

            ComposterBlock.COMPOSTABLES.put(TotEBlocks.OLIVE_SAPLING.get().asItem(), 0.4F);
            ComposterBlock.COMPOSTABLES.put(TotEBlocks.OLIVE_LEAVES.get().asItem(), 0.4F);
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> PROXY.clientInit());
    }
}
