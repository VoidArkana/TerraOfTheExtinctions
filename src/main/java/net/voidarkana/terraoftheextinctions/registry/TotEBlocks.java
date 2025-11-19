package net.voidarkana.terraoftheextinctions.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.common.blocks.*;
import net.voidarkana.terraoftheextinctions.common.blocks.signs.TotEHangingSignBlock;
import net.voidarkana.terraoftheextinctions.common.blocks.signs.TotEStandingSignBlock;
import net.voidarkana.terraoftheextinctions.common.blocks.signs.TotEWallHangingSignBlock;
import net.voidarkana.terraoftheextinctions.common.blocks.signs.TotEWallSignBlock;
import net.voidarkana.terraoftheextinctions.common.worldgen.tree.OliveTreeGrower;

import java.util.function.Supplier;

public class TotEBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TerraOfTheExtinctions.MOD_ID);

    //Olive plank blocks
    public static final RegistryObject<Block> OLIVE_PLANKS = registerBlock("olive_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).ignitedByLava()));
    public static final RegistryObject<Block> OLIVE_STAIRS = registerBlock("olive_stairs",
            () -> new StairBlock(() -> TotEBlocks.OLIVE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
                    .ignitedByLava()));
    public static final RegistryObject<Block> OLIVE_SLAB = registerBlock("olive_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).ignitedByLava()));
    public static final RegistryObject<Block> OLIVE_BUTTON = registerBlock("olive_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).ignitedByLava()
                    , BlockSetType.CRIMSON, 25, true));
    public static final RegistryObject<Block> OLIVE_PRESSURE_PLATE = registerBlock("olive_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy
                    (Blocks.OAK_PRESSURE_PLATE).ignitedByLava(), BlockSetType.CRIMSON));
    public static final RegistryObject<Block> OLIVE_FENCE = registerBlock("olive_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).ignitedByLava()));
    public static final RegistryObject<Block> OLIVE_FENCE_GATE = registerBlock("olive_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).ignitedByLava(),
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));


    //Olive Door and Trapdoor
    public static final RegistryObject<Block> OLIVE_DOOR = registerBlock("olive_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion(), BlockSetType.CRIMSON));
    public static final RegistryObject<Block> OLIVE_TRAPDOOR = registerBlock("olive_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion(), BlockSetType.CRIMSON));

    //Olive signs
    public static final RegistryObject<Block> OLIVE_SIGN = BLOCKS.register("olive_sign",
            () -> new TotEStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_SIGN), TotEWoodTypes.OLIVE));
    public static final RegistryObject<Block> OLIVE_WALL_SIGN = BLOCKS.register("olive_wall_sign",
            () -> new TotEWallSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_SIGN), TotEWoodTypes.OLIVE));
    public static final RegistryObject<Block> OLIVE_HANGING_SIGN = BLOCKS.register("olive_hanging_sign",
            () -> new TotEHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_HANGING_SIGN), TotEWoodTypes.OLIVE));
    public static final RegistryObject<Block> OLIVE_WALL_HANGING_SIGN = BLOCKS.register("olive_wall_hanging_sign",
            () -> new TotEWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_HANGING_SIGN), TotEWoodTypes.OLIVE));

    //Olive logs and wood
    public static final RegistryObject<Block> OLIVE_LOG = registerBlock("olive_log",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> OLIVE_WOOD = registerBlock("olive_wood",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_OLIVE_LOG = registerBlock("stripped_olive_log",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_OLIVE_WOOD = registerBlock("stripped_olive_wood",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));

    //Olive Leaves
    public static final RegistryObject<Block> OLIVE_LEAVES = registerBlock("olive_leaves",
            () -> new OliveLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    //Olive Sapling Blocks
    public static final RegistryObject<Block> OLIVE_SAPLING = registerBlock("olive_sapling",
            () -> new SaplingBlock(new OliveTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).noOcclusion().noCollission()));
    public static final RegistryObject<Block> POTTED_OLIVE_SAPLING = registerBlock("potted_olive_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), TotEBlocks.OLIVE_SAPLING,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));

    //Fish Roe
    public static final RegistryObject<Block> PERCH_ROE = registerBlock("perch_roe",
            () -> new FishRoeBlock(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN).instabreak().randomTicks(), TotEEntities.PERCH));

    //Salt
    public static final RegistryObject<Block> SALT_BLOCK = registerBlock("salt_block",
            () -> new SaltBlock(BlockBehaviour.Properties.copy(Blocks.CALCITE)));

    public static final RegistryObject<Block> SALT_CRYSTAL = registerBlock("salt_crystal",
            () -> new SaltClusterBlock(7, 3, BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ)
                    .forceSolidOn().noOcclusion().sound(SoundType.CALCITE).strength(1.5F)
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> LARGE_SALT_BUD = registerBlock("large_salt_bud",
            () -> new SaltClusterBlock(5, 3, BlockBehaviour.Properties.copy(SALT_CRYSTAL.get())));

    public static final RegistryObject<Block> MEDIUM_SALT_BUD = registerBlock("medium_salt_bud",
            () -> new SaltClusterBlock(4, 3, BlockBehaviour.Properties.copy(SALT_CRYSTAL.get())));

    public static final RegistryObject<Block> SMALL_SALT_BUD = registerBlock("small_salt_bud",
            () -> new SaltClusterBlock(3, 4, BlockBehaviour.Properties.copy(SALT_CRYSTAL.get())));



    //registers
    private static <T extends Block> Supplier<T> create(String key, Supplier<T> block) {
        return BLOCKS.register(key, block);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return TotEItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void registerBlocks(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
