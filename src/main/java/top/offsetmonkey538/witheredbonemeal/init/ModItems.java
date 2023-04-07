package top.offsetmonkey538.witheredbonemeal.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import top.offsetmonkey538.witheredbonemeal.item.WitheredBoneMealItem;

import static top.offsetmonkey538.witheredbonemeal.WitheredBoneMeal.LOGGER;
import static top.offsetmonkey538.witheredbonemeal.WitheredBoneMeal.id;

public final class ModItems {
    private ModItems() {

    }


    public static final BlockItem            WITHERED_BONE_BLOCK = register("withered_bone_block", new BlockItem(ModBlocks.WITHERED_BONE_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item                 WITHERED_BONE       = register("withered_bone",       new Item(                                    new FabricItemSettings().group(ItemGroup.MISC)));
    public static final WitheredBoneMealItem WITHERED_BONE_MEAL  = register("withered_bone_meal",  new WitheredBoneMealItem(                    new FabricItemSettings().group(ItemGroup.MATERIALS)));


    private static <T extends Item> T register(String name, T item) {
        return Registry.register(Registry.ITEM, id(name), item);
    }

    public static void initialize() {
        LOGGER.debug("Initializing items");

        DispenserBlock.registerBehavior(WITHERED_BONE_MEAL, new FallibleItemDispenserBehavior() {
            @Override
            protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                ServerWorld world = pointer.getWorld();
                BlockPos pos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
                BlockState state = world.getBlockState(pos);

                if (!WitheredBoneMealItem.useOnGround(stack, world, pos, state)) this.setSuccess(false);

                return stack;
            }
        });
    }
}
