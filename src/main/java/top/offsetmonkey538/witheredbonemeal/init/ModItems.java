package top.offsetmonkey538.witheredbonemeal.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static top.offsetmonkey538.witheredbonemeal.WitheredBoneMeal.LOGGER;
import static top.offsetmonkey538.witheredbonemeal.WitheredBoneMeal.id;

public final class ModItems {
    private ModItems() {

    }


    public static final BlockItem WITHERED_BONE_BLOCK = register("withered_bone_block", new BlockItem(ModBlocks.WITHERED_BONE_BLOCK, new FabricItemSettings()));


    private static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, id(name), item);
    }

    @SuppressWarnings("UnstableApiUsage")
    private static void addItemsToItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register((entries) -> {
            entries.addBefore(Items.BASALT, WITHERED_BONE_BLOCK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register((entries -> {
            entries.addBefore(Items.BASALT, WITHERED_BONE_BLOCK);
        }));
    }

    public static void initialize() {
        LOGGER.debug("Initializing items");
        addItemsToItemGroups();
    }
}
