package top.offsetmonkey538.witheredbonemeal.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;
import top.offsetmonkey538.witheredbonemeal.init.ModBlocks;
import top.offsetmonkey538.witheredbonemeal.init.ModItems;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerAxisRotated(ModBlocks.WITHERED_BONE_BLOCK, TexturedModel.CUBE_COLUMN);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.WITHERED_BONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WITHERED_BONE_MEAL, Models.GENERATED);
    }
}
