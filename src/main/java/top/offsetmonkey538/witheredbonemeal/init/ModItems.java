package top.offsetmonkey538.witheredbonemeal.init;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import top.offsetmonkey538.witheredbonemeal.item.WitheredBoneMealItem;

import static top.offsetmonkey538.witheredbonemeal.WitheredBoneMeal.LOGGER;
import static top.offsetmonkey538.witheredbonemeal.WitheredBoneMeal.id;

public final class ModItems {
    private ModItems() {

    }


    public static final BlockItem            WITHERED_BONE_BLOCK = register("withered_bone_block", new BlockItem(ModBlocks.WITHERED_BONE_BLOCK, new Item.Settings()));
    public static final Item                 WITHERED_BONE       = register("withered_bone",       new Item(new Item.Settings()));
    public static final WitheredBoneMealItem WITHERED_BONE_MEAL  = register("withered_bone_meal",  new WitheredBoneMealItem(new Item.Settings()));


    private static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, id(name), item);
    }

    private static void addItemsToItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> entries.addBefore(Items.BASALT, WITHERED_BONE_BLOCK));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addBefore(Items.BASALT, WITHERED_BONE_BLOCK));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> entries.addAfter(Items.BONE, WITHERED_BONE));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.addAfter(Items.BONE_MEAL, WITHERED_BONE_MEAL));
    }

    public static void initialize() {
        LOGGER.debug("Initializing items");
        addItemsToItemGroups();

        DispenserBlock.registerBehavior(WITHERED_BONE_MEAL, new FallibleItemDispenserBehavior() {
            @Override
            protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                ServerWorld world = pointer.world();
                BlockPos pos = pointer.pos().offset(pointer.state().get(DispenserBlock.FACING));
                BlockState state = world.getBlockState(pos);

                if (!WitheredBoneMealItem.useOnGround(stack, world, pos, state)) this.setSuccess(false);

                return stack;
            }
        });
    }
}
