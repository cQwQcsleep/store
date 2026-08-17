package com.intellij.util.indexing.containers;

import com.intellij.openapi.util.ThreadLocalCachedIntArray;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class SortedFileIdSetIterator implements IntIdsIterator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final ThreadLocalCachedIntArray ourSpareBuffer = new ThreadLocalCachedIntArray();
    private final int[] myBits;
    private final int myBitsLength;
    private final int myOffset;
    private int myPosition;
    private final int mySize;

    private SortedFileIdSetIterator(int[] iArr, int i, int i2, int i3) {
        this.myBits = iArr;
        this.myBitsLength = i;
        this.myOffset = i2;
        this.myPosition = nextSetBit(0, iArr, i);
        this.mySize = i3;
    }

    public static IntIdsIterator getTransientIterator(IntIdsIterator intIdsIterator) {
        IntIdsIterator intIdsIteratorCreateCopyInInitialState = intIdsIterator.createCopyInInitialState();
        int i = 0;
        int iMin = Integer.MAX_VALUE;
        int iMax = 0;
        while (intIdsIterator.hasNext()) {
            int next = intIdsIterator.next();
            iMax = Math.max(iMax, next);
            iMin = Math.min(iMin, next);
        }
        int i2 = (iMin >> 5) << 5;
        int i3 = ((iMax - i2) >> 5) + 1;
        int[] buffer = ourSpareBuffer.getBuffer(i3);
        for (int i4 = 0; i4 < i3; i4++) {
            buffer[i4] = 0;
        }
        while (intIdsIteratorCreateCopyInInitialState.hasNext()) {
            int next2 = intIdsIteratorCreateCopyInInitialState.next() - i2;
            int i5 = 1 << next2;
            int i6 = next2 >> 5;
            int i7 = buffer[i6];
            if ((i7 & i5) == 0) {
                buffer[i6] = i5 | i7;
                i++;
            }
        }
        return new SortedFileIdSetIterator(buffer, i3, i2, i);
    }

    private static int nextSetBit(int i, int[] iArr, int i2) {
        int i3 = i >> 5;
        if (i3 >= i2) {
            return -1;
        }
        int i4 = ((-1) << i) & iArr[i3];
        while (i4 == 0) {
            i3++;
            if (i3 == i2) {
                return -1;
            }
            i4 = iArr[i3];
        }
        return (i3 << 5) + Long.numberOfTrailingZeros(i4);
    }

    @Override // com.intellij.util.indexing.containers.IntIdsIterator
    public IntIdsIterator createCopyInInitialState() {
        return new SortedFileIdSetIterator(this.myBits, this.myBitsLength, this.myOffset, this.mySize);
    }

    @Override // com.intellij.util.indexing.ValueContainer.IntIterator
    public boolean hasNext() {
        return this.myPosition != -1;
    }

    @Override // com.intellij.util.indexing.ValueContainer.IntIterator
    public int next() {
        int i = this.myPosition;
        int i2 = this.myOffset + i;
        this.myPosition = nextSetBit(i + 1, this.myBits, this.myBitsLength);
        return i2;
    }
}
