
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kratifexpension.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.kratifexpension.KratifExpensionMod;

public class KratifExpensionModPotions {
	public static final DeferredRegister<Potion> REGISTRY = DeferredRegister.create(ForgeRegistries.POTIONS, KratifExpensionMod.MODID);
	public static final RegistryObject<Potion> EXHAUSTION_POTION = REGISTRY.register("exhaustion_potion", () -> new Potion(new MobEffectInstance(KratifExpensionModMobEffects.EXHAUSTION.get(), 3600, 0, false, true)));
}
