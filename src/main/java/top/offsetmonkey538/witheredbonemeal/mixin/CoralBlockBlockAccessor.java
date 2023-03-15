package top.offsetmonkey538.witheredbonemeal.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.CoralBlockBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CoralBlockBlock.class)
public interface CoralBlockBlockAccessor {

    @Accessor
    Block getDeadCoralBlock();
}
