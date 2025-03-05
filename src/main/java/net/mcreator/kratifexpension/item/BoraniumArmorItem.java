package net.mcreator.kratifexpension.item;

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

public abstract class BoraniumArmorItem extends ArmorItem {
    private static final UUID ATTACK_SPEED_UUID = UUID.fromString("1a2b3c4d-1234-5678-9abc-def012345678");
    private static final UUID ATTACK_DAMAGE_UUID = UUID.fromString("d3843b9e-8d5a-41d2-b75f-3a17a2bff8e3");
    private static final UUID KNOCKBACK_RESISTANCE_UUID = UUID.fromString("f54c57d7-1234-4d2a-bb3d-0f12e1c3abcd");
    private static final UUID MAX_HEALTH_UUID = UUID.fromString("c274e1b1-9e56-482f-b68f-a5f2c42bfe23");
    private static final UUID MOVEMENT_SPEED_UUID = UUID.fromString("c174e1b1-9e56-4a3f-b68f-a9f5c42bfe23");

    public BoraniumArmorItem(ArmorItem.Type type, Item.Properties properties) {
        super(new ArmorMaterial() {
            @Override
            public int getDurabilityForType(ArmorItem.Type type) {
                return new int[]{13, 15, 16, 11}[type.getSlot().getIndex()] * 40;
            }

            @Override
            public int getDefenseForType(ArmorItem.Type type) {
                return new int[]{4, 8, 10, 5}[type.getSlot().getIndex()];
            }

            @Override
            public int getEnchantmentValue() {
                return 20;
            }

            @Override
            public SoundEvent getEquipSound() {
                return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_chain"));
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.of(new ItemStack(KratifExpensionModItems.BORANIUM_INGOT.get()));
            }

            @Override
            public String getName() {
                return "boranium_armor";
            }

            @Override
            public float getToughness() {
                return 3.2f;
            }

            @Override
            public float getKnockbackResistance() {
                return 0.12f;
            }
        }, type, properties);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getDefaultAttributeModifiers(slot));

        if (slot == this.getType().getSlot()) {
            if (slot == EquipmentSlot.HEAD) {
                builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_UUID, "Boranium attack speed boost", 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
                builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED_UUID, "Boranium movement speed boost", -0.01, AttributeModifier.Operation.MULTIPLY_TOTAL));
            } else if (slot == EquipmentSlot.CHEST) {
                builder.put(Attributes.MAX_HEALTH, new AttributeModifier(MAX_HEALTH_UUID, "Boranium health boost", 4.0, AttributeModifier.Operation.ADDITION));
                builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED_UUID, "Boranium movement speed boost", -0.02, AttributeModifier.Operation.MULTIPLY_TOTAL));
            } else if (slot == EquipmentSlot.LEGS) {
                builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(KNOCKBACK_RESISTANCE_UUID, "Boranium knockback resistance", 0.2, AttributeModifier.Operation.ADDITION));
                builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED_UUID, "Boranium movement speed boost", -0.01, AttributeModifier.Operation.MULTIPLY_TOTAL));
            } else if (slot == EquipmentSlot.FEET) {
                builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_UUID, "Boranium attack boost", 0.5, AttributeModifier.Operation.ADDITION));
                builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED_UUID, "Boranium movement speed boost", -0.01, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
        }
        return builder.build();
    }

    public static class Helmet extends BoraniumArmorItem {
        public Helmet() {
            super(ArmorItem.Type.HELMET, new Item.Properties().fireResistant());
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "kratif_expension:textures/models/armor/b_layer_1.png";
        }
    }

    public static class Chestplate extends BoraniumArmorItem {
        public Chestplate() {
            super(ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant());
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "kratif_expension:textures/models/armor/b_layer_1.png";
        }
    }

    public static class Leggings extends BoraniumArmorItem {
        public Leggings() {
            super(ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant());
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "kratif_expension:textures/models/armor/b_layer_2.png";
        }
    }

    public static class Boots extends BoraniumArmorItem {
        public Boots() {
            super(ArmorItem.Type.BOOTS, new Item.Properties().fireResistant());
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "kratif_expension:textures/models/armor/b_layer_1.png";
        }
    }
}

