package net.voidarkana.terraoftheextinctions.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.data.providers.*;
import net.voidarkana.terraoftheextinctions.data.providers.loot.TotELootTableProvider;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = TerraOfTheExtinctions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class TotEDataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new TotERecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), TotELootTableProvider.create(packOutput));

        generator.addProvider(event.includeClient(), new TotEBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new TotEItemModelProvider(packOutput, existingFileHelper));

        TotEBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(),
                new TotEBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(),new TotEItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new TotEWorldGenProvider(packOutput, lookupProvider));
    }
}
