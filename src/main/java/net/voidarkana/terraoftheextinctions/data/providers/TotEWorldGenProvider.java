package net.voidarkana.terraoftheextinctions.data.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.common.worldgen.TotEBiomeModifiers;
import net.voidarkana.terraoftheextinctions.common.worldgen.TotEConfiguredFeatures;
import net.voidarkana.terraoftheextinctions.common.worldgen.TotEPlacedFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class TotEWorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, TotEConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, TotEPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, TotEBiomeModifiers::bootstrap);

    public TotEWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(TerraOfTheExtinctions.MOD_ID));
    }
}