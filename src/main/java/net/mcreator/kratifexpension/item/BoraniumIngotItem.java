
package net.mcreator.kratifexpension.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class BoraniumIngotItem extends Item {
	public BoraniumIngotItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON));
	}
}
