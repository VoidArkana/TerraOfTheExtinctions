package net.voidarkana.terraoftheextinctions.data.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.registry.TotEBlocks;
import net.voidarkana.terraoftheextinctions.registry.TotETags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TotEBlockTagGenerator extends BlockTagsProvider {


    public TotEBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TerraOfTheExtinctions.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(TotETags.Blocks.AQUATIC_PLANTS).add(Blocks.SEAGRASS).add(Blocks.TALL_SEAGRASS)
                .add(Blocks.KELP_PLANT).add(Blocks.KELP_PLANT);

        this.tag(TotETags.Blocks.PERCH_ROE_NESTS).add(Blocks.DIRT).add(Blocks.GRAVEL);

        this.tag(TotETags.Blocks.PERCH_FOOD).add(TotEBlocks.PERCH_ROE.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
                TotEBlocks.OLIVE_PLANKS.get(),
                TotEBlocks.OLIVE_STAIRS.get(),
                TotEBlocks.OLIVE_SLAB.get(),
                TotEBlocks.OLIVE_BUTTON.get(),
                TotEBlocks.OLIVE_PRESSURE_PLATE.get(),
                TotEBlocks.OLIVE_FENCE.get(),
                TotEBlocks.OLIVE_FENCE_GATE.get(),
                TotEBlocks.OLIVE_DOOR.get(),
                TotEBlocks.OLIVE_TRAPDOOR.get(),
                TotEBlocks.OLIVE_LOG.get(),
                TotEBlocks.STRIPPED_OLIVE_LOG.get(),
                TotEBlocks.OLIVE_WOOD.get(),
                TotEBlocks.STRIPPED_OLIVE_WOOD.get(),
                TotEBlocks.OLIVE_SIGN.get(),
                TotEBlocks.OLIVE_WALL_SIGN.get(),
                TotEBlocks.OLIVE_HANGING_SIGN.get(),
                TotEBlocks.OLIVE_WALL_HANGING_SIGN.get()
        );

        this.tag(BlockTags.PLANKS).add(
                TotEBlocks.OLIVE_PLANKS.get()
        );

        this.tag(TotETags.Blocks.OLIVE_LOG_BLOCK).add(
                TotEBlocks.OLIVE_LOG.get(),
                TotEBlocks.STRIPPED_OLIVE_LOG.get(),
                TotEBlocks.OLIVE_WOOD.get(),
                TotEBlocks.STRIPPED_OLIVE_WOOD.get()
        );

        this.tag(BlockTags.LOGS_THAT_BURN)
                .addTag(TotETags.Blocks.OLIVE_LOG_BLOCK)
        ;

        this.tag(BlockTags.MINEABLE_WITH_HOE).add(
                TotEBlocks.OLIVE_LEAVES.get()
        );

        this.tag(BlockTags.LEAVES).add(
                TotEBlocks.OLIVE_LEAVES.get()
        );
        this.tag(BlockTags.WOODEN_FENCES).add(
                TotEBlocks.OLIVE_FENCE.get()
        );
        this.tag(BlockTags.FENCE_GATES).add(
                TotEBlocks.OLIVE_FENCE_GATE.get()
        );

        this.tag(BlockTags.WOODEN_DOORS).add(
                TotEBlocks.OLIVE_DOOR.get()
        );

        this.tag(BlockTags.WOODEN_TRAPDOORS).add(
                TotEBlocks.OLIVE_TRAPDOOR.get()
        );

        this.tag(BlockTags.WOODEN_STAIRS).add(
                TotEBlocks.OLIVE_STAIRS.get()
        );

        this.tag(BlockTags.WOODEN_SLABS).add(
                TotEBlocks.OLIVE_SLAB.get()
        );

        this.tag(BlockTags.WOODEN_BUTTONS).add(
                TotEBlocks.OLIVE_BUTTON.get()
        );

        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(
                TotEBlocks.OLIVE_PRESSURE_PLATE.get()
        );

        this.tag(BlockTags.SIGNS).add(
                TotEBlocks.OLIVE_SIGN.get()
        );

        this.tag(BlockTags.WALL_SIGNS).add(
                TotEBlocks.OLIVE_WALL_SIGN.get()
        );

        this.tag(BlockTags.CEILING_HANGING_SIGNS).add(
                TotEBlocks.OLIVE_HANGING_SIGN.get()
        );

        this.tag(BlockTags.WALL_HANGING_SIGNS).add(
                TotEBlocks.OLIVE_WALL_HANGING_SIGN.get()
        );
    }
}
