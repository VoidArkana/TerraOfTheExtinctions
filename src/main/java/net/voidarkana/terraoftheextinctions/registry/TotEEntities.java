package net.voidarkana.terraoftheextinctions.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Bleak;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Candiru;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Perch;
import net.voidarkana.terraoftheextinctions.common.entity.boats.TotEBoatEntity;
import net.voidarkana.terraoftheextinctions.common.entity.boats.TotEChestBoatEntity;

public class TotEEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TerraOfTheExtinctions.MOD_ID);

    public static final RegistryObject<EntityType<TotEBoatEntity>> MOD_BOAT =
            ENTITY_TYPES.register("mod_boat", () -> EntityType.Builder.<TotEBoatEntity>of(TotEBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_boat"));
    public static final RegistryObject<EntityType<TotEChestBoatEntity>> MOD_CHEST_BOAT =
            ENTITY_TYPES.register("mod_chest_boat", () -> EntityType.Builder.<TotEChestBoatEntity>of(TotEChestBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_chest_boat"));

    public static final RegistryObject<EntityType<Bleak>> BLEAK =
            ENTITY_TYPES.register("bleak",
                    () -> EntityType.Builder.of(Bleak::new, MobCategory.WATER_AMBIENT)
                            .sized(0.45f, 0.3f)
                            .build(new ResourceLocation(TerraOfTheExtinctions.MOD_ID, "bleak").toString()));

    public static final RegistryObject<EntityType<Perch>> PERCH =
            ENTITY_TYPES.register("perch",
                    () -> EntityType.Builder.of(Perch::new, MobCategory.WATER_AMBIENT)
                            .sized(0.55f, 0.4f)
                            .build(new ResourceLocation(TerraOfTheExtinctions.MOD_ID, "perch").toString()));

    public static final RegistryObject<EntityType<Candiru>> CANDIRU =
            ENTITY_TYPES.register("candiru",
                    () -> EntityType.Builder.of(Candiru::new, MobCategory.MONSTER)
                            .sized(0.5f, 0.2f)
                            .build(new ResourceLocation(TerraOfTheExtinctions.MOD_ID, "candiru").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
