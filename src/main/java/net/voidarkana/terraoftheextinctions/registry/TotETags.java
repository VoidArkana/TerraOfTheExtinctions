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
        public static final TagKey<Block> GRAPE_LOG_BLOCK = tag("grape_log_block");

        public static final TagKey<Block> PERCH_FOOD = tag("perch_food");
        public static final TagKey<Block> PERCH_ROE_NESTS = tag("perch_nesting_blocks");
        public static final TagKey<Block> GAR_ROE_NESTS = tag("gar_nesting_blocks");

        public static final TagKey<Block> AQUATIC_PLANTS = tag("aquatic_plants");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(TerraOfTheExtinctions.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> OLIVE_LOG_ITEM = tag("olive_log_item");
        public static final TagKey<Item> GRAPE_LOG_ITEM = tag("grape_log_item");

        public static final TagKey<Item> PERCH_FOOD = tag("perch_food");
        public static final TagKey<Item> GAR_FOOD = tag("gar_food");

        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(TerraOfTheExtinctions.MOD_ID, name));
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> IS_BLEAK_BIOME = tag("bleak_biomes");
        public static final TagKey<Biome> IS_PERCH_BIOME = tag("perch_biomes");
        public static final TagKey<Biome> IS_CANDIRU_BIOME = tag("candiru_biomes");
        public static final TagKey<Biome> IS_GAR_BIOME = tag("alligator_gar_biomes");

        public static final TagKey<Biome> IS_SALT_PILLAR_BIOME = tag("salt_pillar_biomes");

        private static TagKey<Biome> tag(String name){
            return TagKey.create(Registries.BIOME, new ResourceLocation(TerraOfTheExtinctions.MOD_ID, name));
        }
    }
}
