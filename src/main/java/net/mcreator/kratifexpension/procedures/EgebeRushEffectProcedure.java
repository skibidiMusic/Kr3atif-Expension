package net.mcreator.kratifexpension.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import net.mcreator.kratifexpension.init.KratifExpensionModMobEffects;
import net.mcreator.kratifexpension.init.KratifExpensionModParticleTypes;

import java.util.List;

public class EgebeRushEffectProcedure {
    public static void execute(LivingEntity entity, int tickCount) {
    	Level world = entity.level();

        if (world instanceof ServerLevel serverLevel) {
            double radius = 2;
            double pushStrength = 1.25;

            //particle
            int yTick = tickCount % 120;
            double offsetY = Math.sin((2 * Math.PI * yTick) / 120);

            tickCount = tickCount % 40;
            double angle = (2 * Math.PI * tickCount) / 40;
            double offsetX = radius * Math.cos(angle);
            double offsetZ = radius * Math.sin(angle);

            Vec3 position = entity.position().add(offsetX, 1 + offsetY, offsetZ);
            ((ServerLevel) world).sendParticles(ParticleTypes.GLOW_SQUID_INK, position.x, position.y, position.z, 5, 0.1, 0.1, 0.1, 0);

            ((ServerLevel) world).sendParticles(KratifExpensionModParticleTypes.EGEBERUSH_WIND_PARTICLE.get(), entity.getX(), entity.getY() + 1, entity.getZ(), 1, 2, 2, 2, 0);
            ((ServerLevel) world).sendParticles(ParticleTypes.SPIT, entity.getX(), entity.getY() + 1, entity.getZ(), 4, 0.5,0.5,0.5, 0.1);

            List<Entity> nearbyEntities = serverLevel.getEntities(entity, entity.getBoundingBox().inflate(radius), e -> e instanceof LivingEntity && e != entity);
            
            for (Entity target : nearbyEntities) {
                Vec3 dist = target.position().subtract(entity.position());
                pushStrength *= dist.length() / radius * (entity.getSpeed() / 0.1);

                Vec3 direction = dist.normalize();
                
                target.setDeltaMovement(direction.x * pushStrength, 0, direction.z * pushStrength);
                //((ServerLevel) world).sendParticles(ParticleTypes.EXPLOSION, target.getX(), target.getY() + 1, target.getZ(), 5, 0.0, 0.0, 0.0, 1.0);

                if ((target instanceof LivingEntity target_0) && !target_0.hasEffect(KratifExpensionModMobEffects.EGEBE_RUSHED.get())) {
                    target_0.addEffect(new MobEffectInstance(KratifExpensionModMobEffects.EGEBE_RUSHED.get(), 10, 0));
                }
            }
        }
    }
}
