package net.voidarkana.terraoftheextinctions.registry;

import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.common.entity.TotEBoatEntity;
import net.voidarkana.terraoftheextinctions.common.item.TotEBoatItem;

public class TotEItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TerraOfTheExtinctions.MOD_ID);


    public static final RegistryObject<Item> OLIVES = ITEMS.register("olives",
            () -> new Item(new Item.Properties().food(TotEFoods.OLIVES)));

    //Olive
    public static final RegistryObject<Item> OLIVE_SIGN = ITEMS.register("olive_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), TotEBlocks.OLIVE_SIGN.get(), TotEBlocks.OLIVE_WALL_SIGN.get()));

    public static final RegistryObject<Item> OLIVE_HANGING_SIGN = ITEMS.register("olive_hanging_sign",
            () -> new HangingSignItem(TotEBlocks.OLIVE_HANGING_SIGN.get(), TotEBlocks.OLIVE_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> OLIVE_BOAT = ITEMS.register("olive_boat",
            () -> new TotEBoatItem(false, TotEBoatEntity.Type.OLIVE, new Item.Properties()));
    public static final RegistryObject<Item> OLIVE_CHEST_BOAT = ITEMS.register("olive_chest_boat",
            () -> new TotEBoatItem(true, TotEBoatEntity.Type.OLIVE, new Item.Properties()));

}
