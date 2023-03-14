package top.offsetmonkey538.witheredbonemeal.init;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static top.offsetmonkey538.witheredbonemeal.WitheredBoneMeal.*;

public final class ModBlocks {
    private ModBlocks() {

    }





    private static <T extends Block> T register(String name, T block) {
        return Registry.register(Registries.BLOCK, id(name), block);
    }

    public static void initialize() {
        LOGGER.debug("Initializing blocks");
    }
}
