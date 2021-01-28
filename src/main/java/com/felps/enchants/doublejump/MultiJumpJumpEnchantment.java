package com.felps.enchants.doublejump;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class MultiJumpJumpEnchantment extends Enchantment {

    public MultiJumpJumpEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND,
                EquipmentSlotType.OFFHAND});
        this.name = "Multi Jump";
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return enchantmentLevel * 10;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 15;
    }

    @Override
    public boolean isTreasureEnchantment() {
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }


}
