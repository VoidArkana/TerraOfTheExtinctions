package net.voidarkana.terraoftheextinctions.common.entity.boats;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.voidarkana.terraoftheextinctions.registry.TotEEntities;
import net.voidarkana.terraoftheextinctions.registry.TotEItems;

public class TotEChestBoatEntity extends ChestBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.INT);

    public TotEChestBoatEntity(EntityType<? extends ChestBoat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public TotEChestBoatEntity(Level pLevel, double pX, double pY, double pZ) {
        this(TotEEntities.MOD_CHEST_BOAT.get(), pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        switch (getModVariant()) {
            case OLIVE -> {
                return TotEItems.OLIVE_CHEST_BOAT.get();
            }
        }
        return super.getDropItem();
    }

    public void setVariant(TotEBoatEntity.Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, TotEBoatEntity.Type.OLIVE.ordinal());
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getModVariant().getSerializedName());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type", 8)) {
            this.setVariant(TotEBoatEntity.Type.byName(pCompound.getString("Type")));
        }
    }

    public TotEBoatEntity.Type getModVariant() {
        return TotEBoatEntity.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }
}
