package com.felps.enchants.backstab;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

import javax.annotation.Nonnull;

public class BackStabEnchantment extends Enchantment {

    public BackStabEnchantment() {
        super(Rarity.RARE, EnchantmentType.WEAPON, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND,
                EquipmentSlotType.OFFHAND});
        this.name = "Back Stab";
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    protected boolean canApplyTogether(@Nonnull Enchantment enchantment) {
        return !(enchantment instanceof DamageEnchantment);
    }
}
