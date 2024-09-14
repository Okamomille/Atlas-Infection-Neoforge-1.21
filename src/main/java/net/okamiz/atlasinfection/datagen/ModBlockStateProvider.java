package net.okamiz.atlasinfection.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.okamiz.atlasinfection.AtlasInfectionMod;
import net.okamiz.atlasinfection.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AtlasInfectionMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.PROTECTED_QUARTZ_BLOCK);
        blockWithItem(ModBlocks.LIVING_BLOCK);
        blockWithItem(ModBlocks.HARD_LIVING_BLOCK);
        blockWithItem(ModBlocks.ATLAS_HEART);

        simpleBlockWithItem(ModBlocks.LIVING_LEAVES.get(),
                models().cubeAll(blockTexture(ModBlocks.LIVING_LEAVES.get()).getPath(), blockTexture(ModBlocks.LIVING_LEAVES.get())).renderType("cutout"));

        simpleBlock(ModBlocks.LIVING_ROOTS.get(),
                models().cross(blockTexture(ModBlocks.LIVING_ROOTS.get()).getPath(), blockTexture(ModBlocks.LIVING_ROOTS.get())).renderType("cutout"));
    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }


}
