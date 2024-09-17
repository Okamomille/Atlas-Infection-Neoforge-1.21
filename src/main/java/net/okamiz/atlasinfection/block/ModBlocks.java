package net.okamiz.atlasinfection.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
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
    public static final DeferredBlock<Block> LIVING_LOG = registerBlock("living_log",
            () -> new ModLogsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).sound(SoundType.WART_BLOCK)));
    public static final DeferredBlock<Block> LIVING_WOOD = registerBlock("living_wood",
            () -> new ModLogsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).sound(SoundType.WART_BLOCK)));
    public static final DeferredBlock<Block> STRIPPED_LIVING_LOG = registerBlock("stripped_living_log",
            () -> new ModLogsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.WART_BLOCK)));
    public static final DeferredBlock<Block> STRIPPED_LIVING_WOOD = registerBlock("stripped_living_wood",
            () -> new ModLogsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).sound(SoundType.WART_BLOCK)));

    public static final DeferredBlock<Block> LIVING_PLANKS = registerBlock("living_planks",
            () -> new LivingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WART_BLOCK)));


    // LIVING PLANTS ----------------------------

    public static final DeferredBlock<Block> LIVING_CARPET = registerBlock("living_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_CARPET).sound(SoundType.WART_BLOCK)));
    public static final DeferredBlock<Block> LIVING_ROOTS = registerBlock("living_roots",
            () -> new LivingPlants(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_ROOTS)));

    public static final DeferredBlock<Block> LIVING_FLOWERS = registerBlock("living_flowers",
            () -> new LivingFlowersBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_ROOTS)));




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
