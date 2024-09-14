package net.okamiz.atlasinfection.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.okamiz.atlasinfection.AtlasInfectionMod;
import net.okamiz.atlasinfection.block.custom.*;
import net.okamiz.atlasinfection.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(AtlasInfectionMod.MOD_ID);


    public static final DeferredBlock<Block> PROTECTED_QUARTZ_BLOCK = registerBlock("protected_quartz_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)));


    public static final DeferredBlock<Block> ATLAS_HEART = registerBlock("atlas_heart",
            () -> new AtlasHeart(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).randomTicks()));

    // LIVING BLOCKS ----------------------------

    public static final DeferredBlock<Block> LIVING_BLOCK = registerBlock("living_block",
            () -> new LivingBlock(BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WART_BLOCK)));
    public static final DeferredBlock<Block> HARD_LIVING_BLOCK = registerBlock("hard_living_block",
            () -> new HardLivingBlock(BlockBehaviour.Properties.of().strength(2.8F).sound(SoundType.WART_BLOCK)));

    public static final DeferredBlock<Block> LIVING_LEAVES = registerBlock("living_leaves",
            () -> new LivingLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_LEAVES).sound(SoundType.WART_BLOCK)));


    // LIVING PLANTS ----------------------------

    public static final DeferredBlock<Block> LIVING_ROOTS = registerBlock("living_roots",
            () -> new LivingPlants(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_ROOTS)));




    private static <T extends Block>DeferredBlock<T> registerBlock(String name, Supplier<T>block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}
