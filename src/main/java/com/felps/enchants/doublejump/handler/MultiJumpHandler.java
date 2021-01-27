package com.felps.enchants.doublejump.handler;

import com.felps.EnchantsDoFelps;
import com.felps.enchants.Enchantments;
import com.felps.enchants.doublejump.capability.MultiJumpCapabilities;
import com.felps.enchants.doublejump.capability.MultiJumpCapability;
import com.felps.enchants.doublejump.message.MultiJumpMessage;
import com.felps.enchants.doublejump.message.MultiJumpMessageImpl;
import com.felps.enchants.doublejump.message.MultiJumpSyncCapabilityMessage;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import static org.apache.http.params.CoreProtocolPNames.PROTOCOL_VERSION;

public class MultiJumpHandler {

    private static final MultiJumpHandler INSTANCE = new MultiJumpHandler();

    private static final SimpleChannel CHANNEL_INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(EnchantsDoFelps.ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);

    private int id;

    public static MultiJumpHandler getInstance() {
        return INSTANCE;
    }

    private MultiJumpHandler() {

    }

    public void init() {
        this.registerMessage(new MultiJumpMessageImpl());
        this.registerMessage(new MultiJumpSyncCapabilityMessage());
    }

    private <T extends MultiJumpMessage> void registerMessage(T multiJumpMessage) {
        CHANNEL_INSTANCE.registerMessage(id++, multiJumpMessage.getClass(),
                multiJumpMessage::encodeMessage,
                multiJumpMessage::decodeMessage,
                (multiJumpMessage1, contextSupplier) -> {
                    NetworkEvent.Context context = contextSupplier.get();
                    ServerPlayerEntity sender = context
                            .getSender();
                    if (sender != null) {
                        context.enqueueWork(() -> multiJumpMessage1.processPacket(sender));
                    }
                    context.setPacketHandled(true);
                });
    }

    @SuppressWarnings("ConstantConditions")
    public static boolean doJump(PlayerEntity playerEntity) {
        if (!allowJump(playerEntity)) {
            return false;
        }
        ItemStack itemStack = playerEntity.getItemStackFromSlot(EquipmentSlotType.FEET);

        int jumps = playerEntity.getCapability(MultiJumpCapabilities.MULTI_JUMP).map(MultiJumpCapability::getMultiJumpAmount).orElse(Integer.MAX_VALUE);
        if (jumps < EnchantmentHelper.getEnchantmentLevel(Enchantments.DOUBLE_JUMP_ENCHANTMENT, itemStack)) {
            playerEntity.jump();
            playerEntity.getCapability(MultiJumpCapabilities.MULTI_JUMP).ifPresent(MultiJumpCapability::incrementMultiJump);
            playerEntity.fallDistance = 0.0F;
            return true;
        }
        return false;
    }

    private static boolean allowJump(PlayerEntity player) {
        boolean performingAction = player.isOnGround() || player.isPassenger() || player.abilities.isFlying;
        boolean insideLiquid = player.isInWater() || player.isInLava();
        if (performingAction || insideLiquid) {

            return false;
        }

        ItemStack itemstack = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
        boolean fallFlyingReady = !player.isElytraFlying() && itemstack.getItem() == Items.ELYTRA && ElytraItem.isUsable(itemstack);


        return !fallFlyingReady || !player.isSneaking();
    }

    public void sendToServer(MultiJumpMessage multiJumpMessage) {
        CHANNEL_INSTANCE.sendToServer(multiJumpMessage);
    }

    public void sendTo(MultiJumpMessage multiJumpMessage, ServerPlayerEntity playerEntity) {
        CHANNEL_INSTANCE.send(PacketDistributor.PLAYER.with(() -> playerEntity), multiJumpMessage);
    }

}
