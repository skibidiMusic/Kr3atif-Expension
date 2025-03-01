package net.mcreator.kratifexpension.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;

import net.minecraft.world.entity.Entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.kratifexpension.procedures.SpiralParticle;
import net.mcreator.kratifexpension.init.KratifExpensionModParticleTypes;

public class BorAdrenalineActiveTickConditionProcedure {
	public static void execute(Entity entity, LevelAccessor world, double x, double y, double z, int tickCount, double progress) {
		if ( world instanceof ServerLevel serverLevel){
			//SpiralParticle.render(2, progress, serverLevel, entity, tickCount, KratifExpensionModParticleTypes.BORADRENALINE_FLAMES.get(), 1);
			if  (progress > 0.5 && tickCount % 4 == 0 && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.AIR && !((world.getBlockState(BlockPos.containing(x, y-1, z))).getBlock() == Blocks.AIR)) {
				serverLevel.setBlock(BlockPos.containing(x, y, z), Blocks.FIRE.defaultBlockState(), 3);
			}
			((ServerLevel) world).sendParticles(KratifExpensionModParticleTypes.BORADRENALINE_EXPLOSION.get(), entity.getX(), entity.getY() + 1, entity.getZ(), 1, .1, .1, .1, 1);
		}
	}
}