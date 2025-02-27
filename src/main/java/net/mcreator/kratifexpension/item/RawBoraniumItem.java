
package net.mcreator.kratifexpension.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class RawBoraniumItem extends Item {
	public RawBoraniumItem() {
		super(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.UNCOMMON));
	}
}
