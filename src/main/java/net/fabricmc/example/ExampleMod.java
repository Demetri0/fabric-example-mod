package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.Blocks.ExampleBlock;
import net.fabricmc.example.Items.ScrollOfReturn;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	public static final Block EXAMPLE_BLOCK = new ExampleBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool());

	public static final ScrollOfReturn SCROLL_OF_RETURN = new ScrollOfReturn(new FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.RARE).maxCount(3).maxDamage(1));


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world from MetaMC!");

		Registry.register(Registry.BLOCK, new Identifier("tutorial", "example_block"), EXAMPLE_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("tutorial", "example_block"), new BlockItem(EXAMPLE_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.ITEM, new Identifier("tutorial", "scroll_of_return"), SCROLL_OF_RETURN);

	}
}
