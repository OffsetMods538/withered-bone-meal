package top.offsetmonkey538.witheredbonemeal.init;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static top.offsetmonkey538.witheredbonemeal.WitheredBoneMeal.LOGGER;
import static top.offsetmonkey538.witheredbonemeal.WitheredBoneMeal.id;

public final class ModItems {
    private ModItems() {

    }





    private static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, id(name), item);
    }

    public static void initialize() {
        LOGGER.debug("Initializing items");
    }
}
