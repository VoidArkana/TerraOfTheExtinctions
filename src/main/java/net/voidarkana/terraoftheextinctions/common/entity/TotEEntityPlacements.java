package net.voidarkana.terraoftheextinctions.common.entity;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Candiru;
import net.voidarkana.terraoftheextinctions.registry.TotEEntities;

public class TotEEntityPlacements {
    public  static void entityPlacement() {
        SpawnPlacements.register(TotEEntities.BLEAK.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(TotEEntities.PERCH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(TotEEntities.CANDIRU.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Candiru::checkCandiruSpawnConditions);
    }
}
