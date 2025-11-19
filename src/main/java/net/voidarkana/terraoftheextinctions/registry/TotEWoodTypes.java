package net.voidarkana.terraoftheextinctions.registry;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;

public class TotEWoodTypes {
    public static final WoodType OLIVE = WoodType.register(new WoodType(TerraOfTheExtinctions.MOD_ID + ":olive", BlockSetType.OAK));
    public static final WoodType GRAPE = WoodType.register(new WoodType(TerraOfTheExtinctions.MOD_ID + ":grape", BlockSetType.OAK));

}
