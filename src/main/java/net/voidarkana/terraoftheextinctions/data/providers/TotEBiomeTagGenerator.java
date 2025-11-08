package net.voidarkana.terraoftheextinctions.data.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.registry.TotETags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TotEBiomeTagGenerator extends BiomeTagsProvider {

    public TotEBiomeTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TerraOfTheExtinctions.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(TotETags.Biomes.IS_BLEAK_BIOME).addTag(BiomeTags.IS_OCEAN).addTag(BiomeTags.IS_RIVER).addTag(Tags.Biomes.IS_SWAMP);
        this.tag(TotETags.Biomes.IS_PERCH_BIOME).addTag(BiomeTags.IS_RIVER);
    }
}
