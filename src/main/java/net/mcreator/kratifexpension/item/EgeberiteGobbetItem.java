
package net.mcreator.kratifexpension.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class EgeberiteGobbetItem extends Item {
	public EgeberiteGobbetItem() {
		super(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.UNCOMMON));
	}
}
