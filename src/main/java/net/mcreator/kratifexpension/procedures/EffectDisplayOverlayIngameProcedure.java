package net.mcreator.kratifexpension.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.kratifexpension.init.KratifExpensionModMobEffects;

public class EffectDisplayOverlayIngameProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(KratifExpensionModMobEffects.BOR_ADRENALINE.get());
	}
}
