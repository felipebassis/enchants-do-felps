package com.felps.enchants.doublejump.message;

import com.felps.enchants.doublejump.capability.MultiJumpCapabilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;

import javax.annotation.Nullable;

public class MultiJumpSyncCapabilityMessage implements MultiJumpMessage {
    private int jumps;

    public MultiJumpSyncCapabilityMessage() {

    }

    public MultiJumpSyncCapabilityMessage(int jumps) {
        this.jumps = jumps;
    }

    @Override
    public void encodeMessage(MultiJumpMessage multiJumpMessage, final PacketBuffer buf) {

        buf.writeByte(this.jumps);
    }

    @Override
    public MultiJumpMessage decodeMessage(final PacketBuffer buf) {

        this.jumps = buf.readByte();
        return this;
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void processPacket(@Nullable final ServerPlayerEntity player) {

        if (player != null && !player.isOnGround()) {

            player.getCapability(MultiJumpCapabilities.MULTI_JUMP).ifPresent(cap -> cap.setMultiJumpAmount(this.jumps));
        }
    }
}
