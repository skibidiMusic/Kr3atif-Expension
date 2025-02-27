
package net.mcreator.kratifexpension.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;

import java.util.List;

public class SupremeUpgradeSmithingTemplateItem extends Item {
	public SupremeUpgradeSmithingTemplateItem() {
		super(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.UNCOMMON));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.translatable("item.kratif_expension.supreme_upgrade_smithing_template.description_0"));
		list.add(Component.translatable("item.kratif_expension.supreme_upgrade_smithing_template.description_1"));
		list.add(Component.translatable("item.kratif_expension.supreme_upgrade_smithing_template.description_2"));
		list.add(Component.translatable("item.kratif_expension.supreme_upgrade_smithing_template.description_3"));
		list.add(Component.translatable("item.kratif_expension.supreme_upgrade_smithing_template.description_4"));
		list.add(Component.translatable("item.kratif_expension.supreme_upgrade_smithing_template.description_5"));
	}
}
