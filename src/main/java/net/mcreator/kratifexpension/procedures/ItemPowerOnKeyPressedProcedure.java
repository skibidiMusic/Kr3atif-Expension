package net.mcreator.kratifexpension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.registries.Registries;

import net.mcreator.kratifexpension.init.KratifExpensionModMobEffects;
import net.mcreator.kratifexpension.init.KratifExpensionModItems;

public class ItemPowerOnKeyPressedProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(KratifExpensionModMobEffects.EXHAUSTION.get()))) {
			if (!(entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(KratifExpensionModMobEffects.BOR_ADRENALINE.get()))
					&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == KratifExpensionModItems.BORANIUM_SWORD.get()) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(KratifExpensionModMobEffects.BOR_ADRENALINE.get(), 300, 0));
				entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), (float) Math.ceil(3 + 0.1 * (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1)));
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 100, 0, false, false));
			}
			if (!(entity instanceof LivingEntity _livEnt9 && _livEnt9.hasEffect(KratifExpensionModMobEffects.EGEBE_RUSH.get()))
					&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == KratifExpensionModItems.EGEBERITE_PICKAXE.get()) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(KratifExpensionModMobEffects.EGEBE_RUSH.get(), 200, 0));
				entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), (float) Math.ceil(3 + 0.1 * (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1)));
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 100, 0, false, false));
			}
		}
	}
}
