package net.okamiz.atlasinfection.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.okamiz.atlasinfection.block.ModBlocks;
import net.okamiz.atlasinfection.util.ModTags;


public class HardLivingBlock extends LivingMechanicBlock {
    public HardLivingBlock(Properties properties) {
        super(properties);
    }


    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {


        for (int i = 0; i < 2; i++) {
            propagatethisBlock(state, level, pos, random);
        }

    }
}


