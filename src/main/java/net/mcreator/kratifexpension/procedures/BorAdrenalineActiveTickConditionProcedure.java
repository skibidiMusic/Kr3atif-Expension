package net.mcreator.kratifexpension.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;

import net.minecraft.world.entity.Entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;

public class BorAdrenalineActiveTickConditionProcedure {
	public static void execute(Entity entity, LevelAccessor world, double x, double y, double z, int tickCount) {
		if  ( tickCount % 4 == 0 && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.AIR && !((world.getBlockState(BlockPos.containing(x, y-1, z))).getBlock() == Blocks.AIR)) {
			world.setBlock(BlockPos.containing(x, y, z), Blocks.FIRE.defaultBlockState(), 3);
		}

		if ( world instanceof ServerLevel serverLevel){
        	//particle
			double radius = 2;
        	int yTick = tickCount % 120;
        	double offsetY = Math.sin((2 * Math.PI * yTick) / 120);
        	tickCount = tickCount % 40;
        	double angle = (2 * Math.PI * tickCount) / 40;
        	double offsetX = radius * Math.cos(angle);
        	double offsetZ = radius * Math.sin(angle);
        	Vec3 position = entity.position().add(offsetX, 1 + offsetY, offsetZ);
        	((ServerLevel) world).sendParticles(ParticleTypes.FLAME, position.x, position.y, position.z, 5, 0.1, 0.1, 0.1, 0);
		}
	}
}
