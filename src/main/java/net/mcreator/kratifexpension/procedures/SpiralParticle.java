package net.mcreator.kratifexpension.procedures;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class SpiralParticle {
    private double angle = 0.0; // Keeps track of the current rotation
    private double speed;
    private double radius;
    private int tickCounter = 0; // Local tick counter

    public SpiralParticle(double radius, double speed) {
        this.radius = radius;
        this.speed = speed;
    }

    public void setSpeed(double newSpeed) {
        this.speed = newSpeed;
    }

    public void render(ServerLevel world, Entity entity, SimpleParticleType particle, int amount) {
        if (entity == null || world == null) return;

        tickCounter += speed; // Increment the counter based on speed

        double yOffset = Math.sin((2 * Math.PI * tickCounter) / 120); // Vertical oscillation
        angle += (2 * Math.PI * speed) / 40; // Rotate based on speed

        // Keep the angle within bounds to avoid floating point precision issues
        angle %= (2 * Math.PI);

        double offsetX = radius * Math.cos(angle);
        double offsetZ = radius * Math.sin(angle);

        Vec3 position = entity.position().add(offsetX, 1.25 + yOffset, offsetZ);
        world.sendParticles(particle, position.x, position.y, position.z, amount, 0.1, 0.1, 0.1, 0);
    }
}