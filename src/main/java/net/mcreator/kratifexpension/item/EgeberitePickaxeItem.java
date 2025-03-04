
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
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber
public class EgeberitePickaxeItem extends PickaxeItem {
	private static final double MOVE_MULTIPLIER = 3.0; // Modify gravity strength
    private static final HashSet<UUID> playersWithBoost = new HashSet<>();

	public EgeberitePickaxeItem() {
		super(new Tier() {
			public int getUses() {
				return 100;
			}

			public float getSpeed() {
				return 11f;
			}

			public float getAttackDamageBonus() {
				return 5f;
			}

			public int getLevel() {
				return 4;
			}

			public int getEnchantmentValue() {
				return 2;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of();
			}
		}, 1, -2.8f, new Item.Properties().fireResistant());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.translatable("item.kratif_expension.egeberite_pickaxe.description_0"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);

		if (entity.hasEffect(KratifExpensionModMobEffects.EGEBE_RUSH.get())) {
			if (!playersWithBoost.contains(entity.getUUID())) {
                playersWithBoost.add(entity.getUUID());
				world.playSound(null, entity.blockPosition(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.5F);
            }
		}

		//BoraniumSwordRightclickedProcedure.execute(world, entity, ar.getObject());
		return ar;
	}

	@SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        if (playersWithBoost.contains(player.getUUID()) && !player.onGround()) {
            Vec3 velocity = player.getLookAngle().scale(MOVE_MULTIPLIER * 0.1);
            player.addDeltaMovement(velocity);
        } else {
			playersWithBoost.remove(player.getUUID());
		}
    }
}
