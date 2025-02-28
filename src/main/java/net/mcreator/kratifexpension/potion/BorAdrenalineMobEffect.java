
package net.mcreator.kratifexpension.potion;

import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.kratifexpension.procedures.BorAdrenalineEffectStartedappliedProcedure;
import net.mcreator.kratifexpension.procedures.EgebeRushEffectProcedure;
import net.mcreator.kratifexpension.procedures.BorAdrenalineEffectExpiresProcedure;
import net.mcreator.kratifexpension.procedures.BorAdrenalineActiveTickConditionProcedure;

import java.util.List;
import java.util.ArrayList;

public class BorAdrenalineMobEffect extends MobEffect {
	private int tickCounter = 0;

	public BorAdrenalineMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -6737152);
		this.addAttributeModifier(Attributes.ARMOR, "7474145f-46bf-3507-9d5f-b52cc6fb25e3", 0.5, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "46598dcd-1de3-3575-9637-e4d13bf7b63b", 1, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(Attributes.ATTACK_SPEED, "6d04b947-4ac0-3c30-8832-6ce584169a0e", 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, "c4042de0-cb60-323d-a39f-fd7574ce1802", 0.25, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(Attributes.ATTACK_KNOCKBACK, "52daf1fe-da15-31a5-8325-d560c9e6d77d", -0.25, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, "1f499e24-8345-343a-8e54-ebf18f12b30e", 0.5, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "ae434afd-53ca-323c-9cd9-d03e4141f8ac", 0.2, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(ForgeMod.ENTITY_REACH.get(), "a01b6595-004a-3d00-9090-bf5378ef44f3", 0.05, AttributeModifier.Operation.MULTIPLY_TOTAL);
	}

	@Override
	public List<ItemStack> getCurativeItems() {
		ArrayList<ItemStack> cures = new ArrayList<ItemStack>();
		return cures;
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.addAttributeModifiers(entity, attributeMap, amplifier);
		BorAdrenalineEffectStartedappliedProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		this.tickCounter += 1;
		BorAdrenalineActiveTickConditionProcedure.execute(entity, entity.level(), entity.getX(), entity.getY(), entity.getZ(), this.tickCounter);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		BorAdrenalineEffectExpiresProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
