package top.offsetmonkey538.witheredbonemeal.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.fabric.api.loot.v2.FabricLootTableBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.ReloadableRegistries;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import top.offsetmonkey538.witheredbonemeal.init.ModItems;

@Mixin(ReloadableRegistries.class)
abstract class ReloadableRegistriesMixin {
    @ModifyArg(
            method = "method_58286",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/registry/MutableRegistry;add(Lnet/minecraft/registry/RegistryKey;Ljava/lang/Object;Lnet/minecraft/registry/entry/RegistryEntryInfo;)Lnet/minecraft/registry/entry/RegistryEntry$Reference;"),
            index = 1
    )
    private static Object modifyLootTable(Object value, @Local(argsOnly = true) Identifier lootTableId) {
        if (!(value instanceof LootTable lootTable)) return value;
        if (!RegistryKey.of(RegistryKeys.LOOT_TABLE, lootTableId).equals(EntityType.WITHER_SKELETON.getLootTableId())) return value;

        return FabricLootTableBuilder.copyOf(lootTable).pool(LootPool.builder().with(ItemEntry.builder(ModItems.WITHERED_BONE)).rolls(UniformLootNumberProvider.create(1, 3))).build();
    }
}
