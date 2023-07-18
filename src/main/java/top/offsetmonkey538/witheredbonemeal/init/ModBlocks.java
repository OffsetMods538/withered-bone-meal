package top.offsetmonkey538.witheredbonemeal.init;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

import static top.offsetmonkey538.witheredbonemeal.WitheredBoneMeal.*;

public final class ModBlocks {
    private ModBlocks() {

    }


    public static final Block WITHERED_BONE_BLOCK = register("withered_bone_block", new PillarBlock(FabricBlockSettings.create().mapColor(MapColor.DEEPSLATE_GRAY).solid().requiresTool().strength(2.0f).sounds(BlockSoundGroup.BONE)));


    private static <T extends Block> T register(String name, T block) {
        return Registry.register(Registries.BLOCK, id(name), block);
    }

    public static void initialize() {
        LOGGER.debug("Initializing blocks");
    }
}
