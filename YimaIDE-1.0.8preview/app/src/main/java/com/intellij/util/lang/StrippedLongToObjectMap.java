package com.intellij.util.lang;

import java.util.function.IntFunction;
import java.util.function.LongFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class StrippedLongToObjectMap<V> implements LongFunction<V> {
    private boolean containsNullKey;
    private long[] keys;
    private int mask;
    private int maxFill;
    private int size;
    private int tableSize;
    private final IntFunction<V[]> valueArrayFactory;
    private V[] values;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/intellij/util/lang/StrippedLongToObjectMap", "replaceByIndex"));
    }

    public StrippedLongToObjectMap(StrippedLongToObjectMap<V> strippedLongToObjectMap) {
        this.valueArrayFactory = strippedLongToObjectMap.valueArrayFactory;
        this.tableSize = strippedLongToObjectMap.tableSize;
        this.mask = strippedLongToObjectMap.mask;
        this.maxFill = strippedLongToObjectMap.maxFill;
        this.size = strippedLongToObjectMap.size;
        this.keys = (long[]) strippedLongToObjectMap.keys.clone();
        this.values = (V[]) ((Object[]) strippedLongToObjectMap.values.clone());
        this.containsNullKey = strippedLongToObjectMap.containsNullKey;
    }

    private int realSize() {
        boolean z = this.containsNullKey;
        int i = this.size;
        return z ? i - 1 : i;
    }

    private void rehash(int i) {
        long j;
        long[] jArr = this.keys;
        V[] vArr = this.values;
        int i2 = i - 1;
        int i3 = i + 1;
        long[] jArr2 = new long[i3];
        V[] vArrApply = this.valueArrayFactory.apply(i3);
        int i4 = this.tableSize;
        int iRealSize = realSize();
        while (true) {
            int i5 = iRealSize - 1;
            if (iRealSize == 0) {
                vArrApply[i] = vArr[this.tableSize];
                this.tableSize = i;
                this.mask = i2;
                this.maxFill = Hash.maxFill(i, 0.5f);
                this.keys = jArr2;
                this.values = vArrApply;
                return;
            }
            do {
                i4--;
                j = jArr[i4];
            } while (j == 0);
            int i6 = ((int) j) & i2;
            if (jArr2[i6] != 0) {
                do {
                    i6 = (i6 + 1) & i2;
                } while (jArr2[i6] != 0);
            }
            jArr2[i6] = jArr[i4];
            vArrApply[i6] = vArr[i4];
            iRealSize = i5;
        }
    }

    public void addByIndex(int i, long j, V v) {
        replaceByIndex((-i) - 1, j, v);
        int i2 = this.size;
        this.size = i2 + 1;
        if (i2 >= this.maxFill) {
            rehash(Hash.arraySize(i2 + 2, 0.5f));
        }
    }

    @Override // java.util.function.LongFunction
    public V apply(long j) {
        long j2;
        if (j == 0) {
            if (this.containsNullKey) {
                return this.values[this.tableSize];
            }
            return null;
        }
        long[] jArr = this.keys;
        int i = ((int) j) & this.mask;
        long j3 = jArr[i];
        if (j3 == 0) {
            return null;
        }
        if (j == j3) {
            return this.values[i];
        }
        do {
            i = (i + 1) & this.mask;
            j2 = jArr[i];
            if (j2 == 0) {
                return null;
            }
        } while (j != j2);
        return this.values[i];
    }

    public V getByIndex(int i) {
        return this.values[i];
    }

    public int index(long j) {
        long j2;
        if (j == 0) {
            boolean z = this.containsNullKey;
            int i = this.tableSize;
            return z ? i : -(i + 1);
        }
        long[] jArr = this.keys;
        int i2 = ((int) j) & this.mask;
        long j3 = jArr[i2];
        if (j3 == 0) {
            return -(i2 + 1);
        }
        if (j == j3) {
            return i2;
        }
        do {
            i2 = (i2 + 1) & this.mask;
            j2 = jArr[i2];
            if (j2 == 0) {
                return -(i2 + 1);
            }
        } while (j != j2);
        return i2;
    }

    public void replaceByIndex(int i, long j, V v) {
        if (v == null) {
            $$$reportNull$$$0(0);
        }
        if (i == this.tableSize) {
            this.containsNullKey = true;
        }
        this.keys[i] = j;
        this.values[i] = v;
    }

    public StrippedLongToObjectMap(IntFunction<V[]> intFunction, int i) {
        this.valueArrayFactory = intFunction;
        int iArraySize = Hash.arraySize(i, 0.5f);
        this.tableSize = iArraySize;
        this.mask = iArraySize - 1;
        this.maxFill = Hash.maxFill(iArraySize, 0.5f);
        int i2 = this.tableSize;
        this.keys = new long[i2 + 1];
        this.values = intFunction.apply(i2 + 1);
    }
}
