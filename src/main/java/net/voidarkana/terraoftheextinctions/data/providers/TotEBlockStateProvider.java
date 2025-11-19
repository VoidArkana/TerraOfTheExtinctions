package net.voidarkana.terraoftheextinctions.data.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.registry.TotEBlocks;

public class TotEBlockStateProvider extends BlockStateProvider {

    public TotEBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TerraOfTheExtinctions.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        //olive woodset
        blockWithItem(TotEBlocks.OLIVE_PLANKS);
        stairsBlock(((StairBlock) TotEBlocks.OLIVE_STAIRS.get()), blockTexture(TotEBlocks.OLIVE_PLANKS.get()));
        slabBlock(((SlabBlock) TotEBlocks.OLIVE_SLAB.get()), blockTexture(TotEBlocks.OLIVE_PLANKS.get()), blockTexture(TotEBlocks.OLIVE_PLANKS.get()));
        buttonBlock(((ButtonBlock) TotEBlocks.OLIVE_BUTTON.get()), blockTexture(TotEBlocks.OLIVE_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) TotEBlocks.OLIVE_PRESSURE_PLATE.get()), blockTexture(TotEBlocks.OLIVE_PLANKS.get()));
        fenceBlock(((FenceBlock) TotEBlocks.OLIVE_FENCE.get()), blockTexture(TotEBlocks.OLIVE_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) TotEBlocks.OLIVE_FENCE_GATE.get()), blockTexture(TotEBlocks.OLIVE_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) TotEBlocks.OLIVE_DOOR.get()), modLoc("block/olive_door_bottom"), modLoc("block/olive_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) TotEBlocks.OLIVE_TRAPDOOR.get()), modLoc("block/olive_trapdoor"), true, "cutout");

        signBlock(((StandingSignBlock) TotEBlocks.OLIVE_SIGN.get()), ((WallSignBlock) TotEBlocks.OLIVE_WALL_SIGN.get()),
                blockTexture(TotEBlocks.OLIVE_PLANKS.get()));
        hangingSignBlock(TotEBlocks.OLIVE_HANGING_SIGN.get(), TotEBlocks.OLIVE_WALL_HANGING_SIGN.get(),
                blockTexture(TotEBlocks.OLIVE_PLANKS.get()));

        logBlock(((RotatedPillarBlock) TotEBlocks.OLIVE_LOG.get()));
        axisBlock(((RotatedPillarBlock) TotEBlocks.STRIPPED_OLIVE_LOG.get()), blockTexture(TotEBlocks.STRIPPED_OLIVE_LOG.get()), new ResourceLocation(TerraOfTheExtinctions.MOD_ID, "block/stripped_olive_log_top"));
        blockItem(TotEBlocks.OLIVE_LOG);
        blockItem(TotEBlocks.STRIPPED_OLIVE_LOG);

        axisBlock(((RotatedPillarBlock) TotEBlocks.OLIVE_WOOD.get()), blockTexture(TotEBlocks.OLIVE_LOG.get()), blockTexture(TotEBlocks.OLIVE_LOG.get()));
        axisBlock(((RotatedPillarBlock) TotEBlocks.STRIPPED_OLIVE_WOOD.get()), blockTexture(TotEBlocks.STRIPPED_OLIVE_LOG.get()), blockTexture(TotEBlocks.STRIPPED_OLIVE_LOG.get()));
        blockItem(TotEBlocks.OLIVE_WOOD);
        blockItem(TotEBlocks.STRIPPED_OLIVE_WOOD);

        simpleBlockWithItem(TotEBlocks.OLIVE_SAPLING.get(), models().cross(blockTexture(TotEBlocks.OLIVE_SAPLING.get()).getPath(),
                blockTexture(TotEBlocks.OLIVE_SAPLING.get())).renderType("cutout"));

        simpleBlockWithItem(TotEBlocks.POTTED_OLIVE_SAPLING.get(), models().singleTexture("potted_olive_sapling", new ResourceLocation(ResourceLocation.DEFAULT_NAMESPACE, "flower_pot_cross"), "plant",
                blockTexture(TotEBlocks.OLIVE_SAPLING.get())).renderType("cutout"));

        blockWithItem(TotEBlocks.SALT_BLOCK);
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation(ResourceLocation.DEFAULT_NAMESPACE, "block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(TerraOfTheExtinctions.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(),
                cubeAll(blockRegistryObject.get()));
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
}
