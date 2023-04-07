package top.offsetmonkey538.witheredbonemeal.init;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

import static top.offsetmonkey538.witheredbonemeal.WitheredBoneMeal.*;

public final class ModBlocks {
    private ModBlocks() {

    }


    public static final Block WITHERED_BONE_BLOCK = register("withered_bone_block", new PillarBlock(FabricBlockSettings.of(Material.STONE, MapColor.DEEPSLATE_GRAY).requiresTool().strength(2.0f).sounds(BlockSoundGroup.BONE)));


    private static <T extends Block> T register(String name, T block) {
        return Registry.register(Registry.BLOCK, id(name), block);
    }

    public static void initialize() {
        LOGGER.debug("Initializing blocks");
    }
}
