package net.voidarkana.terraoftheextinctions.registry;

import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.common.entity.boats.TotEBoatEntity;
import net.voidarkana.terraoftheextinctions.common.item.FishBucketItem;
import net.voidarkana.terraoftheextinctions.common.item.FishSpawnEggItem;
import net.voidarkana.terraoftheextinctions.common.item.TotEBoatItem;

public class TotEItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TerraOfTheExtinctions.MOD_ID);

    //Olive
    public static final RegistryObject<Item> OLIVES = ITEMS.register("olives",
            () -> new Item(new Item.Properties().food(TotEFoods.OLIVES)));

    public static final RegistryObject<Item> OLIVE_SIGN = ITEMS.register("olive_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), TotEBlocks.OLIVE_SIGN.get(), TotEBlocks.OLIVE_WALL_SIGN.get()));

    public static final RegistryObject<Item> OLIVE_HANGING_SIGN = ITEMS.register("olive_hanging_sign",
            () -> new HangingSignItem(TotEBlocks.OLIVE_HANGING_SIGN.get(), TotEBlocks.OLIVE_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> OLIVE_BOAT = ITEMS.register("olive_boat",
            () -> new TotEBoatItem(false, TotEBoatEntity.Type.OLIVE, new Item.Properties()));
    public static final RegistryObject<Item> OLIVE_CHEST_BOAT = ITEMS.register("olive_chest_boat",
            () -> new TotEBoatItem(true, TotEBoatEntity.Type.OLIVE, new Item.Properties()));

    public static final RegistryObject<Item> FISH_MEAT = ITEMS.register("fish_meat",
            () -> new Item(new Item.Properties().food(TotEFoods.RAW_FISH)));
    public static final RegistryObject<Item> COOKED_FISH_MEAT = ITEMS.register("cooked_fish_meat",
            () -> new Item(new Item.Properties().food(TotEFoods.COOKED_FISH)));

    public static final RegistryObject<Item> BLEAK_SPAWN_EGG = ITEMS.register("bleak_spawn_egg",
            () -> new ForgeSpawnEggItem(TotEEntities.BLEAK, 0x3f4c4a, 0x74756e, new Item.Properties()));

    public static final RegistryObject<Item> PERCH_SCALES = ITEMS.register("perch_scales",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PERCH_SPAWN_EGG = ITEMS.register("perch_spawn_egg",
            () -> new FishSpawnEggItem(TotEEntities.PERCH, 0x62673d, 0xcb6736, new Item.Properties()));
    public static final RegistryObject<Item> PERCH_BUCKET = ITEMS.register("perch_bucket", () -> {
        return new FishBucketItem(TotEEntities.PERCH, () -> {
            return Fluids.WATER;
        }, Items.BUCKET, false, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1));});

    public static final RegistryObject<Item> CANDIRU_SPAWN_EGG = ITEMS.register("candiru_spawn_egg",
            () -> new FishSpawnEggItem(TotEEntities.CANDIRU, 0x9d7a93, 0xaba4c3, new Item.Properties()));
}
