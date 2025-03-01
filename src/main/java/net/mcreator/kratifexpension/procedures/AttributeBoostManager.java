package net.mcreator.kratifexpension.procedures;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.ForgeMod;


public class AttributeBoostManager {
    private static class AttributeBoost {
        private final String name;
        private final UUID uuid;
        private final double maxBoost;
        private final double baseBoost;

        public AttributeBoost(String name, UUID uuid, double maxBoost, double BaseBoost) {
            this.name = name;
            this.uuid = uuid;
            this.maxBoost = maxBoost;
            this.baseBoost = BaseBoost;
        }
    }

    private final Map<Attribute, AttributeBoost> attributeBoosts = new HashMap<>();
    //private int elapsedTicks = 0;
    private double progress = 0.0; // Progress is now manually controllable
    private boolean isActive = false;

    //private LivingEntity player;

    public AttributeBoostManager() {
    }

    public void addAttributeBoost(Attribute attribute, String name, String uuid, double maxBoost, double baseBoost) {
        attributeBoosts.put(attribute, new AttributeBoost(name, UUID.fromString(uuid), maxBoost, baseBoost));
    }

    public void startBoost(LivingEntity player) {
        if (isActive) return;
        //this.player = player;
        progress = 0.0;
        isActive = true;
    }

    public void setProgress(double newProgress, LivingEntity player) {
        this.progress = Math.max(0.0, Math.min(1.0, newProgress)); // Clamps progress between 0 and 1

        for (Map.Entry<Attribute, AttributeBoost> entry : attributeBoosts.entrySet()) {
            Attribute attribute = entry.getKey();
            AttributeBoost boost = entry.getValue();
            var playerAttribute = player.getAttribute(attribute);
            if (playerAttribute != null) {
                double boostAmount = boost.baseBoost + boost.maxBoost * progress;
                playerAttribute.removeModifier(boost.uuid);
                playerAttribute.addTransientModifier(new AttributeModifier(
                    boost.uuid, boost.name, boostAmount, AttributeModifier.Operation.MULTIPLY_TOTAL
                ));
            }
        }
    }

    public void stopBoost(LivingEntity player) {
        for (AttributeBoost boost : attributeBoosts.values()) {
            for (Attribute attribute : attributeBoosts.keySet()) {
                var playerAttribute = player.getAttribute(attribute);
                if (playerAttribute != null) {
                    playerAttribute.removeModifier(boost.uuid);
                }
            }
        }
        isActive = false;
    }
}
