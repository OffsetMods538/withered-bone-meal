package top.offsetmonkey538.witheredbonemeal;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.game.minecraft.McVersion;
import net.fabricmc.loader.impl.game.minecraft.McVersionLookup;
import net.fabricmc.loader.impl.game.minecraft.MinecraftGameProvider;
import net.minecraft.SharedConstants;
import net.minecraft.resource.ResourceType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.offsetmonkey538.monkeylib538.utils.IdentifierUtils;
import top.offsetmonkey538.witheredbonemeal.init.ModBlocks;
import top.offsetmonkey538.witheredbonemeal.init.ModItems;

public class WitheredBoneMeal implements ModInitializer {
	public static final String MOD_ID = "withered-bone-meal";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.initialize();
		ModItems.initialize();


		final int dataPackFormat = SharedConstants.getGameVersion().getResourceVersion(ResourceType.SERVER_DATA);
		final String packName;

		if (dataPackFormat >= 18 && dataPackFormat <= 26) packName = "26";
		else if (dataPackFormat == 41) packName = "41";
		else if (dataPackFormat == 48) packName = "48";
		else packName = "26";

        FabricLoader.getInstance().getModContainer(MOD_ID)
				.map(container -> ResourceManagerHelper.registerBuiltinResourcePack(id(packName),
						container, Text.literal("Withered Bone Meal"), ResourcePackActivationType.ALWAYS_ENABLED))
				.filter(success -> !success).ifPresent(success -> LOGGER.warn("Could not register built-in data pack."));
	}

	public static Identifier id(String name) {
		return IdentifierUtils.INSTANCE.of(MOD_ID, name);
	}
}
