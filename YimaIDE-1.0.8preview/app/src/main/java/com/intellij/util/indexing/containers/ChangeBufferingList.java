package com.intellij.util.indexing.containers;

import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ChangeBufferingList implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int[] changes;
    private boolean hasRemovals;
    private short length;
    private boolean mayHaveDupes;
    private RandomAccessIntContainer randomAccessContainer;

    public static final class ChangesIterator implements IntIdsIterator {
        private final int[] changes;
        private int cursor;
        private final int length;
        private final boolean sorted;

        public ChangesIterator(int[] iArr, int i, boolean z) {
            this.changes = iArr;
            this.length = i;
            this.sorted = z;
        }

        @Override // com.intellij.util.indexing.containers.IntIdsIterator
        public IntIdsIterator createCopyInInitialState() {
            return new ChangesIterator(this.changes, this.length, this.sorted);
        }

        @Override // com.intellij.util.indexing.ValueContainer.IntIterator
        public boolean hasNext() {
            return this.cursor < this.length;
        }

        @Override // com.intellij.util.indexing.ValueContainer.IntIterator
        public int next() {
            int i = this.cursor;
            this.cursor = i + 1;
            return this.changes[i];
        }
    }

    public ChangeBufferingList(int i) {
        if (i > 20000) {
            this.randomAccessContainer = new IdBitSet(i);
        } else {
            this.changes = new int[i];
        }
    }

    private RandomAccessIntContainer getRandomAccessContainer() {
        int[] iArr = this.changes;
        RandomAccessIntContainer idBitSet = this.randomAccessContainer;
        if (iArr == null) {
            return idBitSet;
        }
        boolean z = true;
        if (idBitSet == null) {
            short s = this.length;
            boolean z2 = this.hasRemovals;
            if (s < 20000) {
                if (z2) {
                    idBitSet = new SortedIdSet(Math.max((int) s, 3));
                } else {
                    if (this.mayHaveDupes) {
                        removingDupesAndSort();
                    }
                    idBitSet = new SortedIdSet(this.changes, this.length);
                    z = false;
                }
            } else if (z2) {
                idBitSet = new IdBitSet(IdBitSet.calcMinMax(iArr, s), 0);
            } else {
                idBitSet = new IdBitSet(iArr, s, 0);
                z = false;
            }
        }
        if (z) {
            short s2 = this.length;
            for (int i = 0; i < s2; i++) {
                int i2 = this.changes[i];
                if (i2 > 0) {
                    idBitSet.add(i2);
                } else {
                    idBitSet.remove(-i2);
                }
            }
        }
        this.length = (short) 0;
        this.hasRemovals = false;
        this.mayHaveDupes = false;
        this.randomAccessContainer = idBitSet;
        this.changes = null;
        return idBitSet;
    }

    private void removingDupesAndSort() {
        int[] iArr = this.changes;
        short s = this.length;
        if (s < 250) {
            Arrays.sort(iArr, 0, (int) s);
            int i = s - 1;
            int i2 = 0;
            while (i2 < i) {
                int i3 = iArr[i2];
                i2++;
                if (i3 == iArr[i2]) {
                    int i4 = 0;
                    for (int i5 = 1; i5 < s; i5++) {
                        int i6 = iArr[i5];
                        if (i6 != iArr[i4]) {
                            i4++;
                            iArr[i4] = i6;
                        }
                    }
                    this.length = (short) (i4 + 1);
                    break;
                }
            }
        } else {
            IntIdsIterator transientIterator = SortedFileIdSetIterator.getTransientIterator(new ChangesIterator(iArr, s, false));
            int i7 = 0;
            while (transientIterator.hasNext()) {
                iArr[i7] = transientIterator.next();
                i7++;
            }
            this.length = (short) i7;
        }
        this.mayHaveDupes = false;
        if (s == 0 || this.length != 0) {
            return;
        }
        x01.a("ids list is empty after sorting and duplicates elimination");
    }

    public synchronized Object clone() {
        ChangeBufferingList changeBufferingList;
        try {
            try {
                changeBufferingList = (ChangeBufferingList) super.clone();
                int[] iArr = this.changes;
                if (iArr != null) {
                    changeBufferingList.changes = (int[]) iArr.clone();
                }
                RandomAccessIntContainer randomAccessIntContainer = this.randomAccessContainer;
                if (randomAccessIntContainer != null) {
                    changeBufferingList.randomAccessContainer = (RandomAccessIntContainer) randomAccessIntContainer.clone();
                }
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        } catch (Throwable th) {
            throw th;
        }
        return changeBufferingList;
    }

    public synchronized IntIdsIterator intIterator() {
        try {
            if (this.randomAccessContainer != null || this.hasRemovals || this.changes == null) {
                return getRandomAccessContainer().intIterator();
            }
            if (this.mayHaveDupes) {
                removingDupesAndSort();
            }
            return new ChangesIterator(this.changes, this.length, true);
        } catch (Throwable th) {
            throw th;
        }
    }

    public ChangeBufferingList() {
        this(3);
    }
}
