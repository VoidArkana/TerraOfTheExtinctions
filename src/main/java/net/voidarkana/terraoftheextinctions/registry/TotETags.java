package net.voidarkana.terraoftheextinctions.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;

public class TotETags {

    public static class Blocks {
        public static final TagKey<Block> OLIVE_LOG_BLOCK = tag("olive_log_block");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(TerraOfTheExtinctions.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> OLIVE_LOG_ITEM = tag("olive_log_item");

        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(TerraOfTheExtinctions.MOD_ID, name));
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> IS_BLEAK_BIOME = tag("bleak_biomes");

        private static TagKey<Biome> tag(String name){
            return TagKey.create(Registries.BIOME, new ResourceLocation(TerraOfTheExtinctions.MOD_ID, name));
        }
    }
}
