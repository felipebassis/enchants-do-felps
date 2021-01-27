package com.felps.enchants.doublejump.message;

import com.felps.enchants.doublejump.handler.MultiJumpHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;

public class MultiJumpMessageImpl implements MultiJumpMessage {

    @Override
    public void encodeMessage(MultiJumpMessage multiJumpMessage, PacketBuffer buffer) {
        //No Implementation
    }

    @Override
    public MultiJumpMessage decodeMessage(PacketBuffer buffer) {
        return this;
    }

    @Override
    public void processPacket(ServerPlayerEntity sender) {
        MultiJumpHandler.doJump(sender);
    }
}
