package net.okamiz.atlasinfection.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.okamiz.atlasinfection.AtlasInfectionMod;
import net.okamiz.atlasinfection.block.ModBlocks;
import net.okamiz.atlasinfection.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AtlasInfectionMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        basicItem(ModItems.INFECTED_POWDER.get());

        flowerItem(ModBlocks.LIVING_ROOTS);
    }


    public void flowerItem(DeferredBlock<Block> block){
        this.withExistingParent(block.getId().getPath(), mcLoc("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(AtlasInfectionMod.MOD_ID,
                        "block/" + block.getId().getPath()));
    }
}
