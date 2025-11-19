package net.voidarkana.terraoftheextinctions.data.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.registry.TotEBlocks;
import net.voidarkana.terraoftheextinctions.registry.TotEItems;
import net.voidarkana.terraoftheextinctions.registry.TotETags;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TotERecipeProvider extends RecipeProvider implements IConditionBuilder {

    public TotERecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        //Olive woodset
        makePlanks(TotEBlocks.OLIVE_PLANKS, TotETags.Items.OLIVE_LOG_ITEM).save(consumer);
        makeWood(TotEBlocks.OLIVE_WOOD, TotEBlocks.OLIVE_LOG).save(consumer);
        makeWood(TotEBlocks.STRIPPED_OLIVE_WOOD, TotEBlocks.STRIPPED_OLIVE_LOG).save(consumer);
        makeStairs(TotEBlocks.OLIVE_PLANKS, TotEBlocks.OLIVE_STAIRS).save(consumer);
        makeSlab(TotEBlocks.OLIVE_PLANKS, TotEBlocks.OLIVE_SLAB).save(consumer);
        makeFence(TotEBlocks.OLIVE_FENCE, TotEBlocks.OLIVE_PLANKS).save(consumer);
        makeFenceGate(TotEBlocks.OLIVE_FENCE_GATE, TotEBlocks.OLIVE_PLANKS).save(consumer);
        makeDoor(TotEBlocks.OLIVE_DOOR, TotEBlocks.OLIVE_PLANKS).save(consumer);
        makeTrapdoor(TotEBlocks.OLIVE_TRAPDOOR, TotEBlocks.OLIVE_PLANKS).save(consumer);
        makeButton(TotEBlocks.OLIVE_BUTTON, TotEBlocks.OLIVE_PLANKS).save(consumer);
        makePressurePlate(TotEBlocks.OLIVE_PRESSURE_PLATE, TotEBlocks.OLIVE_PLANKS).save(consumer);

        //Olive sign
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, TotEItems.OLIVE_SIGN.get(), 3)
                .pattern("SSS")
                .pattern("SSS")
                .pattern(" # ")
                .define('S', TotEBlocks.OLIVE_PLANKS.get())
                .define('#', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(TotEBlocks.OLIVE_LOG.get()), has(TotEBlocks.OLIVE_LOG.get()))
                .save(consumer);

        //Olive hanging sign
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, TotEItems.OLIVE_HANGING_SIGN.get(), 6)
                .pattern("# #")
                .pattern("SSS")
                .pattern("SSS")
                .define('#', Items.CHAIN)
                .define('S', TotEBlocks.STRIPPED_OLIVE_LOG.get())
                .unlockedBy(getHasName(TotEBlocks.OLIVE_LOG.get()), has(TotEBlocks.OLIVE_LOG.get()))
                .save(consumer);

        //salt
        makeIngotToBlock(TotEItems.SALT, TotEBlocks.SALT_BLOCK).save(consumer);
        makeBlockToIngot(TotEBlocks.SALT_BLOCK, TotEItems.SALT).save(consumer);


        //Grape woodset
        makePlanks(TotEBlocks.GRAPE_PLANKS, TotETags.Items.GRAPE_LOG_ITEM).save(consumer);
        makeWood(TotEBlocks.GRAPE_WOOD, TotEBlocks.GRAPE_LOG).save(consumer);
        makeWood(TotEBlocks.STRIPPED_GRAPE_WOOD, TotEBlocks.STRIPPED_GRAPE_LOG).save(consumer);
        makeStairs(TotEBlocks.GRAPE_PLANKS, TotEBlocks.GRAPE_STAIRS).save(consumer);
        makeSlab(TotEBlocks.GRAPE_PLANKS, TotEBlocks.GRAPE_SLAB).save(consumer);
        makeFence(TotEBlocks.GRAPE_FENCE, TotEBlocks.GRAPE_PLANKS).save(consumer);
        makeFenceGate(TotEBlocks.GRAPE_FENCE_GATE, TotEBlocks.GRAPE_PLANKS).save(consumer);
        makeDoor(TotEBlocks.GRAPE_DOOR, TotEBlocks.GRAPE_PLANKS).save(consumer);
        makeTrapdoor(TotEBlocks.GRAPE_TRAPDOOR, TotEBlocks.GRAPE_PLANKS).save(consumer);
        makeButton(TotEBlocks.GRAPE_BUTTON, TotEBlocks.GRAPE_PLANKS).save(consumer);
        makePressurePlate(TotEBlocks.GRAPE_PRESSURE_PLATE, TotEBlocks.GRAPE_PLANKS).save(consumer);

        //Grape sign
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, TotEItems.GRAPE_SIGN.get(), 3)
                .pattern("SSS")
                .pattern("SSS")
                .pattern(" # ")
                .define('S', TotEBlocks.GRAPE_PLANKS.get())
                .define('#', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(TotEBlocks.GRAPE_LOG.get()), has(TotEBlocks.GRAPE_LOG.get()))
                .save(consumer);

        //Grape hanging sign
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, TotEItems.GRAPE_HANGING_SIGN.get(), 6)
                .pattern("# #")
                .pattern("SSS")
                .pattern("SSS")
                .define('#', Items.CHAIN)
                .define('S', TotEBlocks.STRIPPED_GRAPE_LOG.get())
                .unlockedBy(getHasName(TotEBlocks.GRAPE_LOG.get()), has(TotEBlocks.GRAPE_LOG.get()))
                .save(consumer);
    }
    public ShapelessRecipeBuilder makePlanks(Supplier<? extends Block> plankOut, TagKey<Item> logIn) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, (ItemLike)plankOut.get(), 4).requires(logIn).group("planks").unlockedBy("has_log", has(logIn));
    }

    public ShapedRecipeBuilder makeDoor(Supplier<? extends Block> doorOut, Supplier<? extends Block> plankIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)doorOut.get(), 3).pattern("PP").pattern("PP").pattern("PP").define('P', (ItemLike)plankIn.get()).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)plankIn.get()).getPath(), has((ItemLike)plankIn.get()));
    }

    public ShapedRecipeBuilder makeTrapdoor(Supplier<? extends Block> trapdoorOut, Supplier<? extends Block> plankIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)trapdoorOut.get(), 2).pattern("PPP").pattern("PPP").define('P', (ItemLike)plankIn.get()).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)plankIn.get()).getPath(), has((ItemLike)plankIn.get()));
    }

    public ShapelessRecipeBuilder makeButton(Supplier<? extends Block> buttonOut, Supplier<? extends Block> blockIn) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, (ItemLike)buttonOut.get()).requires((ItemLike)blockIn.get()).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)blockIn.get()).getPath(), has((ItemLike)blockIn.get()));
    }

    public ShapedRecipeBuilder makePressurePlate(Supplier<? extends Block> pressurePlateOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)pressurePlateOut.get())
                .pattern("BB")
                .define('B', (ItemLike)blockIn.get()).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)blockIn.get()).getPath(), has((ItemLike)blockIn.get()));
    }

    public ShapedRecipeBuilder makeStairs(Supplier<? extends Block> blockIn, Supplier<? extends Block> stairsOut) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)stairsOut.get(), 4)
                .pattern("M  ")
                .pattern("MM ")
                .pattern("MMM")
                .define('M', (ItemLike)blockIn.get()).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)blockIn.get()).getPath(), has((ItemLike)blockIn.get()));
    }

    public ShapedRecipeBuilder makeSlab(Supplier<? extends Block> blockIn, Supplier<? extends Block> slabOut) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)slabOut.get(), 6)
                .pattern("MMM")
                .define('M', (ItemLike)blockIn.get()).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)blockIn.get()).getPath(), has((ItemLike)blockIn.get()));
    }

    public ShapedRecipeBuilder makeFence(Supplier<? extends Block> fenceOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)fenceOut.get(), 6).pattern("M/M").pattern("M/M").define('M', (ItemLike)blockIn.get()).define('/', Tags.Items.RODS_WOODEN).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)blockIn.get()).getPath(), has((ItemLike)blockIn.get()));
    }

    public ShapedRecipeBuilder makeFenceGate(Supplier<? extends Block> fenceGateOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)fenceGateOut.get()).pattern("/M/")
                .pattern("/M/")
                .define('M',
                        (ItemLike)blockIn.get()).define('/', Tags.Items.RODS_WOODEN).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)blockIn.get()).getPath(), has((ItemLike)blockIn.get()));
    }

    public ShapedRecipeBuilder makeWood(Supplier<? extends Block> woodOut, Supplier<? extends Block> logIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)woodOut.get(), 3)
                .pattern("MM")
                .pattern("MM")
                .define('M', (ItemLike)logIn.get()).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)logIn.get()).getPath(), has((ItemLike)logIn.get()));
    }

    public ShapedRecipeBuilder makeIngotToBlock(Supplier<? extends Item> ingotIn, Supplier<? extends Block> blockOut) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,
                        blockOut.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ingotIn.get())
                .unlockedBy("has_" +
                                ForgeRegistries.ITEMS.getKey(ingotIn.get()).getPath(),
                        has(ingotIn.get()));
    }

    public ShapelessRecipeBuilder makeBlockToIngot(Supplier<? extends Block> blockIn, Supplier<? extends Item> ingotOut) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ingotOut.get(), 9).requires((ItemLike)blockIn.get()).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)blockIn.get()).getPath(), has((ItemLike)blockIn.get()));
    }

    private ResourceLocation name(String name) {
        return new ResourceLocation(TerraOfTheExtinctions.MOD_ID, name);
    }
}
