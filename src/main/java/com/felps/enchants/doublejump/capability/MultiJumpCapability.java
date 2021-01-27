package com.felps.enchants.doublejump.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface MultiJumpCapability extends INBTSerializable<CompoundNBT> {

    void setMultiJumpAmount(int amount);

    int getMultiJumpAmount();

    void incrementMultiJump();

    void resetMultiJump();

}
