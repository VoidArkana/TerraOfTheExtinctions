package net.voidarkana.terraoftheextinctions.registry;

import net.minecraft.world.food.FoodProperties;

public class TotEFoods {

    public static final FoodProperties OLIVES = (new FoodProperties.Builder())
            .nutrition(2)
            .saturationMod(0.1F).build();

    public static final FoodProperties RAW_FISH =  (new FoodProperties.Builder())
            .nutrition(2)
            .saturationMod(0.1F).build();
}
