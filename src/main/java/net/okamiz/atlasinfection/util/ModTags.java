package net.okamiz.atlasinfection.util;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.okamiz.atlasinfection.AtlasInfectionMod;

public class ModTags {
    public static class Blocks{

        public static final TagKey<Block> NON_INFECTABLE_BLOCKS = createTag("non_infectable_blocks");
        public static final TagKey<Block> LIVING_BLOCKS = createTag("living_blocks");

        private static TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(AtlasInfectionMod.MOD_ID, name));
        }
    }

    public static class Items{
        private static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AtlasInfectionMod.MOD_ID, name));
        }
    }
}
