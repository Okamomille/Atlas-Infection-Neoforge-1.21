package net.okamiz.atlasinfection.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.okamiz.atlasinfection.AtlasInfectionMod;
import net.okamiz.atlasinfection.block.ModBlocks;
import net.okamiz.atlasinfection.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AtlasInfectionMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(ModTags.Blocks.NON_INFECTABLE_BLOCKS)
                .add(Blocks.AIR)
                .add(Blocks.BARRIER)
                .add(Blocks.STRUCTURE_VOID)
                .add(Blocks.BEDROCK)
                .add(Blocks.OBSIDIAN)
                .add(Blocks.SHORT_GRASS)
                .add(Blocks.BAMBOO)
                .add(Blocks.BAMBOO_SAPLING)
                .add(Blocks.TALL_GRASS)
                .add(ModBlocks.ATLAS_HEART.get())
                .add(ModBlocks.HARD_LIVING_BLOCK.get())
                .add(ModBlocks.LIVING_ROOTS.get())
                .add(ModBlocks.LIVING_FLOWERS.get())
                .add(ModBlocks.LIVING_LEAVES.get())
                .add(ModBlocks.LIVING_LOG.get())
                .add(ModBlocks.LIVING_WOOD.get())
                .add(ModBlocks.LIVING_PLANKS.get())
                .add(ModBlocks.STRIPPED_LIVING_LOG.get())
                .add(ModBlocks.STRIPPED_LIVING_WOOD.get())
                .add(ModBlocks.PROTECTED_QUARTZ_BLOCK.get());

        this.tag(ModTags.Blocks.LIVING_BLOCKS)
                .add(ModBlocks.LIVING_BLOCK.get())
                .add(ModBlocks.LIVING_LEAVES.get())
                .add(ModBlocks.HARD_LIVING_BLOCK.get())
                .add(ModBlocks.LIVING_LOG.get())
                .add(ModBlocks.LIVING_WOOD.get())
                .add(ModBlocks.LIVING_PLANKS.get())
                .add(ModBlocks.STRIPPED_LIVING_LOG.get())
                .add(ModBlocks.STRIPPED_LIVING_WOOD.get());

        this.tag(ModTags.Blocks.IS_MASS_BLOCKS_VERTICAL)
                .add(Blocks.CACTUS)
                .add(Blocks.SUGAR_CANE)
                .add(Blocks.BAMBOO_SAPLING)
                .add(Blocks.BAMBOO);

        this.tag(ModTags.Blocks.LIVING_FLOWERS_PLANTABLE)
                .add(ModBlocks.LIVING_BLOCK.get())
                .add(ModBlocks.HARD_LIVING_BLOCK.get())
                .add(ModBlocks.LIVING_FLOWERS.get());

        this.tag(BlockTags.LEAVES)
                .add(ModBlocks.LIVING_LEAVES.get());


        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(ModBlocks.PROTECTED_QUARTZ_BLOCK.get())
            .add(ModBlocks.ATLAS_HEART.get());

        this.tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.LIVING_BLOCK.get())
                .add(ModBlocks.HARD_LIVING_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.LIVING_LOG.get())
                .add(ModBlocks.LIVING_WOOD.get())
                .add(ModBlocks.STRIPPED_LIVING_LOG.get())
                .add(ModBlocks.STRIPPED_LIVING_WOOD.get())
                .add(ModBlocks.LIVING_PLANKS.get());

        this.tag(BlockTags.LOGS)
                .add(ModBlocks.LIVING_LOG.get())
                .add(ModBlocks.LIVING_WOOD.get())
                .add(ModBlocks.STRIPPED_LIVING_LOG.get())
                .add(ModBlocks.STRIPPED_LIVING_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.LIVING_PLANKS.get());
    }
}
