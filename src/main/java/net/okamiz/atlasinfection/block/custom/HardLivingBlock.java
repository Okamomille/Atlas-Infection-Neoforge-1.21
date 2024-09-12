package net.okamiz.atlasinfection.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.okamiz.atlasinfection.util.ModTags;


public class HardLivingBlock extends Block {
    public HardLivingBlock(Properties properties) {
        super(properties);
    }



    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockState blockstate = this.defaultBlockState();
            if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            if (!level.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading



        for (int i = 0; i < 2; i++) {
            BlockPos blockpos = pos.offset(random.nextIntBetweenInclusive(-1,1), random.nextIntBetweenInclusive(-1,1), random.nextIntBetweenInclusive(-1,1));

            if (level.getBlockState(blockpos).is(BlockTags.FLOWERS) || level.getBlockState(blockpos).is(Blocks.SHORT_GRASS)
                    || level.getBlockState(blockpos).is(Blocks.TALL_GRASS) || level.getBlockState(blockpos).is(Blocks.SNOW)) {
                level.setBlockAndUpdate(blockpos, Blocks.AIR.defaultBlockState());
            }

            if (level.getBlockState(blockpos.above()).is(BlockTags.FLOWERS) || level.getBlockState(blockpos.above()).is(Blocks.SHORT_GRASS)
                    || level.getBlockState(blockpos.above()).is(Blocks.TALL_GRASS) || level.getBlockState(blockpos.above()).is(Blocks.SNOW)) {

                level.setBlockAndUpdate(blockpos.above(), Blocks.AIR.defaultBlockState());
            }

            else if (!level.getBlockState(blockpos).is(ModTags.Blocks.NON_INFECTABLE_BLOCKS)) {
                level.setBlockAndUpdate(blockpos, blockstate);
            }
        }

    }

}


