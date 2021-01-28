package com.felps.enchants;

import com.felps.EnchantsDoFelps;
import com.felps.enchants.backstab.BackStabEnchantment;
import com.felps.enchants.doublejump.MultiJumpJumpEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@SuppressWarnings("java:S1118")
@Mod.EventBusSubscriber(modid = EnchantsDoFelps.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EnchantsDoFelps.ID)
public class Enchantments {

    @ObjectHolder("double_jump")
    public static final Enchantment DOUBLE_JUMP_ENCHANTMENT = null;

    @ObjectHolder("back_stab")
    public static final Enchantment BACK_STAB_ENCHANTMENT = null;

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void onRegistryEnchantment(final RegistryEvent.Register<Enchantment> evt) {

        if (evt.getRegistry().equals(ForgeRegistries.ENCHANTMENTS)) {
            evt.getRegistry().register(new MultiJumpJumpEnchantment().setRegistryName(locate("double_jump")));
            evt.getRegistry().register(new BackStabEnchantment().setRegistryName(locate("back_stab")));
        }
    }

    public static ResourceLocation locate(String name) {
        return new ResourceLocation(EnchantsDoFelps.ID, name);
    }
}
