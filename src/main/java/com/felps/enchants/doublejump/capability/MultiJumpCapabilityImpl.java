package com.felps.enchants.doublejump.capability;

import net.minecraft.nbt.CompoundNBT;

public class MultiJumpCapabilityImpl implements MultiJumpCapability {

    private int amount;

    @Override
    public void setMultiJumpAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int getMultiJumpAmount() {
        return amount;
    }

    @Override
    public void incrementMultiJump() {
        this.amount++;
    }

    @Override
    public void resetMultiJump() {
        this.amount = 0;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putInt(MultiJumpCapabilities.MULTI_JUMP_NAME, this.amount);
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.amount = nbt.getInt(MultiJumpCapabilities.MULTI_JUMP_NAME);
    }
}
