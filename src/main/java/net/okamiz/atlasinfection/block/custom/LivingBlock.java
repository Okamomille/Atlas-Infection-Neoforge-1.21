package net.okamiz.atlasinfection.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.okamiz.atlasinfection.block.ModBlocks;
import net.okamiz.atlasinfection.effect.ModEffects;
import net.okamiz.atlasinfection.util.ModTags;


public class LivingBlock extends LivingMechanicBlock {
    public LivingBlock(Properties properties) {
        super(properties);
    }


    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

        for (int i = 0; i < 4; i++) {
            propagatethisBlock(state, level, pos, random);
        }

    }

}


