package top.offsetmonkey538.witheredbonemeal;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
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

		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if (!EntityType.WITHER_SKELETON.getLootTableId().equals(id) || !source.isBuiltin()) return;
			tableBuilder.pool(LootPool.builder().with(ItemEntry.builder(ModItems.WITHERED_BONE)).rolls(UniformLootNumberProvider.create(1, 3)));
		});
	}

	public static Identifier id(String name) {
		return new Identifier(MOD_ID, name);
	}
}
