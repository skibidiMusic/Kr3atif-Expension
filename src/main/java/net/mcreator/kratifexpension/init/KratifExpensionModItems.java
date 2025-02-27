
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kratifexpension.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.kratifexpension.item.SupremeUpgradeSmithingTemplateItem;
import net.mcreator.kratifexpension.item.RawEgeberiteItem;
import net.mcreator.kratifexpension.item.RawBoraniumItem;
import net.mcreator.kratifexpension.item.EgeberitePickaxeItem;
import net.mcreator.kratifexpension.item.EgeberiteItem;
import net.mcreator.kratifexpension.item.EgeberiteGobbetItem;
import net.mcreator.kratifexpension.item.BoraniumSwordItem;
import net.mcreator.kratifexpension.item.BoraniumScrapItem;
import net.mcreator.kratifexpension.item.BoraniumIngotItem;
import net.mcreator.kratifexpension.item.BoraniumArmorItem;
import net.mcreator.kratifexpension.KratifExpensionMod;

public class KratifExpensionModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, KratifExpensionMod.MODID);
	public static final RegistryObject<Item> BORANIUM_ORE = block(KratifExpensionModBlocks.BORANIUM_ORE);
	public static final RegistryObject<Item> RAW_BORANIUM = REGISTRY.register("raw_boranium", () -> new RawBoraniumItem());
	public static final RegistryObject<Item> BORANIUM_INGOT = REGISTRY.register("boranium_ingot", () -> new BoraniumIngotItem());
	public static final RegistryObject<Item> BORANIUM_SWORD = REGISTRY.register("boranium_sword", () -> new BoraniumSwordItem());
	public static final RegistryObject<Item> BORANIUM_SCRAP = REGISTRY.register("boranium_scrap", () -> new BoraniumScrapItem());
	public static final RegistryObject<Item> SUPREME_UPGRADE_SMITHING_TEMPLATE = REGISTRY.register("supreme_upgrade_smithing_template", () -> new SupremeUpgradeSmithingTemplateItem());
	public static final RegistryObject<Item> BORANIUM_ARMOR_HELMET = REGISTRY.register("boranium_armor_helmet", () -> new BoraniumArmorItem.Helmet());
	public static final RegistryObject<Item> BORANIUM_ARMOR_CHESTPLATE = REGISTRY.register("boranium_armor_chestplate", () -> new BoraniumArmorItem.Chestplate());
	public static final RegistryObject<Item> BORANIUM_ARMOR_LEGGINGS = REGISTRY.register("boranium_armor_leggings", () -> new BoraniumArmorItem.Leggings());
	public static final RegistryObject<Item> BORANIUM_ARMOR_BOOTS = REGISTRY.register("boranium_armor_boots", () -> new BoraniumArmorItem.Boots());
	public static final RegistryObject<Item> EGEBERITE_ORE = block(KratifExpensionModBlocks.EGEBERITE_ORE);
	public static final RegistryObject<Item> EGEBERITE_PICKAXE = REGISTRY.register("egeberite_pickaxe", () -> new EgeberitePickaxeItem());
	public static final RegistryObject<Item> EGEBERITE_GOBBET = REGISTRY.register("egeberite_gobbet", () -> new EgeberiteGobbetItem());
	public static final RegistryObject<Item> RAW_EGEBERITE = REGISTRY.register("raw_egeberite", () -> new RawEgeberiteItem());
	public static final RegistryObject<Item> EGEBERITE = REGISTRY.register("egeberite", () -> new EgeberiteItem());

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
