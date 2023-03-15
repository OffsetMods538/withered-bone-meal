package top.offsetmonkey538.witheredbonemeal.item;

import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import top.offsetmonkey538.witheredbonemeal.mixin.CoralBlockAccessor;
import top.offsetmonkey538.witheredbonemeal.mixin.CoralBlockBlockAccessor;
import top.offsetmonkey538.witheredbonemeal.mixin.CoralFanBlockAccessor;

public class WitheredBoneMealItem extends Item {

    private static final Random random = Random.create();

    public WitheredBoneMealItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        final ItemStack stack = context.getStack();
        final World world = context.getWorld();
        final BlockPos pos = context.getBlockPos();
        final BlockState state = world.getBlockState(pos);

        if (useOnGround(stack, world, pos, state)) return ActionResult.success(world.isClient());
        return ActionResult.PASS;
    }

    public static boolean useOnGround(ItemStack stack, World world, BlockPos pos, BlockState state) {
        final Block block = state.getBlock();


        if (block instanceof WitherRoseBlock)            return false;

        if (block instanceof TallFlowerBlock)            return replaceTallBlockWith(world, stack, pos, state, Blocks.WITHER_ROSE);
        if (block instanceof FlowerBlock)                return replaceWith(world, stack, pos, Blocks.WITHER_ROSE);
        if (block instanceof NyliumBlock)                return replaceWith(world, stack, pos, Blocks.NETHERRACK, 0.5);
        if (block instanceof GrassBlock
                || block instanceof MyceliumBlock
                || block instanceof RootedDirtBlock
                || state.isOf(Blocks.PODZOL))            return replaceWith(world, stack, pos, Blocks.DIRT, 0.5);
        if (state.isOf(Blocks.MUDDY_MANGROVE_ROOTS))     return replaceWith(world, stack, pos, Blocks.MUD);

        if (block instanceof NetherWartBlock)            return handleNetherWart(world, stack, pos, state);

        if (block instanceof CropBlock cropBlock)        return handleAgedBlock(world, stack, pos, state, cropBlock.getAgeProperty());
        if (block instanceof StemBlock)                  return handleAgedBlock(world, stack, pos, state, Properties.AGE_7);

        if (block instanceof CoralFanBlock coralBlock)   return replaceWith(world, stack, pos, ((CoralFanBlockAccessor) coralBlock).getDeadCoralBlock().getDefaultState().with(Properties.WATERLOGGED, state.get(Properties.WATERLOGGED)));
        if (block instanceof CoralBlock coralBlock)      return replaceWith(world, stack, pos, ((CoralBlockAccessor) coralBlock).getDeadCoralBlock().getDefaultState().with(Properties.WATERLOGGED, state.get(Properties.WATERLOGGED)));
        if (block instanceof CoralBlockBlock coralBlock) return replaceWith(world, stack, pos, ((CoralBlockBlockAccessor) coralBlock).getDeadCoralBlock().getDefaultState());


        if (block instanceof TallPlantBlock)             return replaceTallBlockWith(world, stack, pos, state, Blocks.AIR);
        if (block instanceof AbstractPlantPartBlock)     return replaceWith(world, stack, pos, Blocks.AIR);
        if (block instanceof MangroveRootsBlock)         return replaceWith(world, stack, pos, Blocks.AIR);
        if (block instanceof HangingRootsBlock)          return replaceWith(world, stack, pos, Blocks.AIR);
        if (block instanceof SugarCaneBlock)             return replaceWith(world, stack, pos, Blocks.AIR);
        if (block instanceof CactusBlock)                return replaceWith(world, stack, pos, Blocks.AIR);
        if (block instanceof BigDripleafBlock)           return replaceWith(world, stack, pos, Blocks.AIR);
        if (block instanceof BigDripleafStemBlock)       return replaceWith(world, stack, pos, Blocks.AIR);
        if (block instanceof BambooBlock)                return replaceWith(world, stack, pos, Blocks.AIR);
        if (block instanceof BambooSaplingBlock)         return replaceWith(world, stack, pos, Blocks.AIR);
        if (block instanceof GlowLichenBlock)            return replaceWith(world, stack, pos, Blocks.AIR);
        if (block instanceof VineBlock)                  return replaceWith(world, stack, pos, Blocks.AIR);
        if (block instanceof PlantBlock)                 return replaceWith(world, stack, pos, Blocks.AIR);


        return false;
    }

    private static boolean handleNetherWart(World world, ItemStack stack, BlockPos pos, BlockState state) {
        int newAge = state.get(Properties.AGE_3) + 1;
        if (newAge > Properties.AGE_3_MAX) return false;

        return replaceWith(world, stack, pos, state.with(Properties.AGE_3, newAge));
    }

    private static boolean handleAgedBlock(World world, ItemStack stack, BlockPos pos, BlockState state, IntProperty ageProperty) {
        int newAge = state.get(ageProperty) - 1;
        if (newAge < 0) return replaceWith(world, stack, pos, Blocks.AIR);

        return replaceWith(world, stack, pos, state.with(ageProperty, newAge));
    }


    private static boolean replaceTallBlockWith(World world, ItemStack stack, BlockPos pos, BlockState state, Block replaceWith) {
        if (state.get(TallPlantBlock.HALF).equals(DoubleBlockHalf.UPPER)) pos = pos.down();
        return replaceWith(world, stack, pos, replaceWith);
    }

    private static boolean replaceWith(World world, ItemStack stack, BlockPos pos, Block replaceWith) {
        return replaceWith(world, stack, pos, replaceWith, 0);
    }
    private static boolean replaceWith(World world, ItemStack stack, BlockPos pos, Block replaceWith, double particleYOffset) {
        return replaceWith(world, stack, pos, replaceWith.getDefaultState(), particleYOffset);
    }
    private static boolean replaceWith(World world, ItemStack stack, BlockPos pos, BlockState replaceWith) {
        return replaceWith(world, stack, pos, replaceWith, 0);
    }
    private static boolean replaceWith(World world, ItemStack stack, BlockPos pos, BlockState replaceWith, double particleYOffset) {
        addSmokeParticles(world, Vec3d.of(pos).add(0, particleYOffset, 0));
        setBlock(world, pos, replaceWith);
        playUseSound(world, pos);

        stack.decrement(1);
        return true;
    }

    private static void setBlock(World world, BlockPos pos, BlockState newState) {
        if (world.isClient()) return;
        world.setBlockState(pos, newState);
    }

    private static void playUseSound(World world, BlockPos pos) {
        if (world.isClient()) return;
        world.playSound(null, pos, SoundEvents.ENTITY_WITHER_SHOOT, SoundCategory.BLOCKS, 0.1f, 1.25f);
    }

    private static void addSmokeParticles(World world, Vec3d pos) {
        if (!world.isClient()) return;
        for (int i = 0; i < 64; i++) {
            double velX = random.nextGaussian() * 0.02;
            double velY = random.nextGaussian() * 0.02;
            double velZ = random.nextGaussian() * 0.02;

            double x = pos.getX() + random.nextDouble();
            double y = pos.getY() + random.nextDouble();
            double z = pos.getZ() + random.nextDouble();

            world.addParticle(ParticleTypes.SMOKE, x, y, z, velX, velY, velZ);
        }
    }
}
