package net.okamiz.atlasinfection.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.okamiz.atlasinfection.block.ModBlocks;
import net.okamiz.atlasinfection.effect.ModEffects;
import net.okamiz.atlasinfection.util.ModTags;

public class LivingMechanicBlock extends Block {
    public LivingMechanicBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if(entity instanceof LivingEntity livingEntity){
            livingEntity.addEffect(new MobEffectInstance(ModEffects.INFECTED_EFFECT, 100, 0));
        }
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    protected void propagatethisBlock(BlockState state, ServerLevel level, BlockPos pos, RandomSource random){
        BlockState blockstate = this.defaultBlockState();
        if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
        if (!level.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading


        BlockPos blockpos = pos.offset(random.nextIntBetweenInclusive(-1,1), random.nextIntBetweenInclusive(-1,1), random.nextIntBetweenInclusive(-1,1));
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


        if(level.getBlockState(blockpos).is(BlockTags.LEAVES)){
            level.setBlockAndUpdate(blockpos, ModBlocks.LIVING_LEAVES.get().defaultBlockState());
        }
        else if (!level.getBlockState(blockpos).is(ModTags.Blocks.NON_INFECTABLE_BLOCKS)) {
            level.setBlockAndUpdate(blockpos, blockstate);
        }

    }
}
