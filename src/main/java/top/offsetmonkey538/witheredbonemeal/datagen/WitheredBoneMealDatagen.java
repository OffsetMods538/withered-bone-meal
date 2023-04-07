package top.offsetmonkey538.witheredbonemeal.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import top.offsetmonkey538.witheredbonemeal.datagen.language.ModEnglishLanguageProvider;

public class WitheredBoneMealDatagen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(ModModelProvider::new);
        fabricDataGenerator.addProvider(ModRecipeProvider::new);

        // Language
        fabricDataGenerator.addProvider(ModEnglishLanguageProvider::new);
    }
}
