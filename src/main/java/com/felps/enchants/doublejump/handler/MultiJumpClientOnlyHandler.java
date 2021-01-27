package com.felps.enchants.doublejump.handler;

import com.felps.enchants.doublejump.capability.MultiJumpCapabilities;
import com.felps.enchants.doublejump.capability.MultiJumpCapability;
import com.felps.enchants.doublejump.message.MultiJumpMessageImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

@OnlyIn(Dist.CLIENT)
public class MultiJumpClientOnlyHandler {

    private static final Minecraft MC = Minecraft.getInstance();

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onKeyInput(final InputEvent.KeyInputEvent evt) {

        if (!MC.isGameFocused() || evt.getAction() != GLFW_PRESS) {

            return;
        }

        // can't use player.movementInput.jump as it triggers too often
        if(MC.gameSettings.keyBindJump.isKeyDown()) {

            ClientPlayerEntity player = MC.player;
            if (player != null && MultiJumpHandler.doJump(player)) {
                MultiJumpHandler.getInstance().sendToServer(new MultiJumpMessageImpl());
            }
        }
    }
}
