package net.voidarkana.terraoftheextinctions.data.providers.loot;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.terraoftheextinctions.registry.TotEBlocks;
import net.voidarkana.terraoftheextinctions.registry.TotEItems;

import java.util.Set;

public class TotEBlockLootTableProvider extends BlockLootSubProvider {

    public TotEBlockLootTableProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropWhenSilkTouch(TotEBlocks.PERCH_ROE.get());

        this.dropSelf(TotEBlocks.OLIVE_PLANKS.get());
        this.dropSelf(TotEBlocks.OLIVE_STAIRS.get());
        this.dropSelf(TotEBlocks.OLIVE_FENCE.get());
        this.dropSelf(TotEBlocks.OLIVE_FENCE_GATE.get());
        this.dropSelf(TotEBlocks.OLIVE_BUTTON.get());
        this.dropSelf(TotEBlocks.OLIVE_PRESSURE_PLATE.get());
        this.dropSelf(TotEBlocks.OLIVE_TRAPDOOR.get());
        this.add(TotEBlocks.OLIVE_SLAB.get(),
                block -> createSlabItemTable(TotEBlocks.OLIVE_SLAB.get()));
        this.add(TotEBlocks.OLIVE_DOOR.get(),
                block -> createDoorTable(TotEBlocks.OLIVE_DOOR.get()));

        this.add(TotEBlocks.OLIVE_SIGN.get(),
                block -> createSingleItemTable(TotEItems.OLIVE_SIGN.get()));
        this.add(TotEBlocks.OLIVE_WALL_SIGN.get(),
                block -> createSingleItemTable(TotEItems.OLIVE_SIGN.get()));
        this.add(TotEBlocks.OLIVE_HANGING_SIGN.get(),
                block -> createSingleItemTable(TotEItems.OLIVE_HANGING_SIGN.get()));
        this.add(TotEBlocks.OLIVE_WALL_HANGING_SIGN.get(),
                block -> createSingleItemTable(TotEItems.OLIVE_HANGING_SIGN.get()));

        this.dropSelf(TotEBlocks.OLIVE_LOG.get());
        this.dropSelf(TotEBlocks.STRIPPED_OLIVE_LOG.get());
        this.dropSelf(TotEBlocks.OLIVE_WOOD.get());
        this.dropSelf(TotEBlocks.STRIPPED_OLIVE_WOOD.get());

        this.dropSelf(TotEBlocks.OLIVE_SAPLING.get());
        this.add(TotEBlocks.POTTED_OLIVE_SAPLING.get(),
                createPotFlowerItemTable(TotEBlocks.OLIVE_SAPLING.get()));

        this.add(TotEBlocks.OLIVE_LEAVES.get(),
                block -> createLeavesDrops(TotEBlocks.OLIVE_LEAVES.get(), TotEBlocks.OLIVE_SAPLING.get(), 0.05F, 0.0625F, 0.083333336F, 0.1F));

        this.dropSelf(TotEBlocks.SALT_BLOCK.get());

        this.add(TotEBlocks.SALT_CRYSTAL.get(), (p_252201_) -> {
            return createSilkTouchDispatchTable(p_252201_, LootItem.lootTableItem(TotEItems.SALT.get())
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
                    .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                    .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                    .otherwise(this.applyExplosionDecay(p_252201_, LootItem.lootTableItem(TotEItems.SALT.get())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))));
        });

        this.dropWhenSilkTouch(TotEBlocks.SMALL_SALT_BUD.get());
        this.dropWhenSilkTouch(TotEBlocks.MEDIUM_SALT_BUD.get());
        this.dropWhenSilkTouch(TotEBlocks.LARGE_SALT_BUD.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return TotEBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
