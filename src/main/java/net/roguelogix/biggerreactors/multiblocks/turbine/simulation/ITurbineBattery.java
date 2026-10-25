package net.roguelogix.biggerreactors.multiblocks.turbine.simulation;

import net.minecraft.nbt.CompoundTag;
import net.roguelogix.biggerreactors.util.SimpleNBTSerializable;

public interface ITurbineBattery extends SimpleNBTSerializable<CompoundTag> {
    long extract(long toExtract);
    
    long stored();
    
    long capacity();
}
