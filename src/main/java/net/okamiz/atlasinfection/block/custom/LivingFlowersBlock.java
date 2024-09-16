package net.okamiz.atlasinfection.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.okamiz.atlasinfection.block.ModBlocks;
import net.okamiz.atlasinfection.util.ModTags;

public class LivingFlowersBlock extends LivingPlants {
    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 1);
    protected static final VoxelShape FLOWERS_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);
    public LivingFlowersBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(STAGE, 0));
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STAGE);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Vec3 vec3 = state.getOffset(level, pos);
        return FLOWERS_SHAPE.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(STAGE) == 0) {
            if (level.isEmptyBlock(pos.above()) && level.getRawBrightness(pos.above(), 0) >= 9) {
                int i = this.getHeightBelowUpToMax(level, pos) + 1;
                if (i < 16 && net.neoforged.neoforge.common.CommonHooks.canCropGrow(level, pos, state, random.nextInt(3) == 0)) {
                    this.growFlowers(level, pos);
                    net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(level, pos, state);
                }
            }
        }
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(STAGE) == 0;
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
        }
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(ModTags.Blocks.LIVING_FLOWERS_PLANTABLE);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (!state.canSurvive(level, currentPos)) {
            level.scheduleTick(currentPos, this, 1);
        } else {
            if (facing == Direction.UP && facingState.is(ModBlocks.LIVING_FLOWERS)) {
                level.setBlock(currentPos, (ModBlocks.LIVING_FLOWERS.get().defaultBlockState()), 3);
            }
            if(level.getBlockState(currentPos.above()).is(ModBlocks.LIVING_FLOWERS)){
                level.setBlock(currentPos, state.setValue(STAGE, 1), 3);
            }else{
                level.setBlock(currentPos, state.setValue(STAGE, 0), 3);
            }

        }
        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    protected float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        return player.getMainHandItem().canPerformAction(net.neoforged.neoforge.common.ItemAbilities.SWORD_DIG) ? 1.0F : super.getDestroyProgress(state, player, level, pos);
    }

    protected void growFlowers(Level level, BlockPos blockPos) {
        level.setBlock(blockPos.above(), ModBlocks.LIVING_FLOWERS.get().defaultBlockState(), 2);
        level.setBlock(blockPos, ModBlocks.LIVING_FLOWERS.get().defaultBlockState().setValue(STAGE, 1), 3);
    }


    protected int getHeightAboveUpToMax(BlockGetter level, BlockPos pos) {
        int i = 0;

        while (i < 16 && level.getBlockState(pos.above(i + 1)).is(ModBlocks.LIVING_FLOWERS)) {
            i++;
        }

        return i;
    }
    protected int getHeightBelowUpToMax(BlockGetter level, BlockPos pos) {
        int i = 0;

        while (i < 16 && level.getBlockState(pos.below(i + 1)).is(ModBlocks.LIVING_FLOWERS)) {
            i++;
        }

        return i;
    }
}
