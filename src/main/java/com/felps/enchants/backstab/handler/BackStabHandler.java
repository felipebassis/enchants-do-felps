package com.felps.enchants.backstab.handler;

import com.felps.enchants.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BackStabHandler {

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public void onBackStab(LivingHurtEvent livingHurtEvent) {
        LivingEntity attackedEntity = livingHurtEvent.getEntityLiving();
        DamageSource source = livingHurtEvent.getSource();
        LivingEntity attacker = (LivingEntity) source.getTrueSource();
        if (attacker == null) {
            return;
        }
        ItemStack heldItemMainHand = attacker.getHeldItemMainhand();
        Item item = heldItemMainHand.getItem();
        if (item instanceof SwordItem) {
            int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.BACK_STAB_ENCHANTMENT, heldItemMainHand);
            if (enchantmentLevel > 0 && attacker.getHorizontalFacing() == attackedEntity.getHorizontalFacing()) {
                attackedEntity.attackEntityFrom(DamageSource.GENERIC, livingHurtEvent.getAmount() * (enchantmentLevel + 1));
                attackedEntity.setRevengeTarget(attacker);
            }
        }
    }
}
