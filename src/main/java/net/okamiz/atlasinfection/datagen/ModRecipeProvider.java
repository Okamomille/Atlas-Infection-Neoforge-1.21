package net.okamiz.atlasinfection.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.okamiz.atlasinfection.block.ModBlocks;

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



        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIVING_WOOD.get(), 3)
                .pattern("LL")
                .pattern("LL")
                .define('L', ModBlocks.LIVING_LOG)
                .unlockedBy("has_living_log", has(ModBlocks.LIVING_LOG.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_LIVING_WOOD.get(), 3)
                .pattern("LL")
                .pattern("LL")
                .define('L', ModBlocks.STRIPPED_LIVING_LOG)
                .unlockedBy("has_stripped_living_log", has(ModBlocks.STRIPPED_LIVING_LOG.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIVING_PLANKS.get(), 4)
                .requires(ModBlocks.LIVING_LOG.get())
                .unlockedBy("has_living_log", has(ModBlocks.LIVING_LOG.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIVING_PLANKS.get(), 4)
                .requires(ModBlocks.LIVING_WOOD.get())
                .unlockedBy("has_living_wood", has(ModBlocks.LIVING_WOOD.get())).save(recipeOutput, "atlasinfection:living_planks_from_living_wood");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIVING_PLANKS.get(), 4)
                .requires(ModBlocks.STRIPPED_LIVING_LOG.get())
                .unlockedBy("has_stripped_living_log", has(ModBlocks.STRIPPED_LIVING_LOG.get())).save(recipeOutput, "atlasinfection:living_planks_from_stripped_living_log");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIVING_PLANKS.get(), 4)
                .requires(ModBlocks.STRIPPED_LIVING_WOOD.get())
                .unlockedBy("has_stripped_living_wood", has(ModBlocks.STRIPPED_LIVING_WOOD.get())).save(recipeOutput, "atlasinfection:living_planks_from_stripped_living_wood");

    }
}
