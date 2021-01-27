package com.felps.enchants.doublejump.message;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;

public interface MultiJumpMessage {
    void encodeMessage(MultiJumpMessage multiJumpMessage, PacketBuffer buffer);

    <T extends MultiJumpMessage> T decodeMessage(PacketBuffer buffer);

    void processPacket(ServerPlayerEntity sender);
}
