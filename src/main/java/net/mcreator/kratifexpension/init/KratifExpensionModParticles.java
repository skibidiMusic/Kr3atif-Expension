
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kratifexpension.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.kratifexpension.client.particle.EgeberushWindParticleParticle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KratifExpensionModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(KratifExpensionModParticleTypes.EGEBERUSH_WIND_PARTICLE.get(), EgeberushWindParticleParticle::provider);
	}
}
