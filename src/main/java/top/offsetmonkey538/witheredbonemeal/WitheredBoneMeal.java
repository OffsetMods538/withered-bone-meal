package top.offsetmonkey538.witheredbonemeal;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.offsetmonkey538.witheredbonemeal.init.ModBlocks;
import top.offsetmonkey538.witheredbonemeal.init.ModItems;

public class WitheredBoneMeal implements ModInitializer {
	public static final String MOD_ID = "withered-bone-meal";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.initialize();
		ModItems.initialize();
	}

	public static Identifier id(String name) {
		return new Identifier(MOD_ID, name);
	}
}
