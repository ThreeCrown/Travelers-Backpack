package com.tiviacz.travelersbackpack.components;

import net.minecraft.nbt.CompoundTag;

public record RenderInfo(CompoundTag compoundTag) {
    public static final RenderInfo EMPTY = new RenderInfo(new CompoundTag());

    public boolean isEmpty() {
        return this.compoundTag.isEmpty();
    }

    public boolean hasTanks() {
        return false;
    }

    public void updateCapacity(int capacity) {
        if(this.compoundTag.contains("Capacity")) {
            this.compoundTag.putInt("Capacity", capacity);
        }
    }

    public int getCapacity() {
        if(this.compoundTag.contains("Capacity")) {
            return this.compoundTag.getInt("Capacity");
        }
        return 0;
    }

    public static RenderInfo createCreativeTabInfo() {
        return EMPTY;
    }

    @Override
    public boolean equals(Object pOther) {
        if(this == pOther) {
            return true;
        } else {
            return pOther instanceof RenderInfo renderInfo && this.compoundTag.toString().equals(renderInfo.compoundTag.toString());
        }
    }
}
