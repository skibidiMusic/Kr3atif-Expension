
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kratifexpension.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.mcreator.kratifexpension.block.EgeberiteOreBlock;
import net.mcreator.kratifexpension.block.BoraniumOreBlock;
import net.mcreator.kratifexpension.KratifExpensionMod;

public class KratifExpensionModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, KratifExpensionMod.MODID);
	public static final RegistryObject<Block> BORANIUM_ORE = REGISTRY.register("boranium_ore", () -> new BoraniumOreBlock());
	public static final RegistryObject<Block> EGEBERITE_ORE = REGISTRY.register("egeberite_ore", () -> new EgeberiteOreBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
