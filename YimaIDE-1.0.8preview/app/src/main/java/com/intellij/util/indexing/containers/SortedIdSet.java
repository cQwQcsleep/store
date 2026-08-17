package com.intellij.util.indexing.containers;

import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class SortedIdSet implements RandomAccessIntContainer, Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int[] mySet;
    private int mySetLength;
    private int mySize;

    public final class Iterator implements IntIdsIterator {
        private int myCursor;

        public Iterator() {
            this.myCursor = SortedIdSet.this.findNext(0);
        }

        @Override // com.intellij.util.indexing.containers.IntIdsIterator
        public IntIdsIterator createCopyInInitialState() {
            return SortedIdSet.this.new Iterator();
        }

        @Override // com.intellij.util.indexing.ValueContainer.IntIterator
        public boolean hasNext() {
            return this.myCursor != -1;
        }

        @Override // com.intellij.util.indexing.ValueContainer.IntIterator
        public int next() {
            int i = SortedIdSet.this.get(this.myCursor);
            this.myCursor = SortedIdSet.this.findNext(this.myCursor + 1);
            return i;
        }
    }

    public SortedIdSet(int[] iArr, int i) {
        this.mySet = iArr;
        this.mySize = i;
        this.mySetLength = i;
    }

    private static int binarySearch(int[] iArr, int i, int i2, int i3) {
        int i4 = i2 - 1;
        while (i <= i4) {
            int i5 = (i + i4) >>> 1;
            int iAbs = Math.abs(iArr[i5]);
            if (iAbs < i3) {
                i = i5 + 1;
            } else {
                if (iAbs <= i3) {
                    return i5;
                }
                i4 = i5 - 1;
            }
        }
        return -(i + 1);
    }

    @Override // com.intellij.util.indexing.containers.RandomAccessIntContainer
    public boolean add(int i) {
        int i2 = this.mySetLength;
        int iBinarySearch = (i2 == 0 || (i2 > 0 && Math.abs(this.mySet[i2 - 1]) < i)) ? (-this.mySetLength) - 1 : binarySearch(this.mySet, 0, this.mySetLength, i);
        if (iBinarySearch >= 0) {
            if (this.mySet[iBinarySearch] > 0) {
                return false;
            }
            iBinarySearch = (-iBinarySearch) - 1;
        }
        int i3 = this.mySetLength;
        int[] iArr = this.mySet;
        if (i3 == iArr.length) {
            this.mySet = Arrays.copyOf(iArr, iArr.length < 1024 ? iArr.length << 1 : iArr.length + (iArr.length / 5));
        }
        int i4 = -iBinarySearch;
        int i5 = i4 - 1;
        boolean z = i5 == this.mySetLength;
        if (!z && Math.abs(this.mySet[i5]) != i) {
            int[] iArr2 = this.mySet;
            System.arraycopy(iArr2, i5, iArr2, i4, this.mySetLength - i5);
            z = true;
        }
        this.mySet[i5] = i;
        this.mySize++;
        if (z) {
            this.mySetLength++;
        }
        return true;
    }

    @Override // com.intellij.util.indexing.containers.RandomAccessIntContainer
    public Object clone() {
        try {
            SortedIdSet sortedIdSet = (SortedIdSet) super.clone();
            sortedIdSet.mySet = (int[]) this.mySet.clone();
            return sortedIdSet;
        } catch (CloneNotSupportedException e) {
            rc6.a(e);
            return null;
        }
    }

    public int findNext(int i) {
        while (i < this.mySetLength) {
            if (this.mySet[i] > 0) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int get(int i) {
        return this.mySet[i];
    }

    @Override // com.intellij.util.indexing.containers.RandomAccessIntContainer
    public IntIdsIterator intIterator() {
        return new Iterator();
    }

    @Override // com.intellij.util.indexing.containers.RandomAccessIntContainer
    public boolean remove(int i) {
        int iBinarySearch = binarySearch(this.mySet, 0, this.mySetLength, i);
        if (iBinarySearch >= 0) {
            int[] iArr = this.mySet;
            if (iArr[iBinarySearch] >= 0) {
                iArr[iBinarySearch] = -i;
                this.mySize--;
                return true;
            }
        }
        return false;
    }

    public SortedIdSet(int i) {
        this.mySet = new int[i];
    }
}
