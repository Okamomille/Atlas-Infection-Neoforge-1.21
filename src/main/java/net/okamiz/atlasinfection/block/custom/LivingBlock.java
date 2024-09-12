package net.okamiz.atlasinfection.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;

public class LivingBlock extends Block {
    public LivingBlock(Properties properties) {
        super(properties);
    }



    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockState blockstate = this.defaultBlockState();
            if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            if (!level.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading



        for (int i = 0; i < 4; i++) {
            BlockPos blockpos = pos.offset(random.nextInt(5) - 1, random.nextInt(5) - 1, random.nextInt(5) - 1);
            if (!level.getBlockState(blockpos).isAir()) {
                level.setBlockAndUpdate(blockpos, blockstate);
        }
        }

    }

}


