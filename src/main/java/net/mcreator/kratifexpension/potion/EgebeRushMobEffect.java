
package net.mcreator.kratifexpension.potion;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.kratifexpension.procedures.EgebeRushEffectProcedure;
import net.mcreator.kratifexpension.procedures.EgebeRushExpires;

import java.util.List;
import java.util.ArrayList;

public class EgebeRushMobEffect extends MobEffect {
	private int tickCounter = 0;

	public EgebeRushMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -3342388);
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "25dd9e45-1766-3be5-81fd-95a893e604a6", 1, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(Attributes.ATTACK_SPEED, "ed4f04ac-be89-3fff-87fd-10792774608a", 1, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(Attributes.JUMP_STRENGTH, "f437e174-de9f-3127-9160-ebca06139483", 1, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(ForgeMod.SWIM_SPEED.get(), "a9de68de-6e16-35ca-bb1c-e97f37225ea9", 1, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(ForgeMod.ENTITY_GRAVITY.get(), "b021e727-9d0f-386c-8d2f-0818b0a26fe6", 0, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(ForgeMod.ENTITY_REACH.get(), "61e0402c-431a-30cb-8a0a-9e8f88999665", .25, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(ForgeMod.BLOCK_REACH.get(), "da362ee3-f7a8-36a4-b9c3-f9a978689c7b", .25, AttributeModifier.Operation.MULTIPLY_TOTAL);
	}

	@Override
	public List<ItemStack> getCurativeItems() {
		ArrayList<ItemStack> cures = new ArrayList<ItemStack>();
		cures.add(new ItemStack(Items.MILK_BUCKET));
		return cures;
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.addAttributeModifiers(entity, attributeMap, amplifier);
		Level world = entity.level();

    	Vec3 entityPos = entity.position();
    	if (!world.isClientSide()) {
    	     world.playSound(null, BlockPos.containing(entityPos.x, entityPos.y, entityPos.z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kratif_expension:egeberkchargeup")), SoundSource.AMBIENT, 1, 1);
    	} else {
    	     world.playLocalSound(entityPos.x, entityPos.y, entityPos.z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kratif_expension:egeberkchargeup")), SoundSource.AMBIENT, 1, 1, false);
    	}
		//entity.level().particle(null, amplifier, amplifier, amplifier, amplifier, amplifier, amplifier);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		this.tickCounter += 1;
		EgebeRushEffectProcedure.execute(entity, this.tickCounter);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		EgebeRushExpires.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
