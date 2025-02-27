
package net.mcreator.kratifexpension.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.kratifexpension.procedures.EgebeRushedOnEffectActiveTickProcedure;
import net.mcreator.kratifexpension.procedures.EgebeRushedEffectStartedappliedProcedure;

public class EgebeRushedMobEffect extends MobEffect {
	public EgebeRushedMobEffect() {
		super(MobEffectCategory.HARMFUL, -16751104);
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "c0418c97-d155-30aa-93f1-ecb45869c5a0", -0.25, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(Attributes.JUMP_STRENGTH, "03b4e467-aa35-376f-9d8e-28cefe4b2842", -0.2, AttributeModifier.Operation.MULTIPLY_TOTAL);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.addAttributeModifiers(entity, attributeMap, amplifier);
		EgebeRushedEffectStartedappliedProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ());
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		EgebeRushedOnEffectActiveTickProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ());
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
