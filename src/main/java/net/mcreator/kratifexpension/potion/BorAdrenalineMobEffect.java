package net.mcreator.kratifexpension.potion;

import net.mcreator.kratifexpension.procedures.BorAdrenalineEffectStartedappliedProcedure;
import net.mcreator.kratifexpension.procedures.SpiralParticle;
import net.mcreator.kratifexpension.procedures.BorAdrenalineEffectExpiresProcedure;
import net.mcreator.kratifexpension.procedures.BorAdrenalineActiveTickConditionProcedure;
import net.mcreator.kratifexpension.init.KratifExpensionModParticleTypes;
import net.mcreator.kratifexpension.procedures.AttributeBoostManager;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.ForgeConfig.Server;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class BorAdrenalineMobEffect extends MobEffect {
    private static final int DURATION_TICKS = 300; // 15 seconds
    private static final int BOOST_INTERVAL = 30; // Every 3 seconds (60 ticks)
	private static final Random random = new Random();

    private int tickCounter = 0;
	private double boostProgress = 0;

	private SpiralParticle spiralParticle;
    private final AttributeBoostManager boostManager = new AttributeBoostManager();

    public BorAdrenalineMobEffect() {
        super(MobEffectCategory.BENEFICIAL, -6737152);

        boostManager.addAttributeBoost(Attributes.ARMOR, "Armor Boost", "7474145f-46bf-3507-9d5f-b52cc6fb25e3", 1.5, 0.25);
        boostManager.addAttributeBoost(Attributes.ATTACK_DAMAGE, "Attack Damage Boost", "46598dcd-1de3-3575-9637-e4d13bf7b63b", 1.5, 0.25);
        boostManager.addAttributeBoost(Attributes.ATTACK_SPEED, "Attack Speed Boost", "6d04b947-4ac0-3c30-8832-6ce584169a0e", 0.25, 0.05);
        boostManager.addAttributeBoost(Attributes.KNOCKBACK_RESISTANCE, "Knockback Resistance Boost", "c4042de0-cb60-323d-a39f-fd7574ce1802", 1, 0.1);
        boostManager.addAttributeBoost(Attributes.ATTACK_KNOCKBACK, "Attack Knockback Boost", "52daf1fe-da15-31a5-8325-d560c9e6d77d", -0.25, -0.1);
        boostManager.addAttributeBoost(Attributes.ARMOR_TOUGHNESS, "Armor Toughness Boost", "1f499e24-8345-343a-8e54-ebf18f12b30e", 1.5, 0.1);
        boostManager.addAttributeBoost(Attributes.MOVEMENT_SPEED, "Movement Speed Boost", "ae434afd-53ca-323c-9cd9-d03e4141f8ac", 0.2, 0.05);
        boostManager.addAttributeBoost(ForgeMod.ENTITY_REACH.get(), "Entity Reach Boost", "a01b6595-004a-3d00-9090-bf5378ef44f3", 0.8, 0.2);

		this.spiralParticle = new SpiralParticle(3, 1);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return new ArrayList<>();
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.addAttributeModifiers(entity, attributeMap, amplifier);
        BorAdrenalineEffectStartedappliedProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);

		tickCounter = 0;
		boostProgress = 0;

		if (entity.level() instanceof ServerLevel _Level) {
			boostManager.startBoost(entity);
			spiralParticle.setSpeed(1 + 2 * boostProgress);
			spiralParticle.render(_Level, entity, KratifExpensionModParticleTypes.BORADRENALINE_FLAMES.get(), 2);
		}
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        tickCounter+= 1;
        BorAdrenalineActiveTickConditionProcedure.execute(entity, entity.level(), entity.getX(), entity.getY(), entity.getZ(), tickCounter, boostProgress);

		if (!(entity.level() instanceof ServerLevel _Level)) {
			return;
		}

        if (tickCounter % BOOST_INTERVAL == 0) {
            double progress = (double) tickCounter / DURATION_TICKS;
			boostProgress = progress;
            boostManager.setProgress(progress, entity);
            spawnLightning(entity.level(), entity.blockPosition(), progress == 1, entity);
            entity.heal(5.0f);
        }

		if (entity.level() instanceof ServerLevel) {
			spiralParticle.setSpeed(1 + 2 * Math.pow(boostProgress, 4));
			spiralParticle.render(_Level, entity, KratifExpensionModParticleTypes.BORADRENALINE_FLAMES.get(), 2);
            entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2, 0, false, false));
		}
    }

    private static void spawnLightningEffect(Vec3 pos, ServerLevel _level) {
        LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);

        entityToSpawn.moveTo(pos);
        entityToSpawn.setVisualOnly(true);

        _level.addFreshEntity(entityToSpawn);
    }

    private void spawnLightning(Level world, BlockPos pos, boolean noRandom, LivingEntity plr) {
		if (world instanceof ServerLevel _level) {
			LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);

            double offsetX, offsetZ;

            if (!noRandom) {
                offsetX = random.nextInt(-3, 4);
                offsetZ = random.nextInt(-3, 4); // Range: -5 to +5
            }
            else {
                offsetX = 0;
                offsetZ = 0;
            }

			//entityToSpawn.moveTo(pos.getX() + offsetX, pos.getY(), pos.getZ() + offsetZ);
            BlockPos targetPos = BlockPos.containing(pos.getX() + offsetX, pos.getY(), pos.getZ() + offsetZ); 

			entityToSpawn.moveTo(Vec3.atBottomCenterOf(targetPos));
			entityToSpawn.setVisualOnly(true);

            float damageAmount = 5.0F; // Adjust damage value
            AABB area = new AABB(targetPos).inflate(10); // 3-block radius
            List<LivingEntity> entities = world.getEntitiesOfClass(LivingEntity.class, area);

            for (LivingEntity entity : entities) {
                if (entity != plr) { // Skip the player
                    entity.hurt(entity.damageSources().lightningBolt(), damageAmount);
                    entity.setSecondsOnFire(5);
                    if (entity.fireImmune()) {
                        entity.hurt(entity.damageSources().onFire(), 2.0F); // Deals 1 heart of fire damage
                    }

                    // Calculate knockback direction
                    Vec3 knockbackDirection = entity.position().subtract(plr.position()).normalize().scale(0.5);
                    entity.setDeltaMovement(knockbackDirection.x, 0.3, knockbackDirection.z);
                    entity.hurtMarked = true; // Ensure motion updates properly

                    // Spawn lightning effect at the entity's position
                    spawnLightningEffect(entity.position(), _level);
                }
            }

			_level.addFreshEntity(entityToSpawn);
		}
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
        BorAdrenalineEffectExpiresProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
        boostManager.stopBoost(entity);
        entity.hurt(entity.damageSources().onFire(), 10.0f);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
