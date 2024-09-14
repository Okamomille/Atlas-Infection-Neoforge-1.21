package net.okamiz.atlasinfection.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.okamiz.atlasinfection.block.ModBlocks;
import net.okamiz.atlasinfection.util.ModTags;

public class AtlasHeart extends Block {
    public AtlasHeart(Properties properties) {
        super(properties);
    }


    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        BlockPos blockpos = pos.offset(0,-1,0);
        level.setBlockAndUpdate(blockpos, ModBlocks.LIVING_BLOCK.get().defaultBlockState());
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
        if (!level.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading



        for (int i = 0; i < 10; i++) {
            BlockPos blockpos = pos.offset(random.nextIntBetweenInclusive(-2,2), random.nextIntBetweenInclusive(-2,2), random.nextIntBetweenInclusive(-2,2));

            if (level.getBlockState(blockpos).is(BlockTags.FLOWERS) || level.getBlockState(blockpos).is(Blocks.SHORT_GRASS)
                    || level.getBlockState(blockpos).is(Blocks.TALL_GRASS)) {
                level.setBlockAndUpdate(blockpos, ModBlocks.LIVING_ROOTS.get().defaultBlockState());
            }

            if (level.getBlockState(blockpos.above()).is(BlockTags.FLOWERS) || level.getBlockState(blockpos.above()).is(Blocks.SHORT_GRASS)
                    || level.getBlockState(blockpos.above()).is(Blocks.TALL_GRASS)) {
                level.setBlockAndUpdate(blockpos.above(), ModBlocks.LIVING_ROOTS.get().defaultBlockState());
            }

            if(level.getBlockState(blockpos.above()).is(Blocks.SNOW)){
                level.setBlockAndUpdate(blockpos.above(), Blocks.AIR.defaultBlockState());
            }else if(level.getBlockState(blockpos).is(Blocks.SNOW)){
                level.setBlockAndUpdate(blockpos, Blocks.AIR.defaultBlockState());
            }

            if (!level.getBlockState(blockpos).is(ModTags.Blocks.NON_INFECTABLE_BLOCKS)) {
                    level.setBlockAndUpdate(blockpos, ModBlocks.LIVING_BLOCK.get().defaultBlockState());
            }
            if (level.getBlockState(blockpos).isAir()) {
                level.setBlockAndUpdate(blockpos, ModBlocks.HARD_LIVING_BLOCK.get().defaultBlockState());
            }
        }

    }
}
