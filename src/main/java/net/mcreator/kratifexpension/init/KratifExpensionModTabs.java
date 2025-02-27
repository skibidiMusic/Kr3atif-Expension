
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kratifexpension.init;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;

import net.mcreator.kratifexpension.KratifExpensionMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class KratifExpensionModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KratifExpensionMod.MODID);

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			tabData.accept(KratifExpensionModBlocks.BORANIUM_ORE.get().asItem());
			tabData.accept(KratifExpensionModBlocks.EGEBERITE_ORE.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			tabData.accept(KratifExpensionModItems.RAW_BORANIUM.get());
			tabData.accept(KratifExpensionModItems.BORANIUM_INGOT.get());
			tabData.accept(KratifExpensionModItems.BORANIUM_SCRAP.get());
			tabData.accept(KratifExpensionModItems.SUPREME_UPGRADE_SMITHING_TEMPLATE.get());
			tabData.accept(KratifExpensionModItems.EGEBERITE_GOBBET.get());
			tabData.accept(KratifExpensionModItems.RAW_EGEBERITE.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.COMBAT) {
			tabData.accept(KratifExpensionModItems.BORANIUM_SWORD.get());
			tabData.accept(KratifExpensionModItems.BORANIUM_ARMOR_HELMET.get());
			tabData.accept(KratifExpensionModItems.BORANIUM_ARMOR_CHESTPLATE.get());
			tabData.accept(KratifExpensionModItems.BORANIUM_ARMOR_LEGGINGS.get());
			tabData.accept(KratifExpensionModItems.BORANIUM_ARMOR_BOOTS.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(KratifExpensionModItems.EGEBERITE_PICKAXE.get());
		}
	}
}
