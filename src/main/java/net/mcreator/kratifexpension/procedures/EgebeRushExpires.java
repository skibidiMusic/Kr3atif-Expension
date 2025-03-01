package net.mcreator.kratifexpension.procedures;

import net.mcreator.kratifexpension.init.KratifExpensionModMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.registries.ForgeRegistries;

public class EgebeRushExpires {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;

        // Apply the EXHAUSTION effect
        if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(KratifExpensionModMobEffects.EXHAUSTION.get(), 1200, 0, false, true));
        }

        // Apply the ANTI_EGEBE_RUSH effect
        if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(KratifExpensionModMobEffects.ANTI_EGEBE_RUSH.get(), 240, 0, false, true));
        }

        // Apply the BLINDNESS effect
        if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 240, 0, false, true));
        }

        // Play the sound effect (server-side)
        if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
                _level.playSound(null, BlockPos.containing(x, y, z),
                        ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kratif_expension:slowcore")),
                        SoundSource.AMBIENT, 1, 1);
            } else {
                _level.playLocalSound(x, y, z,
                        ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kratif_expension:slowcore")),
                        SoundSource.AMBIENT, 1, 1, false);
            }
        }
    }
}
