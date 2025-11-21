package net.voidarkana.terraoftheextinctions.data.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.registry.TotEBlocks;
import net.voidarkana.terraoftheextinctions.registry.TotEItems;
import net.voidarkana.terraoftheextinctions.registry.TotETags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TotEItemTagGenerator extends ItemTagsProvider {
    public TotEItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, TerraOfTheExtinctions.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.copy(TotETags.Blocks.OLIVE_LOG_BLOCK, TotETags.Items.OLIVE_LOG_ITEM);
        this.copy(TotETags.Blocks.GRAPE_LOG_BLOCK, TotETags.Items.GRAPE_LOG_ITEM);

        this.tag(TotETags.Items.PERCH_FOOD).addTag(ItemTags.FISHES).add(TotEItems.FISH_MEAT.get());

        this.tag(TotETags.Items.GAR_FOOD)
                .add(TotEBlocks.PERCH_ROE.get().asItem());

        this.tag(ItemTags.LOGS_THAT_BURN)
                .addTag(TotETags.Items.OLIVE_LOG_ITEM)
                .addTag(TotETags.Items.GRAPE_LOG_ITEM);

        this.tag(ItemTags.PLANKS).add(
                TotEBlocks.OLIVE_PLANKS.get().asItem(),
                TotEBlocks.GRAPE_PLANKS.get().asItem()
        );

        this.tag(ItemTags.LEAVES).add(
                TotEBlocks.OLIVE_LEAVES.get().asItem(),
                TotEBlocks.GRAPE_LEAVES.get().asItem()
        );

        this.tag(ItemTags.WOODEN_FENCES).add(
                TotEBlocks.OLIVE_FENCE.get().asItem(),
                TotEBlocks.GRAPE_FENCE.get().asItem()
        );
        this.tag(ItemTags.FENCE_GATES).add(
                TotEBlocks.OLIVE_FENCE_GATE.get().asItem(),
                TotEBlocks.GRAPE_FENCE_GATE.get().asItem()
        );

        this.tag(ItemTags.WOODEN_DOORS).add(
                TotEBlocks.OLIVE_DOOR.get().asItem(),
                TotEBlocks.GRAPE_DOOR.get().asItem()
        );

        this.tag(ItemTags.WOODEN_TRAPDOORS).add(
                TotEBlocks.OLIVE_TRAPDOOR.get().asItem(),
                TotEBlocks.GRAPE_TRAPDOOR.get().asItem()
        );

        this.tag(ItemTags.WOODEN_STAIRS).add(
                TotEBlocks.OLIVE_STAIRS.get().asItem(),
                TotEBlocks.GRAPE_STAIRS.get().asItem()
        );
        this.tag(ItemTags.WOODEN_SLABS).add(
                TotEBlocks.OLIVE_SLAB.get().asItem(),
                TotEBlocks.GRAPE_SLAB.get().asItem()
        );
        this.tag(ItemTags.WOODEN_BUTTONS).add(
                TotEBlocks.OLIVE_BUTTON.get().asItem(),
                TotEBlocks.GRAPE_BUTTON.get().asItem()
        );
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES).add(
                TotEBlocks.OLIVE_PRESSURE_PLATE.get().asItem(),
                TotEBlocks.GRAPE_PRESSURE_PLATE.get().asItem()
        );

        this.tag(ItemTags.SIGNS).add(
                TotEItems.OLIVE_SIGN.get(),
                TotEItems.GRAPE_SIGN.get()
        );

        this.tag(ItemTags.HANGING_SIGNS).add(
                TotEItems.OLIVE_HANGING_SIGN.get(),
                TotEItems.GRAPE_HANGING_SIGN.get()
        );
    }
}
