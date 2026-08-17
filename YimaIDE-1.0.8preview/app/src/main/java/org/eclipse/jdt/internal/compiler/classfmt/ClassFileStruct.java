package org.eclipse.jdt.internal.compiler.classfmt;

import org.eclipse.jdt.internal.compiler.codegen.Opcodes;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public abstract class ClassFileStruct {
    int[] constantPoolOffsets;
    byte[] reference;
    int structOffset;

    public ClassFileStruct(byte[] bArr, int[] iArr, int i) {
        this.reference = bArr;
        this.constantPoolOffsets = iArr;
        this.structOffset = i;
    }

    public double doubleAt(int i) {
        return Double.longBitsToDouble(i8At(i));
    }

    public float floatAt(int i) {
        return Float.intBitsToFloat(i4At(i));
    }

    public int i4At(int i) {
        int i2 = i + this.structOffset;
        byte[] bArr = this.reference;
        return ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2] & 255) << 24) | (((bArr[i2 + 2] & 255) << 8) + (bArr[i2 + 3] & 255));
    }

    public long i8At(int i) {
        int i2 = i + this.structOffset;
        byte[] bArr = this.reference;
        long j = (((long) (bArr[i2] & 255)) << 56) | (((long) (bArr[i2 + 1] & 255)) << 48) | (((long) (bArr[i2 + 2] & 255)) << 40) | (((long) (bArr[i2 + 3] & 255)) << 32) | (((long) (bArr[i2 + 4] & 255)) << 24) | (((long) (bArr[i2 + 5] & 255)) << 16);
        return ((long) (bArr[i2 + 7] & 255)) | j | (((long) (bArr[i2 + 6] & 255)) << 8);
    }

    public void reset() {
        this.reference = null;
        this.constantPoolOffsets = null;
    }

    public int u1At(int i) {
        return this.reference[i + this.structOffset] & 255;
    }

    public int u2At(int i) {
        int i2 = i + this.structOffset;
        byte[] bArr = this.reference;
        int i3 = i2 + 1;
        return (bArr[i3] & 255) | ((bArr[i2] & 255) << 8);
    }

    public long u4At(int i) {
        int i2 = i + this.structOffset;
        byte[] bArr = this.reference;
        long j = ((((long) bArr[i2]) & 255) << 24) | ((long) ((bArr[i2 + 1] & 255) << 16));
        return ((long) (bArr[i2 + 3] & 255)) | j | ((long) ((bArr[i2 + 2] & 255) << 8));
    }

    public char[] utf8At(int i, int i2) {
        char[] cArr = new char[i2];
        int i3 = this.structOffset + i;
        int i4 = 0;
        int i5 = i2;
        while (i5 != 0) {
            byte[] bArr = this.reference;
            int i6 = i3 + 1;
            byte b = bArr[i3];
            int i7 = b & 255;
            int i8 = i5 - 1;
            if ((b & Opcodes.OPC_ior) == 0) {
                i3 = i6;
                i5 = i8;
            } else if ((b & Opcodes.OPC_lload_2) != 0) {
                i5 -= 3;
                int i9 = i3 + 2;
                i3 += 3;
                i7 = ((bArr[i6] & Opcodes.OPC_lstore_0) << 6) | ((b & Opcodes.OPC_dconst_1) << 12) | (bArr[i9] & Opcodes.OPC_lstore_0);
            } else {
                i5 -= 2;
                i3 += 2;
                i7 = ((b & Opcodes.OPC_lload_1) << 6) | (bArr[i6] & Opcodes.OPC_lstore_0);
            }
            cArr[i4] = (char) i7;
            i4++;
        }
        if (i4 == i2) {
            return cArr;
        }
        char[] cArr2 = new char[i4];
        System.arraycopy(cArr, 0, cArr2, 0, i4);
        return cArr2;
    }
}
