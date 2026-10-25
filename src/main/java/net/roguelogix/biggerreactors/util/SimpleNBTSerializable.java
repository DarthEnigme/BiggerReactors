package net.roguelogix.biggerreactors.util;

import net.minecraft.nbt.Tag;

/**
 * Pre-1.20.5 shape of INBTSerializable, for internal state that never needs registry access
 */
public interface SimpleNBTSerializable<T extends Tag> {
    T serializeNBT();
    
    void deserializeNBT(T nbt);
}
