package net.okamiz.atlasinfection.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.okamiz.atlasinfection.block.ModBlocks;
import net.okamiz.atlasinfection.item.ModItems;
import net.okamiz.atlasinfection.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {


                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ATLAS_HEART.get(), 1)
                .pattern("BIB")
                .pattern("IEI")
                .pattern("BIB")
                .define('E', Items.END_CRYSTAL)
                .define('I', Items.ENDER_PEARL)
                .define('B', Items.DEEPSLATE)
                .unlockedBy("has_end_crystal", has(Items.END_CRYSTAL))
                .unlockedBy("has_ender_pearl", has(Items.ENDER_PEARL))
                .unlockedBy("has_deepslate", has(Blocks.DEEPSLATE))

                        .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PROTECTED_QUARTZ_BLOCK.get(), 8)
                .pattern("QQQ")
                .pattern("QEQ")
                .pattern("QQQ")
                .define('Q', Blocks.QUARTZ_BLOCK)
                .define('E', Items.HONEYCOMB)
                .unlockedBy("has_honeycomb", has(Items.HONEYCOMB))
                .unlockedBy("has_quartz_block", has(Blocks.QUARTZ_BLOCK))
                .save(recipeOutput);
/*
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLACK_OPAL.get(), 9)
                .requires(ModBlocks.BLACK_OPAL_BLOCK.get())
                .unlockedBy("has_black_opal_block", has(ModBlocks.BLACK_OPAL_BLOCK.get())).save(pRecipeOutput);
         */
    }
}
