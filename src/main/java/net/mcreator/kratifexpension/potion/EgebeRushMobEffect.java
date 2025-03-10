package net.mcreator.kratifexpension.potion;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.mcreator.kratifexpension.procedures.EgebeRushEffectProcedure;
import net.mcreator.kratifexpension.procedures.EgebeRushExpires;
import net.mcreator.kratifexpension.procedures.SpiralParticle;
import net.mcreator.kratifexpension.init.KratifExpensionModMobEffects;
import net.mcreator.kratifexpension.init.KratifExpensionModParticleTypes;
import net.mcreator.kratifexpension.procedures.AttributeBoostManager;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@Mod.EventBusSubscriber()
public class EgebeRushMobEffect extends MobEffect {
    private int tickCounter = 0;
    private static final double DURATION_TICKS = 200.0d; // 10 seconds (20 ticks per second)
    private final AttributeBoostManager boostManager = new AttributeBoostManager();
	private SpiralParticle spiralParticle;

    public EgebeRushMobEffect() {
        super(MobEffectCategory.BENEFICIAL, -3342388);
        boostManager.addAttributeBoost(Attributes.MOVEMENT_SPEED, "Speed Boost Modifier", "e8d1a5b0-8f78-4e6d-8e49-3d4b648495ad", 2, 0.75);
        boostManager.addAttributeBoost(ForgeMod.SWIM_SPEED.get(), "Swim Speed Boost Modifier", "a4b2c3d4-e5f6-7890-abcd-ef1234567890", 1.5, 0.25);
        boostManager.addAttributeBoost(Attributes.ATTACK_SPEED, "Attack Speed Boost Modifier", "ed4f04ac-be89-3fff-87fd-10792774608a", 1.5, 0.5);
        boostManager.addAttributeBoost(Attributes.JUMP_STRENGTH, "Jump Strength Boost Modifier", "f437e174-de9f-3127-9160-ebca06139483", 4, 1);
        boostManager.addAttributeBoost(ForgeMod.ENTITY_GRAVITY.get(), "Entity Gravity Modifier", "b021e727-9d0f-386c-8d2f-0818b0a26fe6", -.2, 0);
        boostManager.addAttributeBoost(ForgeMod.ENTITY_REACH.get(), "Entity Reach Modifier", "61e0402c-431a-30cb-8a0a-9e8f88999665", 0.75, 0);
        boostManager.addAttributeBoost(ForgeMod.BLOCK_REACH.get(), "Block Reach Modifier", "da362ee3-f7a8-36a4-b9c3-f9a978689c7b", 0.75, 0);
        boostManager.addAttributeBoost(Attributes.FLYING_SPEED, "Fly Speed Modifier", "c16f6db4-cf99-4303-b858-1925c1355057", 1.5, .25);
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
            world.playSound(null, BlockPos.containing(entityPos.x, entityPos.y, entityPos.z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kratif_expension:egeberushmusic")), SoundSource.PLAYERS, 1, 1);
        } else {
            world.playLocalSound(entityPos.x, entityPos.y, entityPos.z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kratif_expension:egeberkchargeup")), SoundSource.AMBIENT, 1, 1, false);
            world.playLocalSound(entityPos.x, entityPos.y, entityPos.z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kratif_expension:egeberushmusic")), SoundSource.PLAYERS, 1, 1, false);
        }
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity.level().isClientSide()) {
			return;
		}
        
        ServerLevel _level = (ServerLevel)entity.level();

        this.tickCounter += 1;
        EgebeRushEffectProcedure.execute(entity, this.tickCounter);
        double progress = Math.min((double)Math.sqrt((tickCounter / DURATION_TICKS)), 1.0d);

        entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 2, (int)((progress) * 4 + 3), false, false));
        entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 2, (int)((progress) * 5 + 2), false, false));

		//spiral effect
		boostManager.setProgress(progress, entity);
		spiralParticle.setSpeed(1 + 4 * Math.pow(progress, 4));
		spiralParticle.render(_level, entity, ParticleTypes.GLOW_SQUID_INK, 5);
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

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
    if (event.getEntity() instanceof ServerPlayer player) {
        if (player.hasEffect(KratifExpensionModMobEffects.EGEBE_RUSH.get())) {
            event.setNewSpeed(event.getNewSpeed() * 3.0F); // Triple mining speed
        }
    }
    }

    @SubscribeEvent
    public static void onPlayerLand(LivingFallEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return; // Ensure it's a player
        //System.out.println("Landed");
        
        // Check if the player has the specific potion effect
        if (!player.hasEffect(KratifExpensionModMobEffects.EGEBE_RUSH.get())) return; // Replace with your custom effect if needed

        float fallDistance = event.getDistance(); // Get the distance fallen
        
        if (fallDistance >= 5) { // Minimum fall distance threshold
            // Get nearby entities within a radius (5 blocks in this example)

            float sqrtDist = (float)Math.sqrt(fallDistance / 5.0d);

            List<LivingEntity> nearbyEntities = player.level().getEntitiesOfClass(
                LivingEntity.class, 
                player.getBoundingBox().inflate(sqrtDist * 5), // Expands search area
                e -> e != player // Exclude the player
            );

            for (LivingEntity entity : nearbyEntities) {
                // Launch the entity into the air
                entity.setDeltaMovement(entity.getDeltaMovement().add(0, Math.sqrt(2 * entity.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).getValue() * fallDistance), 0)); // Adjust height as needed

                // Apply damage (5.0F in this example)
                entity.hurt(player.damageSources().magic(), (float)sqrtDist * 5);

                if (!entity.hasEffect(KratifExpensionModMobEffects.EGEBE_RUSHED.get())) {
                    entity.addEffect(new MobEffectInstance(KratifExpensionModMobEffects.EGEBE_RUSHED.get(), 10, 0));
                }
            }

            // Play a sound effect for impact
            player.level().playSound(null, player.blockPosition(), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }

    @SubscribeEvent
    public static void onPlayerJump(LivingJumpEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        // Check if the player has the EGEBE_RUSH effect
        if (!player.hasEffect(KratifExpensionModMobEffects.EGEBE_RUSH.get())) return;

        Vec3 motion = player.getDeltaMovement();
        player.setDeltaMovement(motion.x, motion.y + 1, motion.z); // Adjust jump height

        // Play jump sound effect
        player.level().playSound(null, player.blockPosition(), SoundEvents.RABBIT_JUMP, SoundSource.PLAYERS, 1.0F, 1.2F);
    }

    @SubscribeEvent
    public static void onLivingFall(LivingFallEvent event) {
        // Check if the entity is a player (optional)
        if ((event.getEntity() instanceof Player plr) && plr.hasEffect(KratifExpensionModMobEffects.EGEBE_RUSH.get())) {
            // Reduce damage by 50% (you can change the multiplier)
            float reducedDamage = event.getDamageMultiplier() * 0.1f; 

            // Set the new fall damage multiplier
            event.setDamageMultiplier(reducedDamage);

            // If you want to completely remove fall damage, use:
            // event.setDamageMultiplier(0);
        }
    }


    private static final int MINE_RADIUS = 2;

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        Level world = player.level();
        BlockPos origin = event.getPos();
        ItemStack heldItem = player.getMainHandItem();

        if (world.isClientSide() || !(world instanceof ServerLevel serverLevel)) return;

        // Check if player has the required effect and is using the right tool
        if (player.hasEffect(KratifExpensionModMobEffects.EGEBE_RUSH.get()) && isValidTool(heldItem)) { // Change effect if needed
            BlockState targetState = world.getBlockState(origin);
            Block targetBlock = targetState.getBlock();
            Set<BlockPos> minedBlocks = new HashSet<>();

            // If it's a log, chop the whole tree
            if (isLogBlock(targetBlock)) {
                chopTree(serverLevel, origin, targetBlock, minedBlocks, player);
            } 
            // If it's any other block, mine in a cube
            else {
                mineConnectedBlocks(serverLevel, origin, targetBlock, minedBlocks, MINE_RADIUS, player);
            }

            // Damage the tool based on blocks mined
            damageTool(player, heldItem, minedBlocks.size());
        }
    }

    private static boolean isValidTool(ItemStack item) {
        Item tool = item.getItem();
        return tool.isCorrectToolForDrops(Blocks.STONE.defaultBlockState()) || // Pickaxes
               tool.isCorrectToolForDrops(Blocks.OAK_LOG.defaultBlockState()); // Axes
    }

    private static boolean isLogBlock(Block block) {
        return block.defaultBlockState().is(Blocks.OAK_LOG) || block.defaultBlockState().is(Blocks.SPRUCE_LOG) ||
               block.defaultBlockState().is(Blocks.BIRCH_LOG) || block.defaultBlockState().is(Blocks.JUNGLE_LOG) ||
               block.defaultBlockState().is(Blocks.ACACIA_LOG) || block.defaultBlockState().is(Blocks.DARK_OAK_LOG) ||
               block.defaultBlockState().is(Blocks.MANGROVE_LOG);
    }

    private static void mineConnectedBlocks(ServerLevel world, BlockPos pos, Block targetBlock, Set<BlockPos> mined, int radius, Player player) {
        if (radius < 0 || mined.contains(pos)) return;
        BlockState state = world.getBlockState(pos);

        if (state.getBlock() != targetBlock) return; // Only mine if it's the same type
        mined.add(pos);
        world.destroyBlock(pos, true, player); // Break block and drop items

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    if (dx != 0 || dy != 0 || dz != 0) {
                        mineConnectedBlocks(world, pos.offset(dx, dy, dz), targetBlock, mined, radius - 1, player);
                    }
                }
            }
        }
    }

    private static void chopTree(ServerLevel world, BlockPos pos, Block logBlock, Set<BlockPos> logs, Player player) {
        findTreeLogs(world, pos, logBlock, logs);

        for (BlockPos logPos : logs) {
            world.destroyBlock(logPos, true, player);
        }
    }

    private static void findTreeLogs(ServerLevel world, BlockPos pos, Block logBlock, Set<BlockPos> logs) {
        if (logs.contains(pos)) return;

        BlockState state = world.getBlockState(pos);
        if (state.getBlock() != logBlock) return;

        logs.add(pos);
        for (int dy = 1; dy <= 20; dy++) { // Search upward to find the whole tree
            findTreeLogs(world, pos.above(dy), logBlock, logs);
        }
    }

    private static void damageTool(Player player, ItemStack tool, int blocksMined) {
        if (!tool.isDamageableItem()) return; // Only apply damage if the tool is breakable

        int durabilityLoss = Math.max(1, blocksMined / 3); // Adjust durability loss scaling
        tool.hurtAndBreak(durabilityLoss, player, (p) -> p.broadcastBreakEvent(p.getUsedItemHand()));
    }
}
