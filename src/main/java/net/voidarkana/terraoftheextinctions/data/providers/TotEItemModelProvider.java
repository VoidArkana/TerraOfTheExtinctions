package net.voidarkana.terraoftheextinctions.data.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RailState;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.registry.TotEBlocks;
import net.voidarkana.terraoftheextinctions.registry.TotEItems;

public class TotEItemModelProvider extends ItemModelProvider {

    public TotEItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TerraOfTheExtinctions.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        // OLIVE
        simpleItem(TotEItems.OLIVES);
        simpleBlockItem(TotEBlocks.OLIVE_DOOR);

        simpleBlockItem(TotEBlocks.OLIVE_SAPLING);

        trapdoorItem(TotEBlocks.OLIVE_TRAPDOOR);

        evenSimplerBlockItem(TotEBlocks.OLIVE_STAIRS);
        evenSimplerBlockItem(TotEBlocks.OLIVE_SLAB);
        evenSimplerBlockItem(TotEBlocks.OLIVE_FENCE_GATE);
        evenSimplerBlockItem(TotEBlocks.OLIVE_PRESSURE_PLATE);

        fenceItem(TotEBlocks.OLIVE_FENCE, TotEBlocks.OLIVE_PLANKS);
        buttonItem(TotEBlocks.OLIVE_BUTTON, TotEBlocks.OLIVE_PLANKS);

        simpleItem(TotEItems.OLIVE_SIGN);
        simpleItem(TotEItems.OLIVE_HANGING_SIGN);

        simpleItem(TotEItems.OLIVE_BOAT);
        simpleItem(TotEItems.OLIVE_CHEST_BOAT);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation(ResourceLocation.DEFAULT_NAMESPACE, "item/generated")).texture("layer0",
                new ResourceLocation(TerraOfTheExtinctions.MOD_ID, "item/" + item.getId().getPath()));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(TerraOfTheExtinctions.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", new ResourceLocation(TerraOfTheExtinctions.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture", new ResourceLocation(TerraOfTheExtinctions.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation(ResourceLocation.DEFAULT_NAMESPACE, "item/generated")).texture("layer0",
                new ResourceLocation(TerraOfTheExtinctions.MOD_ID,"item/" + item.getId().getPath()));
    }
}
