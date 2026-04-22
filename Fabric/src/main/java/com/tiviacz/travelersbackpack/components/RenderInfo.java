package com.tiviacz.travelersbackpack.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.tiviacz.travelersbackpack.inventory.FluidVariantWrapper;
import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.material.Fluids;

public record RenderInfo(CompoundTag compoundTag) {
    private static final String LEFT_TANK = "LeftTank";
    private static final String RIGHT_TANK = "RightTank";
    private static final String CAPACITY = "Capacity";
    public static final RenderInfo EMPTY = new RenderInfo(new CompoundTag());
    public static final Codec<RenderInfo> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    CompoundTag.CODEC.fieldOf("RenderInfo").forGetter(RenderInfo::compoundTag)
            ).apply(instance, RenderInfo::new)
    );
    public static final StreamCodec<ByteBuf, RenderInfo> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(CompoundTag.CODEC), RenderInfo::compoundTag, RenderInfo::new
    );

    public boolean isEmpty() {
        return this.compoundTag.isEmpty();
    }

    public boolean hasTanks() {
        return this.compoundTag.contains(LEFT_TANK) || this.compoundTag.contains(RIGHT_TANK);
    }

    public FluidVariantWrapper getLeftFluidStack() {
        if(this.compoundTag.contains(LEFT_TANK)) {
            return FluidVariantWrapper.parseOptional(this.compoundTag.getCompoundOrEmpty(LEFT_TANK));
        }
        return FluidVariantWrapper.blank();
    }

    public FluidVariantWrapper getRightFluidStack() {
        if(this.compoundTag.contains(RIGHT_TANK)) {
            return FluidVariantWrapper.parseOptional(this.compoundTag.getCompoundOrEmpty(RIGHT_TANK));
        }
        return FluidVariantWrapper.blank();
    }

    public void updateCapacity(long capacity) {
        if(this.compoundTag.contains(CAPACITY)) {
            this.compoundTag.putLong(CAPACITY, capacity);
        }
    }

    public long getCapacity() {
        if(this.compoundTag.contains(CAPACITY)) {
            return this.compoundTag.getLongOr(CAPACITY, 0);
        }
        return 0;
    }

    public static RenderInfo createCreativeTabInfo() {
        return EMPTY;
    }

    @Override
    public boolean equals(Object other) {
        if(other == this) {
            return true;
        } else {
            return other instanceof RenderInfo(CompoundTag tag) && this.compoundTag.equals(tag);
        }
    }

    @Override
    public int hashCode() {
        return this.compoundTag.hashCode();
    }
}
