package com.felps.enchants.doublejump;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.text.ITextComponent;

public class MultiJumpJumpEnchantment extends Enchantment {

    public MultiJumpJumpEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND,
                EquipmentSlotType.OFFHAND});
        this.name = "Multi Jump";
    }

    @Override
    public boolean isTreasureEnchantment() {
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public ITextComponent getDisplayName(int level) {
        return super.getDisplayName(level);
    }
}
