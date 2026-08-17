package com.sun.org.apache.xerces.internal.impl.dtd.models;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CMStateSet {
    int fBitCount;
    int fBits1;
    int fBits2;
    byte[] fByteArray;
    int fByteCount;

    public CMStateSet(int i) {
        this.fBitCount = i;
        if (i < 0) {
            f63.a("ImplementationMessages.VAL_CMSI");
            throw null;
        }
        if (i > 64) {
            int i2 = i / 8;
            this.fByteCount = i2;
            if (i % 8 != 0) {
                this.fByteCount = i2 + 1;
            }
            this.fByteArray = new byte[this.fByteCount];
        }
        zeroBits();
    }

    public boolean equals(Object obj) {
        if (obj instanceof CMStateSet) {
            return isSameSet((CMStateSet) obj);
        }
        return false;
    }

    public final boolean getBit(int i) {
        int i2 = this.fBitCount;
        if (i >= i2) {
            f63.a("ImplementationMessages.VAL_CMSI");
            return false;
        }
        if (i2 >= 65) {
            return (this.fByteArray[i >> 3] & ((byte) (1 << (i % 8)))) != 0;
        }
        int i3 = 1 << (i % 32);
        if (i < 32) {
            return (this.fBits1 & i3) != 0;
        }
        return (this.fBits2 & i3) != 0;
    }

    public int hashCode() {
        if (this.fBitCount < 65) {
            return this.fBits1 + (this.fBits2 * 31);
        }
        int i = 0;
        for (int i2 = this.fByteCount - 1; i2 >= 0; i2--) {
            i = (i * 31) + this.fByteArray[i2];
        }
        return i;
    }

    public final void intersection(CMStateSet cMStateSet) {
        if (this.fBitCount < 65) {
            this.fBits1 &= cMStateSet.fBits1;
            this.fBits2 = cMStateSet.fBits2 & this.fBits2;
        } else {
            for (int i = this.fByteCount - 1; i >= 0; i--) {
                byte[] bArr = this.fByteArray;
                bArr[i] = (byte) (bArr[i] & cMStateSet.fByteArray[i]);
            }
        }
    }

    public final boolean isEmpty() {
        if (this.fBitCount < 65) {
            return this.fBits1 == 0 && this.fBits2 == 0;
        }
        for (int i = this.fByteCount - 1; i >= 0; i--) {
            if (this.fByteArray[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public final boolean isSameSet(CMStateSet cMStateSet) {
        int i = this.fBitCount;
        if (i != cMStateSet.fBitCount) {
            return false;
        }
        if (i < 65) {
            return this.fBits1 == cMStateSet.fBits1 && this.fBits2 == cMStateSet.fBits2;
        }
        for (int i2 = this.fByteCount - 1; i2 >= 0; i2--) {
            if (this.fByteArray[i2] != cMStateSet.fByteArray[i2]) {
                return false;
            }
        }
        return true;
    }

    public final void setBit(int i) {
        int i2 = this.fBitCount;
        if (i >= i2) {
            f63.a("ImplementationMessages.VAL_CMSI");
            return;
        }
        if (i2 < 65) {
            int i3 = 1 << (i % 32);
            if (i < 32) {
                this.fBits1 = (this.fBits1 & (~i3)) | i3;
                return;
            } else {
                this.fBits2 = (this.fBits2 & (~i3)) | i3;
                return;
            }
        }
        byte b = (byte) (1 << (i % 8));
        int i4 = i >> 3;
        byte[] bArr = this.fByteArray;
        byte b2 = (byte) (bArr[i4] & (~b));
        bArr[i4] = b2;
        bArr[i4] = (byte) (b | b2);
    }

    public final void setTo(CMStateSet cMStateSet) {
        int i = this.fBitCount;
        if (i != cMStateSet.fBitCount) {
            f63.a("ImplementationMessages.VAL_CMSI");
            return;
        }
        if (i < 65) {
            this.fBits1 = cMStateSet.fBits1;
            this.fBits2 = cMStateSet.fBits2;
        } else {
            for (int i2 = this.fByteCount - 1; i2 >= 0; i2--) {
                this.fByteArray[i2] = cMStateSet.fByteArray[i2];
            }
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append("{");
            for (int i = 0; i < this.fBitCount; i++) {
                if (getBit(i)) {
                    stringBuffer.append(" " + i);
                }
            }
            stringBuffer.append(" }");
        } catch (RuntimeException unused) {
        }
        return stringBuffer.toString();
    }

    public final void union(CMStateSet cMStateSet) {
        if (this.fBitCount < 65) {
            this.fBits1 |= cMStateSet.fBits1;
            this.fBits2 = cMStateSet.fBits2 | this.fBits2;
        } else {
            for (int i = this.fByteCount - 1; i >= 0; i--) {
                byte[] bArr = this.fByteArray;
                bArr[i] = (byte) (bArr[i] | cMStateSet.fByteArray[i]);
            }
        }
    }

    public final void zeroBits() {
        if (this.fBitCount < 65) {
            this.fBits1 = 0;
            this.fBits2 = 0;
        } else {
            for (int i = this.fByteCount - 1; i >= 0; i--) {
                this.fByteArray[i] = 0;
            }
        }
    }
}
