package com.intellij.util.io.pagecache.impl;

import com.intellij.util.MathUtil;
import java.util.function.IntSupplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ConfinedIntValue implements IntSupplier {
    private final int maxValue;
    private final int minValue;
    private int value;

    public ConfinedIntValue(int i, int i2, int i3) {
        this.value = i;
        this.minValue = i2;
        this.maxValue = i3;
    }

    public void dec() {
        update(this.value - 1);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ConfinedIntValue.class != obj.getClass()) {
            return false;
        }
        ConfinedIntValue confinedIntValue = (ConfinedIntValue) obj;
        return this.value == confinedIntValue.value && this.minValue == confinedIntValue.minValue && this.maxValue == confinedIntValue.maxValue;
    }

    @Override // java.util.function.IntSupplier
    public int getAsInt() {
        return value();
    }

    public int hashCode() {
        return (((this.value * 31) + this.minValue) * 31) + this.maxValue;
    }

    public String toString() {
        return "ConfinedIntValue[=" + this.value + "][" + this.minValue + ".." + this.maxValue + ']';
    }

    public void update(int i) {
        this.value = MathUtil.clamp(i, this.minValue, this.maxValue);
    }

    public int value() {
        return this.value;
    }
}
