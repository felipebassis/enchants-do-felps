package com.felps.enchants.doublejump.capability;

import com.felps.enchants.doublejump.handler.MultiJumpHandler;
import com.felps.enchants.doublejump.message.MultiJumpSyncCapabilityMessage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MultiJumpCapabilitySyncHandler {
    @SuppressWarnings({"unused", "ConstantConditions"})
    @SubscribeEvent
    public void onEntityJoinWorld(final EntityJoinWorldEvent evt) {

        if (evt.getEntity() instanceof ServerPlayerEntity) {

            ServerPlayerEntity player = (ServerPlayerEntity) evt.getEntity();
            int jumps = player.getCapability(MultiJumpCapabilities.MULTI_JUMP).map(MultiJumpCapability::getMultiJumpAmount).orElse(0);
            if (jumps > 0) {
                MultiJumpHandler.getInstance().sendTo(new MultiJumpSyncCapabilityMessage(jumps), player);
            }
        }
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onLivingFall(final LivingFallEvent evt) {

        if (evt.getEntityLiving() instanceof PlayerEntity) {

            evt.setDistance(this.onGroundHit((PlayerEntity) evt.getEntityLiving(), evt.getDistance()));
        }
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onPlayerFall(final PlayerFlyableFallEvent evt) {

        evt.setDistance(this.onGroundHit(evt.getPlayer(), evt.getDistance()));
    }

    @SuppressWarnings("ConstantConditions")
    private float onGroundHit(PlayerEntity player, float fallDistance) {

        int jumps = player.getCapability(MultiJumpCapabilities.MULTI_JUMP)
                .map(MultiJumpCapability::getMultiJumpAmount)
                .orElse(0);
        player.getCapability(MultiJumpCapabilities.MULTI_JUMP).ifPresent(MultiJumpCapability::resetMultiJump);
        if (jumps > 0) {

            float f = 1.25F;
            if (player.isPotionActive(Effects.JUMP_BOOST)) {

                f += 0.6875F * (player.getActivePotionEffect(Effects.JUMP_BOOST).getAmplifier() + 1.0F);
            }

            return Math.max(0.0F, fallDistance - jumps * f);
        }

        return fallDistance;
    }
}
