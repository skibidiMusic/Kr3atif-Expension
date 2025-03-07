
package net.mcreator.kratifexpension.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.level.NoteBlockEvent.Play;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.mcreator.kratifexpension.init.KratifExpensionModMobEffects;
import net.mcreator.kratifexpension.procedures.BoraniumSwordRightclickedProcedure;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber
public class EgeberitePickaxeItem extends PickaxeItem {
    private static final double MOVE_MULTIPLIER = 1.25; // Speed boost multiplier
    private static final int EFFECT_DURATION_TICKS = 30; // 1.5 seconds
    private static final int COOLDOWN_TICKS = 20; // 1 seconds
    private static final HashMap<UUID, Integer> activePlayers = new HashMap<>();

    public EgeberitePickaxeItem() {
        super(new Tier() {
            public int getUses() { return 1000; }
            public float getSpeed() { return 20f; }
            public float getAttackDamageBonus() { return 5f; }
            public int getLevel() { return 4; }
            public int getEnchantmentValue() { return 20; }
            public Ingredient getRepairIngredient() { return Ingredient.of(); }
        }, 1, -2.8f, new Item.Properties().fireResistant());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        InteractionResultHolder<ItemStack> result = super.use(world, player, hand);
        UUID playerId = player.getUUID();

        // Check if the item is on cooldown
        if (player.getCooldowns().isOnCooldown(this)) {
            return result;
        }

        // Check if the player has the Egebe Rush effect
        if (!player.hasEffect(KratifExpensionModMobEffects.EGEBE_RUSH.get())) {
            return result;
        }

        // Apply the boost effect
        activePlayers.put(playerId, EFFECT_DURATION_TICKS);
        world.playSound(null, player.blockPosition(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.5F);

        // Set cooldown so it visually greys out in hotbar
        player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);

        return result;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        UUID playerId = player.getUUID();

        // Check if the player is affected
        if (activePlayers.containsKey(playerId)) {
            int timeLeft = activePlayers.get(playerId);
            if (timeLeft > 0) {
                Vec3 lookAngle = player.getLookAngle();
                double speed = MOVE_MULTIPLIER * 0.1;
                Vec3 velocity = lookAngle.scale(speed);
                player.addDeltaMovement(velocity);

                // Particle Effect
                if (player.level() instanceof ServerLevel _level) {
                    _level.sendParticles(ParticleTypes.SPIT, player.getX(), player.getY() + 1, player.getZ(), 4, 0.5, 0.5, 0.5, 0.1);
                }

                activePlayers.put(playerId, timeLeft - 1);
            } else {
                activePlayers.remove(playerId); // Remove effect when duration ends
            }
        }
    }
}
