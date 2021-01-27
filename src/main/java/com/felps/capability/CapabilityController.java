package com.felps.capability;

import com.felps.EnchantsDoFelps;
import com.felps.enchants.Enchantments;
import com.felps.enchants.doublejump.capability.MultiJumpCapabilities;
import com.felps.enchants.doublejump.capability.MultiJumpCapability;
import com.felps.enchants.doublejump.capability.MultiJumpCapabilityImpl;
import com.google.common.base.CaseFormat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EnchantsDoFelps.ID)
public class CapabilityController {

    public static void register() {

        CapabilityManager.INSTANCE.register(MultiJumpCapability.class, new CapabilityStorage<>(), MultiJumpCapabilityImpl::new);
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void onAttachCapabilitiesEntity(final AttachCapabilitiesEvent<Entity> evt) {

        if (evt.getObject() instanceof PlayerEntity) {

            evt.addCapability(Enchantments.locate(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, MultiJumpCapabilities.MULTI_JUMP_NAME)), new CapabilityDispatcher<>(new MultiJumpCapabilityImpl(), MultiJumpCapabilities.MULTI_JUMP));
        }
    }
}
