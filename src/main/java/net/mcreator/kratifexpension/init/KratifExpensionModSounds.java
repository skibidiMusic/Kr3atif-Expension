
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kratifexpension.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.kratifexpension.KratifExpensionMod;

public class KratifExpensionModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, KratifExpensionMod.MODID);
	public static final RegistryObject<SoundEvent> BORANDRENALINE = REGISTRY.register("borandrenaline", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("kratif_expension", "borandrenaline")));
	public static final RegistryObject<SoundEvent> HEADBUZZING = REGISTRY.register("headbuzzing", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("kratif_expension", "headbuzzing")));
}
