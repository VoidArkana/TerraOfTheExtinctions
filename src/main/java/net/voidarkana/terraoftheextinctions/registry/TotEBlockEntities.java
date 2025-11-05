package net.voidarkana.terraoftheextinctions.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.common.blockentity.TotEHangingSignBlockEntity;
import net.voidarkana.terraoftheextinctions.common.blockentity.TotESignBlockEntity;

public class TotEBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TerraOfTheExtinctions.MOD_ID);

    public static final RegistryObject<BlockEntityType<TotESignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITIES.register( "mod_sign", () ->
                    BlockEntityType.Builder.of(TotESignBlockEntity::new,
                                    TotEBlocks.OLIVE_SIGN.get(),
                                    TotEBlocks.OLIVE_WALL_SIGN.get())
                            .build(null));

    public static final RegistryObject<BlockEntityType<TotEHangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register( "mod_hanging_sign", () ->
                    BlockEntityType.Builder.of(TotEHangingSignBlockEntity::new,
                                    TotEBlocks.OLIVE_HANGING_SIGN.get(),
                                    TotEBlocks.OLIVE_WALL_HANGING_SIGN.get())
                            .build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
