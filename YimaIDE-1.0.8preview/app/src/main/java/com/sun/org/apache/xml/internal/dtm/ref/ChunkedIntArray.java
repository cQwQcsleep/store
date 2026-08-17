package com.sun.org.apache.xml.internal.dtm.ref;

import com.sun.org.apache.xml.internal.res.XMLMessages;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class ChunkedIntArray {
    static final int chunkalloc = 1024;
    static final int lowbits = 10;
    static final int lowmask = 1023;
    final int[] fastArray;
    final int slotsize = 4;
    ChunksVector chunks = new ChunksVector();
    int lastUsed = 0;

    public class ChunksVector {
        final int BLOCKSIZE = 64;
        int[][] m_map = new int[64][];
        int m_mapSize = 64;
        int pos = 0;

        public ChunksVector() {
        }

        public void addElement(int[] iArr) {
            int i;
            int i2 = this.pos;
            int i3 = this.m_mapSize;
            if (i2 >= i3) {
                while (true) {
                    int i4 = this.pos;
                    i = this.m_mapSize;
                    if (i4 < i) {
                        break;
                    } else {
                        this.m_mapSize = i + 64;
                    }
                }
                int[][] iArr2 = new int[i][];
                System.arraycopy(this.m_map, 0, iArr2, 0, i3);
                this.m_map = iArr2;
            }
            int[][] iArr3 = this.m_map;
            int i5 = this.pos;
            iArr3[i5] = iArr;
            this.pos = i5 + 1;
        }

        public final int[] elementAt(int i) {
            return this.m_map[i];
        }

        public final int size() {
            return this.pos;
        }
    }

    public ChunkedIntArray(int i) {
        int[] iArr = new int[1024];
        this.fastArray = iArr;
        if (4 < i) {
            throw new ArrayIndexOutOfBoundsException(XMLMessages.createXMLMessage("ER_CHUNKEDINTARRAY_NOT_SUPPORTED", new Object[]{Integer.toString(i)}));
        }
        if (4 > i) {
            System.out.println("*****WARNING: ChunkedIntArray(" + i + ") wasting " + (4 - i) + " words per slot");
        }
        this.chunks.addElement(iArr);
    }

    public int appendSlot(int i, int i2, int i3, int i4) {
        int i5 = (this.lastUsed + 1) * 4;
        int i6 = i5 >> 10;
        int i7 = i5 & 1023;
        if (i6 > this.chunks.size() - 1) {
            this.chunks.addElement(new int[1024]);
        }
        int[] iArrElementAt = this.chunks.elementAt(i6);
        iArrElementAt[i7] = i;
        iArrElementAt[i7 + 1] = i2;
        iArrElementAt[i7 + 2] = i3;
        iArrElementAt[i7 + 3] = i4;
        int i8 = this.lastUsed + 1;
        this.lastUsed = i8;
        return i8;
    }

    public void discardLast() {
        this.lastUsed--;
    }

    public int readEntry(int i, int i2) throws ArrayIndexOutOfBoundsException {
        if (i2 >= 4) {
            throw new ArrayIndexOutOfBoundsException(XMLMessages.createXMLMessage("ER_OFFSET_BIGGER_THAN_SLOT", null));
        }
        int i3 = i * 4;
        return this.chunks.elementAt(i3 >> 10)[(i3 & 1023) + i2];
    }

    public void readSlot(int i, int[] iArr) {
        int i2 = i * 4;
        int i3 = i2 >> 10;
        int i4 = i2 & 1023;
        if (i3 > this.chunks.size() - 1) {
            this.chunks.addElement(new int[1024]);
        }
        System.arraycopy(this.chunks.elementAt(i3), i4, iArr, 0, 4);
    }

    public int slotsUsed() {
        return this.lastUsed;
    }

    public int specialFind(int i, int i2) {
        while (i > 0) {
            int i3 = i * 4;
            i = this.chunks.elementAt(i3 >> 10)[(i3 & 1023) + 1];
            if (i == i2) {
                break;
            }
        }
        if (i <= 0) {
            return i2;
        }
        return -1;
    }

    public void writeEntry(int i, int i2, int i3) throws ArrayIndexOutOfBoundsException {
        if (i2 >= 4) {
            throw new ArrayIndexOutOfBoundsException(XMLMessages.createXMLMessage("ER_OFFSET_BIGGER_THAN_SLOT", null));
        }
        int i4 = i * 4;
        this.chunks.elementAt(i4 >> 10)[(i4 & 1023) + i2] = i3;
    }

    public void writeSlot(int i, int i2, int i3, int i4, int i5) {
        int i6 = i * 4;
        int i7 = i6 >> 10;
        int i8 = i6 & 1023;
        if (i7 > this.chunks.size() - 1) {
            this.chunks.addElement(new int[1024]);
        }
        int[] iArrElementAt = this.chunks.elementAt(i7);
        iArrElementAt[i8] = i2;
        iArrElementAt[i8 + 1] = i3;
        iArrElementAt[i8 + 2] = i4;
        iArrElementAt[i8 + 3] = i5;
    }
}
