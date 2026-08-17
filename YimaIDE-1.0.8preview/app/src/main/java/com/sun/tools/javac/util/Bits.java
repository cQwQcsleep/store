package com.sun.tools.javac.util;

import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Bits {
    private static final int[] unassignedBits = new int[0];
    private static final int wordlen = 32;
    private static final int wordmask = 31;
    private static final int wordshift = 5;
    public int[] bits;
    protected BitsState currentState;

    public enum BitsState {
        UNKNOWN,
        UNINIT,
        NORMAL;

        public static BitsState getState(int[] iArr, boolean z) {
            if (z) {
                return UNKNOWN;
            }
            return iArr != Bits.unassignedBits ? NORMAL : UNINIT;
        }
    }

    public Bits(int[] iArr, BitsState bitsState) {
        this.bits = iArr;
        this.currentState = bitsState;
        int iOrdinal = bitsState.ordinal();
        if (iOrdinal == 0) {
            this.bits = null;
        } else {
            if (iOrdinal != 2) {
                return;
            }
            Assert.check(iArr != unassignedBits);
        }
    }

    private static int trailingZeroBits(int i) {
        int i2;
        Assert.check(true);
        if (i == 0) {
            return 32;
        }
        if ((65535 & i) == 0) {
            i >>>= 16;
            i2 = 17;
        } else {
            i2 = 1;
        }
        if ((i & 255) == 0) {
            i2 += 8;
            i >>>= 8;
        }
        if ((i & 15) == 0) {
            i2 += 4;
            i >>>= 4;
        }
        if ((i & 3) == 0) {
            i2 += 2;
            i >>>= 2;
        }
        return i2 - (i & 1);
    }

    public Bits andSet(Bits bits) {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        internalAndSet(bits);
        this.currentState = BitsState.NORMAL;
        return this;
    }

    public Bits assign(Bits bits) {
        this.bits = bits.dup().bits;
        this.currentState = BitsState.NORMAL;
        return this;
    }

    public void clear() {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        int i = 0;
        while (true) {
            int[] iArr = this.bits;
            if (i >= iArr.length) {
                this.currentState = BitsState.NORMAL;
                return;
            } else {
                iArr[i] = 0;
                i++;
            }
        }
    }

    public Bits diffSet(Bits bits) {
        int i = 0;
        Assert.check(this.currentState != BitsState.UNKNOWN);
        while (true) {
            int[] iArr = this.bits;
            if (i >= iArr.length) {
                this.currentState = BitsState.NORMAL;
                return this;
            }
            int[] iArr2 = bits.bits;
            if (i < iArr2.length) {
                iArr[i] = (~iArr2[i]) & iArr[i];
            }
            i++;
        }
    }

    public Bits dup() {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        Bits bits = new Bits();
        bits.bits = dupBits();
        this.currentState = BitsState.NORMAL;
        return bits;
    }

    public int[] dupBits() {
        BitsState bitsState = this.currentState;
        BitsState bitsState2 = BitsState.NORMAL;
        int[] iArr = this.bits;
        if (bitsState != bitsState2) {
            return iArr;
        }
        int[] iArr2 = new int[iArr.length];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        return iArr2;
    }

    public void excl(int i) {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        Assert.check(i >= 0);
        int i2 = i >>> 5;
        sizeTo(i2 + 1);
        int[] iArr = this.bits;
        iArr[i2] = (~(1 << (i & 31))) & iArr[i2];
        this.currentState = BitsState.NORMAL;
    }

    public void excludeFrom(int i) {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        Bits bits = new Bits();
        bits.sizeTo(this.bits.length);
        bits.inclRange(0, i);
        internalAndSet(bits);
        this.currentState = BitsState.NORMAL;
    }

    public void incl(int i) {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        Assert.check(i >= 0);
        int i2 = i >>> 5;
        sizeTo(i2 + 1);
        int[] iArr = this.bits;
        iArr[i2] = (1 << (i & 31)) | iArr[i2];
        this.currentState = BitsState.NORMAL;
    }

    public void inclRange(int i, int i2) {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        sizeTo((i2 >>> 5) + 1);
        while (i < i2) {
            int[] iArr = this.bits;
            int i3 = i >>> 5;
            iArr[i3] = iArr[i3] | (1 << (i & 31));
            i++;
        }
        this.currentState = BitsState.NORMAL;
    }

    public void internalAndSet(Bits bits) {
        int i = 0;
        Assert.check(this.currentState != BitsState.UNKNOWN);
        sizeTo(bits.bits.length);
        while (true) {
            int[] iArr = bits.bits;
            if (i >= iArr.length) {
                return;
            }
            int[] iArr2 = this.bits;
            iArr2[i] = iArr[i] & iArr2[i];
            i++;
        }
    }

    public void internalReset() {
        this.bits = null;
        this.currentState = BitsState.UNKNOWN;
    }

    public boolean isMember(int i) {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        if (i >= 0) {
            int[] iArr = this.bits;
            if (i < (iArr.length << 5) && (iArr[i >>> 5] & (1 << (i & 31))) != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isReset() {
        return this.currentState == BitsState.UNKNOWN;
    }

    public int nextBit(int i) {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        int i2 = i >>> 5;
        int[] iArr = this.bits;
        if (i2 >= iArr.length) {
            return -1;
        }
        int i3 = (~((1 << (i & 31)) - 1)) & iArr[i2];
        while (i3 == 0) {
            i2++;
            int[] iArr2 = this.bits;
            if (i2 >= iArr2.length) {
                return -1;
            }
            i3 = iArr2[i2];
        }
        return (i2 << 5) + trailingZeroBits(i3);
    }

    public Bits orSet(Bits bits) {
        int i = 0;
        Assert.check(this.currentState != BitsState.UNKNOWN);
        sizeTo(bits.bits.length);
        while (true) {
            int[] iArr = bits.bits;
            if (i >= iArr.length) {
                this.currentState = BitsState.NORMAL;
                return this;
            }
            int[] iArr2 = this.bits;
            iArr2[i] = iArr[i] | iArr2[i];
            i++;
        }
    }

    public void reset() {
        internalReset();
    }

    public void sizeTo(int i) {
        int[] iArr = this.bits;
        if (iArr.length < i) {
            this.bits = Arrays.copyOf(iArr, i);
        }
    }

    public String toString() {
        int[] iArr = this.bits;
        if (iArr == null || iArr.length <= 0) {
            return "[]";
        }
        char[] cArr = new char[iArr.length * 32];
        for (int i = 0; i < this.bits.length * 32; i++) {
            cArr[i] = isMember(i) ? '1' : '0';
        }
        return new String(cArr);
    }

    public Bits xorSet(Bits bits) {
        int i = 0;
        Assert.check(this.currentState != BitsState.UNKNOWN);
        sizeTo(bits.bits.length);
        while (true) {
            int[] iArr = bits.bits;
            if (i >= iArr.length) {
                this.currentState = BitsState.NORMAL;
                return this;
            }
            int[] iArr2 = this.bits;
            iArr2[i] = iArr[i] ^ iArr2[i];
            i++;
        }
    }

    public Bits(Bits bits) {
        this(bits.dup().bits, BitsState.getState(bits.bits, false));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Bits(boolean z) {
        int[] iArr = unassignedBits;
        this(iArr, BitsState.getState(iArr, z));
    }

    public Bits() {
        this(false);
    }
}
