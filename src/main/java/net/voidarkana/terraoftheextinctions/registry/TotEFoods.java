package net.voidarkana.terraoftheextinctions.registry;

import net.minecraft.world.food.FoodProperties;

public class TotEFoods {

    public static final FoodProperties OLIVES = (new FoodProperties.Builder())
            .nutrition(2)
            .saturationMod(0.1F).build();

    public static final FoodProperties RAW_FISH =  (new FoodProperties.Builder())
            .nutrition(2)
            .saturationMod(0.1F).build();

    public static final FoodProperties COOKED_FISH =  (new FoodProperties.Builder())
            .nutrition(6)
            .saturationMod(0.8F).build();

    public static final FoodProperties GRAPES = (new FoodProperties.Builder())
            .nutrition(3)
            .saturationMod(0.2F).build();
}
