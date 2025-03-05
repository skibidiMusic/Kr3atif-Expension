
package net.mcreator.kratifexpension.item;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import net.mcreator.kratifexpension.procedures.EgeberiteArmorHelmetTickEventProcedure;
import net.mcreator.kratifexpension.init.KratifExpensionModItems;

import com.google.common.collect.Iterables;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap;
import net.mcreator.kratifexpension.init.KratifExpensionModItems;
import java.util.UUID;

public abstract class EgeberiteArmorItem extends ArmorItem {

    private static final UUID ATTACK_SPEED_UUID = UUID.fromString("1a5b3c4d-1234-5678-9abc-def012345678");
    private static final UUID ATTACK_DAMAGE_UUID = UUID.fromString("d2343b9e-8d5a-41d2-b75f-3a17a2bff8e3");
    private static final UUID KNOCKBACK_RESISTANCE_UUID = UUID.fromString("f11c57d7-1234-4d2a-bb3d-0f12e1c3abcd");
    private static final UUID ARMOR_TOUGHNESS_UUID = UUID.fromString("c274e5b1-9e56-482f-b68f-a5f2c42bfe23");
    private static final UUID MOVEMENT_SPEED_UUID = UUID.fromString("c174e2a1-9e56-4a3f-b68f-a9f5c42bfe21");
    private static final UUID MOVEMENT_SPEED_UUID2 = UUID.fromString("c174e2a1-9e56-4a3f-b68f-a9f5c42bfe22");
    private static final UUID MOVEMENT_SPEED_UUID3 = UUID.fromString("c174e2a1-9e56-4a3f-b68f-a9f5c42bfe23");
    private static final UUID MOVEMENT_SPEED_UUID4 = UUID.fromString("c174e2a1-9e56-4a3f-b68f-a9f5c42bfe24");
    
	public EgeberiteArmorItem(ArmorItem.Type type, Item.Properties properties) {
		super(new ArmorMaterial() {
			@Override
			public int getDurabilityForType(ArmorItem.Type type) {
				return new int[]{13, 15, 16, 11}[type.getSlot().getIndex()] * 15;
			}

			@Override
			public int getDefenseForType(ArmorItem.Type type) {
				return new int[]{3, 7, 9, 4}[type.getSlot().getIndex()];
			}

			@Override
			public int getEnchantmentValue() {
				return 9;
			}

			@Override
			public SoundEvent getEquipSound() {
				return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_diamond"));
			}

			@Override
			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(KratifExpensionModItems.EGEBERITE.get()));
			}

			@Override
			public String getName() {
				return "egeberite_armor";
			}

			@Override
			public float getToughness() {
				return 0f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0f;
			}
		}, type, properties);
	}

	
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getDefaultAttributeModifiers(slot));

        if (slot == this.getType().getSlot()) {
            if (slot == EquipmentSlot.HEAD) {
                builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_UUID, "1 attack speed boost", 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
                builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED_UUID, "2 movement speed boost", 0.04, AttributeModifier.Operation.MULTIPLY_TOTAL));
            } else if (slot == EquipmentSlot.CHEST) {
             	builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_UUID, "3 attack boost", -0.1, AttributeModifier.Operation.ADDITION));
                builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED_UUID2, "4 movement speed boost", 0.03, AttributeModifier.Operation.ADDITION));
            } else if (slot == EquipmentSlot.LEGS) {
                builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(KNOCKBACK_RESISTANCE_UUID, "5 knockback resistance", -0.1, AttributeModifier.Operation.ADDITION));
                builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED_UUID3, "6 movement speed boost", 0.02, AttributeModifier.Operation.ADDITION));
            } else if (slot == EquipmentSlot.FEET) {
                builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_UUID, "7 attack speed boost", 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
                builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED_UUID4, "8 movement speed boost", 0.06, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
        }
        return builder.build();
    }

	public static class Helmet extends EgeberiteArmorItem {
		public Helmet() {
			super(ArmorItem.Type.HELMET, new Item.Properties().fireResistant());
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "kratif_expension:textures/models/armor/e_layer_1.png";
		}

		@Override
		public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
			super.inventoryTick(itemstack, world, entity, slot, selected);
			if (entity instanceof Player player && Iterables.contains(player.getArmorSlots(), itemstack)) {
				EgeberiteArmorHelmetTickEventProcedure.execute();
			}
		}
	}

	public static class Chestplate extends EgeberiteArmorItem {
		public Chestplate() {
			super(ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant());
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "kratif_expension:textures/models/armor/e_layer_1.png";
		}
	}

	public static class Leggings extends EgeberiteArmorItem {
		public Leggings() {
			super(ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant());
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "kratif_expension:textures/models/armor/e_layer_2.png";
		}
	}

	public static class Boots extends EgeberiteArmorItem {
		public Boots() {
			super(ArmorItem.Type.BOOTS, new Item.Properties().fireResistant());
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "kratif_expension:textures/models/armor/e_layer_1.png";
		}
	}
}
