package top.offsetmonkey538.witheredbonemeal.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.CoralFanBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CoralFanBlock.class)
public interface CoralFanBlockAccessor {

    @Accessor
    Block getDeadCoralBlock();
}
