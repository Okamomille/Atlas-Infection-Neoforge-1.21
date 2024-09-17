package net.okamiz.atlasinfection.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.okamiz.atlasinfection.block.ModBlocks;
import net.okamiz.atlasinfection.effect.ModEffects;
import net.okamiz.atlasinfection.util.ModTags;

public class LivingMechanicBlock extends Block {
    public static final BooleanProperty CAN_PROPAGATE = BooleanProperty.create("can_propagate");
    public LivingMechanicBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(CAN_PROPAGATE, true));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CAN_PROPAGATE);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if(entity instanceof LivingEntity livingEntity){
            livingEntity.addEffect(new MobEffectInstance(ModEffects.INFECTED_EFFECT, 100, 0));
        }
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(CAN_PROPAGATE);
    }

    protected void propagatethisBlock(BlockState state, ServerLevel level, BlockPos pos, RandomSource random){
        BlockState blockstate = this.defaultBlockState();
        if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
        if (!level.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
        BlockPos blockpos = pos.offset(random.nextIntBetweenInclusive(-1,1), random.nextIntBetweenInclusive(-1,1), random.nextIntBetweenInclusive(-1,1));


        propagate(blockpos, level, pos, random);

        if(!level.getBlockState(blockpos).is(ModTags.Blocks.NON_INFECTABLE_BLOCKS) && !level.getBlockState(blockpos).is(BlockTags.LOGS)
                && !level.getBlockState(blockpos).is(BlockTags.LEAVES)) {
            level.setBlockAndUpdate(blockpos, blockstate);
        }
    }
    protected void propagateLivingBlock(BlockState state, ServerLevel level, BlockPos pos, RandomSource random){
        BlockState blockstate = this.defaultBlockState();
        if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
        if (!level.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
        BlockPos blockpos = pos.offset(random.nextIntBetweenInclusive(-1,1), random.nextIntBetweenInclusive(-1,1), random.nextIntBetweenInclusive(-1,1));

        propagate(blockpos, level, pos, random);

        if (!level.getBlockState(blockpos).is(ModTags.Blocks.NON_INFECTABLE_BLOCKS) && !level.getBlockState(blockpos).is(BlockTags.LOGS)
                && !level.getBlockState(blockpos).is(BlockTags.LEAVES) && !level.getBlockState(blockpos).isAir()) {
            level.setBlockAndUpdate(blockpos, ModBlocks.LIVING_BLOCK.get().defaultBlockState());
        }
    }


    protected void propagate(BlockPos blockpos, ServerLevel level, BlockPos pos, RandomSource random){

        if (level.getBlockState(blockpos).is(BlockTags.FLOWERS)|| level.getBlockState(blockpos).is(Blocks.FERN)|| level.getBlockState(blockpos).is(Blocks.LARGE_FERN)) {
            level.setBlockAndUpdate(blockpos, ModBlocks.LIVING_FLOWERS.get().defaultBlockState());
        }
        if (level.getBlockState(blockpos.above()).is(BlockTags.FLOWERS) || level.getBlockState(blockpos).is(Blocks.FERN)|| level.getBlockState(blockpos).is(Blocks.LARGE_FERN)) {
            level.setBlockAndUpdate(blockpos.above(), ModBlocks.LIVING_FLOWERS.get().defaultBlockState());
        }

        if (level.getBlockState(blockpos).is(Blocks.SHORT_GRASS) || level.getBlockState(blockpos).is(Blocks.TALL_GRASS)) {
            level.setBlockAndUpdate(blockpos, ModBlocks.LIVING_ROOTS.get().defaultBlockState());
        }
        if (level.getBlockState(blockpos.above()).is(Blocks.SHORT_GRASS) || level.getBlockState(blockpos.above()).is(Blocks.TALL_GRASS)) {
            level.setBlockAndUpdate(blockpos.above(), ModBlocks.LIVING_ROOTS.get().defaultBlockState());
        }


        if (level.getBlockState(blockpos.above()).is(ModTags.Blocks.IS_MASS_BLOCKS_VERTICAL)) {
            for (int i = 25; i > 2; i--) {
                if (level.getBlockState(blockpos.above(i)).is(ModTags.Blocks.IS_MASS_BLOCKS_VERTICAL)) {
                    level.setBlockAndUpdate(blockpos.above(i), Blocks.AIR.defaultBlockState());
                }
            }
            for (int i = 2; i > 0; i--) {
                if (level.getBlockState(blockpos.above(i)).is(ModTags.Blocks.IS_MASS_BLOCKS_VERTICAL)) {
                    level.setBlockAndUpdate(blockpos.above(i), ModBlocks.LIVING_FLOWERS.get().defaultBlockState());
                }
            }
        }
        if (level.getBlockState(blockpos.above()).is(Blocks.KELP) || level.getBlockState(blockpos.above()).is(Blocks.KELP_PLANT)) {
            for (int i = 25; i > 0; i--) {
                if (level.getBlockState(blockpos.above(i)).is(Blocks.KELP) || level.getBlockState(blockpos.above(i)).is(Blocks.KELP_PLANT)) {
                    level.setBlockAndUpdate(blockpos.above(i), ModBlocks.LIVING_BLOCK.get().defaultBlockState());
                }
            }
        }

        if(level.getBlockState(blockpos).is(BlockTags.RAILS) && !level.getBlockState(blockpos).is(ModTags.Blocks.LIVING_BLOCKS)){
            level.setBlockAndUpdate(blockpos, ModBlocks.LIVING_CARPET.get().defaultBlockState());
        }

        if(level.getBlockState(blockpos).is(BlockTags.WOOL_CARPETS) || level.getBlockState(blockpos).is(Blocks.MOSS_CARPET)){
            level.setBlockAndUpdate(blockpos, ModBlocks.LIVING_CARPET.get().defaultBlockState());
        }


        if(level.getBlockState(blockpos.above()).is(Blocks.SNOW)){
            level.setBlockAndUpdate(blockpos.above(), Blocks.AIR.defaultBlockState());
        }else if(level.getBlockState(blockpos).is(Blocks.SNOW)){
            level.setBlockAndUpdate(blockpos, Blocks.AIR.defaultBlockState());
        }

        if(level.getBlockState(blockpos).is(BlockTags.PLANKS) && !level.getBlockState(blockpos).is(ModTags.Blocks.LIVING_BLOCKS)){
            level.setBlockAndUpdate(blockpos, ModBlocks.LIVING_PLANKS.get().defaultBlockState());
        }

        if(level.getBlockState(blockpos).is(BlockTags.LOGS) && !level.getBlockState(blockpos).is(ModTags.Blocks.LIVING_BLOCKS)){
            level.setBlockAndUpdate(blockpos, ModBlocks.LIVING_LOG.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS,
                    level.getBlockState(blockpos).getValue(RotatedPillarBlock.AXIS)));
        }

        if(level.getBlockState(blockpos).is(BlockTags.LEAVES) && !level.getBlockState(blockpos).is(ModTags.Blocks.LIVING_BLOCKS)){
            level.setBlockAndUpdate(blockpos, ModBlocks.LIVING_LEAVES.get().defaultBlockState());
        }

    }


    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if(level.getBlockState(pos.south()).is(ModTags.Blocks.LIVING_BLOCKS) || level.getBlockState(pos.south()).isAir()){
            if(level.getBlockState(pos.north()).is(ModTags.Blocks.LIVING_BLOCKS) || level.getBlockState(pos.north()).isAir()){
                if(level.getBlockState(pos.east()).is(ModTags.Blocks.LIVING_BLOCKS) || level.getBlockState(pos.east()).isAir()){
                    if(level.getBlockState(pos.west()).is(ModTags.Blocks.LIVING_BLOCKS) || level.getBlockState(pos.west()).isAir()){
                        if(level.getBlockState(pos.below()).is(ModTags.Blocks.LIVING_BLOCKS) || level.getBlockState(pos.below()).isAir()){
                            if(level.getBlockState(pos.above()).is(ModTags.Blocks.LIVING_BLOCKS) || level.getBlockState(pos.above()).isAir()){
                                level.setBlock(pos, state.setValue(CAN_PROPAGATE, false), 3);
                            }
                        }
                    }
                }
            }
        } else{
            level.setBlock(pos, state.setValue(CAN_PROPAGATE, true), 3);
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }
}
