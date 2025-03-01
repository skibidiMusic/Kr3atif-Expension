
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kratifexpension.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import net.mcreator.kratifexpension.potion.ExhaustionMobEffect;
import net.mcreator.kratifexpension.potion.EgebeRushedMobEffect;
import net.mcreator.kratifexpension.potion.EgebeRushMobEffect;
import net.mcreator.kratifexpension.potion.BorAdrenalineMobEffect;
import net.mcreator.kratifexpension.potion.AntiEgebeRushMobEffect;
import net.mcreator.kratifexpension.potion.AntiBorAdrenalineMobEffect;
import net.mcreator.kratifexpension.KratifExpensionMod;

public class KratifExpensionModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, KratifExpensionMod.MODID);
	public static final RegistryObject<MobEffect> EXHAUSTION = REGISTRY.register("exhaustion", () -> new ExhaustionMobEffect());
	public static final RegistryObject<MobEffect> EGEBE_RUSH = REGISTRY.register("egebe_rush", () -> new EgebeRushMobEffect());
	public static final RegistryObject<MobEffect> BOR_ADRENALINE = REGISTRY.register("bor_adrenaline", () -> new BorAdrenalineMobEffect());
	public static final RegistryObject<MobEffect> EGEBE_RUSHED = REGISTRY.register("egebe_rushed", () -> new EgebeRushedMobEffect());
	public static final RegistryObject<MobEffect> ANTI_EGEBE_RUSH = REGISTRY.register("anti_egebe_rush", () -> new AntiEgebeRushMobEffect());
	public static final RegistryObject<MobEffect> ANTI_BOR_ADRENALINE = REGISTRY.register("anti_bor_adrenaline", () -> new AntiBorAdrenalineMobEffect());
}
