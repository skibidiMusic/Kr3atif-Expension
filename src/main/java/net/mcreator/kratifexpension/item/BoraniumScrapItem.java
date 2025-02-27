
package net.mcreator.kratifexpension.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class BoraniumScrapItem extends Item {
	public BoraniumScrapItem() {
		super(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.UNCOMMON));
	}
}
