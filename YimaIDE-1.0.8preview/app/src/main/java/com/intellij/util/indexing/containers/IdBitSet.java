package com.intellij.util.indexing.containers;

import com.intellij.util.ArrayUtil;
import com.intellij.util.indexing.impl.ValueContainerImpl;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class IdBitSet implements RandomAccessIntContainer, Cloneable {
    private int bitIndexBase;
    private long[] bitSlots;
    private int bitsSet;
    private int maxNonZeroSlotIndex;

    public final class Iterator implements IntIdsIterator {
        private int nextSetBit;

        private Iterator() {
            this.nextSetBit = IdBitSet.this.nextSetBit(0);
        }

        @Override // com.intellij.util.indexing.containers.IntIdsIterator
        public IntIdsIterator createCopyInInitialState() {
            return IdBitSet.this.new Iterator();
        }

        @Override // com.intellij.util.indexing.ValueContainer.IntIterator
        public boolean hasNext() {
            return this.nextSetBit != -1;
        }

        @Override // com.intellij.util.indexing.ValueContainer.IntIterator
        public int next() {
            int i = this.nextSetBit;
            this.nextSetBit = IdBitSet.this.nextSetBit(i + 1);
            return i;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[2];
        objArr[0] = "com/intellij/util/indexing/containers/IdBitSet";
        if (i != 1) {
            objArr[1] = "ensureContainerCapacity";
        } else {
            objArr[1] = "calcMinMax";
        }
        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", objArr));
    }

    public IdBitSet(int[] iArr, int i) {
        this.bitIndexBase = -1;
        int i2 = iArr[0];
        int i3 = iArr[1];
        int iRoundDownToSlot = roundDownToSlot(i2);
        this.bitIndexBase = iRoundDownToSlot;
        this.bitSlots = allocateArrayForCapacity(capacityWithReserve(i3 - iRoundDownToSlot) + i);
    }

    private static long[] allocateArrayForCapacity(int i) {
        if (i >= 0) {
            return new long[(i >> 6) + 1];
        }
        ty8.a("capacityInBits(=", i, ") must be >= 0");
        return null;
    }

    public static int[] calcMinMax(int[] iArr, int i) {
        int iMax = Integer.MIN_VALUE;
        int iMin = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < i; i2++) {
            iMax = Math.max(iMax, iArr[i2]);
            iMin = Math.min(iMin, iArr[i2]);
        }
        return new int[]{iMin, iMax};
    }

    private static int capacityWithReserve(int i) {
        int i2 = ((i / 5) * 3) + i;
        if (i2 >= 0) {
            return i2;
        }
        ty8.a("length(=", i, ") is too big -- i.e. id range is too large to keep in bitset");
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int nextSetBit(int i) {
        int i2 = this.bitIndexBase;
        if (i2 < 0) {
            g33.a();
            return 0;
        }
        if (i >= i2) {
            i -= i2;
        }
        int i3 = i >> 6;
        if (i3 > this.maxNonZeroSlotIndex) {
            return -1;
        }
        long j = this.bitSlots[i3] & ((-1) << i);
        while (j == 0) {
            i3++;
            if (i3 > this.maxNonZeroSlotIndex) {
                return -1;
            }
            j = this.bitSlots[i3];
        }
        return (i3 * 64) + Long.numberOfTrailingZeros(j) + this.bitIndexBase;
    }

    private static int roundDownToSlot(int i) {
        return (i >> 6) << 6;
    }

    @Override // com.intellij.util.indexing.containers.RandomAccessIntContainer
    public boolean add(int i) {
        boolean zContains = contains(i);
        if (!zContains) {
            int i2 = this.bitIndexBase;
            if (i2 < 0) {
                this.bitIndexBase = roundDownToSlot(i);
            } else if (i < i2) {
                int iRoundDownToSlot = roundDownToSlot(i);
                int i3 = (this.bitIndexBase - iRoundDownToSlot) >> 6;
                long[] jArr = this.bitSlots;
                long[] jArr2 = new long[jArr.length + i3];
                System.arraycopy(jArr, 0, jArr2, i3, jArr.length);
                this.bitSlots = jArr2;
                this.bitIndexBase = iRoundDownToSlot;
                this.maxNonZeroSlotIndex += i3;
            }
            this.bitsSet++;
            int i4 = i - this.bitIndexBase;
            int i5 = i4 >> 6;
            long[] jArr3 = this.bitSlots;
            if (i5 >= jArr3.length) {
                this.bitSlots = ArrayUtil.realloc(jArr3, Math.max(capacityWithReserve(jArr3.length), i5 + 1));
            }
            long[] jArr4 = this.bitSlots;
            jArr4[i5] = jArr4[i5] | (1 << (i4 & 63));
            this.maxNonZeroSlotIndex = Math.max(this.maxNonZeroSlotIndex, i5);
        }
        return !zContains;
    }

    @Override // com.intellij.util.indexing.containers.RandomAccessIntContainer
    public IdBitSet clone() {
        try {
            IdBitSet idBitSet = (IdBitSet) super.clone();
            long[] jArr = this.bitSlots;
            int length = jArr.length;
            int i = this.maxNonZeroSlotIndex;
            if (length != i + 1) {
                this.bitSlots = Arrays.copyOf(jArr, i + 1);
            }
            idBitSet.bitSlots = (long[]) this.bitSlots.clone();
            return idBitSet;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Must not happen, since class implements Cloneable", e);
        }
    }

    public boolean contains(int i) {
        int i2 = this.bitIndexBase;
        if (i >= i2 && i2 >= 0) {
            int i3 = i - i2;
            int i4 = i3 >> 6;
            long[] jArr = this.bitSlots;
            if (i4 < jArr.length) {
                if (((1 << (i3 & 63)) & jArr[i4]) != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getMax() {
        if (this.bitIndexBase < 0 || this.bitsSet <= 0) {
            g33.a();
            return 0;
        }
        long[] jArr = this.bitSlots;
        int i = this.maxNonZeroSlotIndex;
        return (i * 64) + (63 - Long.numberOfLeadingZeros(jArr[i])) + this.bitIndexBase;
    }

    public int getMin() {
        return nextSetBit(0);
    }

    @Override // com.intellij.util.indexing.containers.RandomAccessIntContainer
    public IntIdsIterator intIterator() {
        return size() == 0 ? ValueContainerImpl.EMPTY_ITERATOR : new Iterator();
    }

    @Override // com.intellij.util.indexing.containers.RandomAccessIntContainer
    public boolean remove(int i) {
        int i2 = this.bitIndexBase;
        boolean z = false;
        if (i >= i2 && i2 >= 0) {
            if (!contains(i)) {
                return false;
            }
            z = true;
            this.bitsSet--;
            int i3 = i - this.bitIndexBase;
            int i4 = i3 >> 6;
            long[] jArr = this.bitSlots;
            jArr[i4] = jArr[i4] & (~(1 << (i3 & 63)));
            if (i4 == this.maxNonZeroSlotIndex) {
                while (true) {
                    int i5 = this.maxNonZeroSlotIndex;
                    if (i5 < 0 || this.bitSlots[i5] != 0) {
                        break;
                    }
                    this.maxNonZeroSlotIndex = i5 - 1;
                }
            }
        }
        return z;
    }

    public int size() {
        return this.bitsSet;
    }

    public IdBitSet(int[] iArr, int i, int i2) {
        this(calcMinMax(iArr, i), i2);
        for (int i3 = 0; i3 < i; i3++) {
            add(iArr[i3]);
        }
    }

    public IdBitSet(int i) {
        this.bitIndexBase = -1;
        this.bitSlots = allocateArrayForCapacity(capacityWithReserve(i));
    }
}
