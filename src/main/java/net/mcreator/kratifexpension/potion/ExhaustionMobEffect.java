
package net.mcreator.kratifexpension.potion;

import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import java.util.List;
import java.util.ArrayList;

public class ExhaustionMobEffect extends MobEffect {
	public ExhaustionMobEffect() {
		super(MobEffectCategory.HARMFUL, -16490762);
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "25dd9e45-1766-3be5-81fd-95a893e604a6", -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(Attributes.ATTACK_SPEED, "ed4f04ac-be89-3fff-87fd-10792774608a", -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(Attributes.JUMP_STRENGTH, "f437e174-de9f-3127-9160-ebca06139483", -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(ForgeMod.SWIM_SPEED.get(), "a9de68de-6e16-35ca-bb1c-e97f37225ea9", -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(ForgeMod.ENTITY_GRAVITY.get(), "b021e727-9d0f-386c-8d2f-0818b0a26fe6", 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(ForgeMod.ENTITY_REACH.get(), "61e0402c-431a-30cb-8a0a-9e8f88999665", -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(ForgeMod.BLOCK_REACH.get(), "da362ee3-f7a8-36a4-b9c3-f9a978689c7b", -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
	}

	@Override
	public List<ItemStack> getCurativeItems() {
		ArrayList<ItemStack> cures = new ArrayList<ItemStack>();
		cures.add(new ItemStack(Items.MILK_BUCKET));
		cures.add(new ItemStack(Items.HONEY_BOTTLE));
		return cures;
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
