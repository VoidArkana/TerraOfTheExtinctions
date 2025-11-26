package net.voidarkana.terraoftheextinctions.data.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.registry.TotEBlocks;
import net.voidarkana.terraoftheextinctions.registry.TotEDamageTypes;
import net.voidarkana.terraoftheextinctions.registry.TotEItems;
import net.voidarkana.terraoftheextinctions.registry.TotETags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TotEDamageTypeTagGenerator extends DamageTypeTagsProvider {

    public TotEDamageTypeTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, TerraOfTheExtinctions.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(DamageTypeTags.BYPASSES_ARMOR).add(TotEDamageTypes.CANDIRU_INFESTATION).add(TotEDamageTypes.BAROTRAUMA);

    }
}
