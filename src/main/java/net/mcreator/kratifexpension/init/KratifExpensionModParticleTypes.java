
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kratifexpension.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.mcreator.kratifexpension.KratifExpensionMod;

public class KratifExpensionModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, KratifExpensionMod.MODID);
	public static final RegistryObject<SimpleParticleType> EGEBERUSH_WIND_PARTICLE = REGISTRY.register("egeberush_wind_particle", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> BORADRENALINEEXPLOSION1 = REGISTRY.register("boradrenalineexplosion1", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> BORADRENALINE_FLAMES = REGISTRY.register("boradrenaline_flames", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> BORADRENALINE_EXPLOSION = REGISTRY.register("boradrenaline_explosion", () -> new SimpleParticleType(false));
}
