package com.felps.enchants.doublejump.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class MultiJumpCapabilities {

    @CapabilityInject(MultiJumpCapability.class)
    public static final Capability<MultiJumpCapability> MULTI_JUMP = null;
    public static final String MULTI_JUMP_NAME = "MultiJump";
}
