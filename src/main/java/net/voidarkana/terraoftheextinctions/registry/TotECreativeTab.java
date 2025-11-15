package net.voidarkana.terraoftheextinctions.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;

public class TotECreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TerraOfTheExtinctions.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TERRA_OF_THE_EXTINCTIONS =
            CREATIVE_MODE_TABS.register("terraoftheextinctions", ()-> CreativeModeTab.builder().icon(() -> new ItemStack(TotEItems.PERCH_SCALES.get()))
                    .title(Component.translatable("creativetab.terraoftheextinctions"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(TotEItems.BLEAK_SPAWN_EGG.get());
                        output.accept(TotEItems.CANDIRU_SPAWN_EGG.get());
                        output.accept(TotEItems.PERCH_SPAWN_EGG.get());

                        output.accept(TotEItems.FISH_MEAT.get());
                        output.accept(TotEItems.COOKED_FISH_MEAT.get());

                        output.accept(TotEItems.PERCH_BUCKET.get());
                        output.accept(TotEBlocks.PERCH_ROE.get());
                        output.accept(TotEItems.PERCH_SCALES.get());

                        output.accept(TotEItems.OLIVES.get());

                        output.accept(TotEBlocks.OLIVE_PLANKS.get());

                        output.accept(TotEBlocks.OLIVE_LOG.get());
                        output.accept(TotEBlocks.STRIPPED_OLIVE_LOG.get());
                        output.accept(TotEBlocks.OLIVE_WOOD.get());
                        output.accept(TotEBlocks.STRIPPED_OLIVE_WOOD.get());

                        output.accept(TotEBlocks.OLIVE_STAIRS.get());
                        output.accept(TotEBlocks.OLIVE_SLAB.get());
                        output.accept(TotEBlocks.OLIVE_FENCE.get());
                        output.accept(TotEBlocks.OLIVE_FENCE_GATE.get());
                        output.accept(TotEBlocks.OLIVE_DOOR.get());
                        output.accept(TotEBlocks.OLIVE_TRAPDOOR.get());
                        output.accept(TotEBlocks.OLIVE_PRESSURE_PLATE.get());
                        output.accept(TotEBlocks.OLIVE_BUTTON.get());

                        output.accept(TotEItems.OLIVE_SIGN.get());
                        output.accept(TotEItems.OLIVE_HANGING_SIGN.get());
                        output.accept(TotEItems.OLIVE_BOAT.get());
                        output.accept(TotEItems.OLIVE_CHEST_BOAT.get());

                        output.accept(TotEBlocks.OLIVE_LEAVES.get());
                        output.accept(TotEBlocks.OLIVE_SAPLING.get());

                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
