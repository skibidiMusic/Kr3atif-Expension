package net.mcreator.kratifexpension.potion;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attributes;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.mcreator.kratifexpension.procedures.EgebeRushEffectProcedure;
import net.mcreator.kratifexpension.procedures.EgebeRushExpires;
import net.mcreator.kratifexpension.procedures.SpiralParticle;
import net.mcreator.kratifexpension.init.KratifExpensionModParticleTypes;
import net.mcreator.kratifexpension.procedures.AttributeBoostManager;

import java.util.List;
import java.util.ArrayList;

public class EgebeRushMobEffect extends MobEffect {
    private int tickCounter = 0;
    private static final int DURATION_TICKS = 200; // 10 seconds (20 ticks per second)
    private final AttributeBoostManager boostManager = new AttributeBoostManager();
	private SpiralParticle spiralParticle;

    public EgebeRushMobEffect() {
        super(MobEffectCategory.BENEFICIAL, -3342388);
        boostManager.addAttributeBoost(Attributes.MOVEMENT_SPEED, "Speed Boost Modifier", "e8d1a5b0-8f78-4e6d-8e49-3d4b648495ad", 1.5, 0.25);
        boostManager.addAttributeBoost(ForgeMod.SWIM_SPEED.get(), "Swim Speed Boost Modifier", "a4b2c3d4-e5f6-7890-abcd-ef1234567890", 1.5, 0.25);
        boostManager.addAttributeBoost(Attributes.ATTACK_SPEED, "Attack Speed Boost Modifier", "ed4f04ac-be89-3fff-87fd-10792774608a", 1.0, 0.25);
        boostManager.addAttributeBoost(Attributes.JUMP_STRENGTH, "Jump Strength Boost Modifier", "f437e174-de9f-3127-9160-ebca06139483", 1.0, 0.25);
        boostManager.addAttributeBoost(ForgeMod.ENTITY_GRAVITY.get(), "Entity Gravity Modifier", "b021e727-9d0f-386c-8d2f-0818b0a26fe6", -0.2, 0);
        boostManager.addAttributeBoost(ForgeMod.ENTITY_REACH.get(), "Entity Reach Modifier", "61e0402c-431a-30cb-8a0a-9e8f88999665", 0.75, 0);
        boostManager.addAttributeBoost(ForgeMod.BLOCK_REACH.get(), "Block Reach Modifier", "da362ee3-f7a8-36a4-b9c3-f9a978689c7b", 0.75, 0);
		this.spiralParticle = new SpiralParticle(5, 1);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        ArrayList<ItemStack> cures = new ArrayList<>();
        cures.add(new ItemStack(Items.MILK_BUCKET));
        return cures;
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        tickCounter = 0;
		System.out.println(tickCounter);

        boostManager.startBoost(entity);
        super.addAttributeModifiers(entity, attributeMap, amplifier);
        Level world = entity.level();
        Vec3 entityPos = entity.position();

        if (!world.isClientSide()) {
            world.playSound(null, BlockPos.containing(entityPos.x, entityPos.y, entityPos.z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kratif_expension:egeberkchargeup")), SoundSource.AMBIENT, 1, 1);
        } else {
            world.playLocalSound(entityPos.x, entityPos.y, entityPos.z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kratif_expension:egeberkchargeup")), SoundSource.AMBIENT, 1, 1, false);
        }
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
		if (!(entity.level() instanceof ServerLevel _level) ) {
			return;
		}

        this.tickCounter += 1;
        EgebeRushEffectProcedure.execute(entity, this.tickCounter);
        double progress = (double) tickCounter / DURATION_TICKS;

		//spiral effect
		if (entity.level() instanceof ServerLevel) {
			boostManager.setProgress(progress, entity);
			spiralParticle.setSpeed(1 + 5 * progress);
			spiralParticle.render(_level, entity, ParticleTypes.GLOW_SQUID_INK, 5);
		}
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
        EgebeRushExpires.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
        boostManager.stopBoost(entity);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
