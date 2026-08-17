package com.intellij.openapi.util.io;

import com.intellij.util.ArrayUtil;
import com.intellij.util.io.IOUtil;
import com.intellij.util.io.UnsyncByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ByteArraySequence {
    public static final ByteArraySequence EMPTY = new ByteArraySequence(ArrayUtil.EMPTY_BYTE_ARRAY);
    private final byte[] myBytes;
    private final int myLen;
    private final int myOffset;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 3) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 3) ? 2 : 3];
        if (i == 2 || i == 3) {
            objArr[0] = "com/intellij/openapi/util/io/ByteArraySequence";
        } else {
            objArr[0] = "bytes";
        }
        if (i == 2) {
            objArr[1] = "getInternalBuffer";
        } else if (i != 3) {
            objArr[1] = "com/intellij/openapi/util/io/ByteArraySequence";
        } else {
            objArr[1] = "toBytes";
        }
        if (i != 2 && i != 3) {
            if (i != 4) {
                objArr[2] = "<init>";
            } else {
                objArr[2] = "create";
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 3) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public ByteArraySequence(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            $$$reportNull$$$0(1);
        }
        this.myBytes = bArr;
        this.myOffset = i;
        this.myLen = i2;
        if (i < 0 || i > bArr.length || i + i2 > bArr.length || i2 < 0) {
            fq7.a("Offset is out of range: ", i, "; bytes.length: ", bArr.length, "; len: ", i2);
            throw null;
        }
    }

    public static ByteArraySequence create(byte[] bArr) {
        if (bArr == null) {
            $$$reportNull$$$0(4);
        }
        return bArr.length == 0 ? EMPTY : new ByteArraySequence(bArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ByteArraySequence.class != obj.getClass()) {
            return false;
        }
        ByteArraySequence byteArraySequence = (ByteArraySequence) obj;
        int i = this.myLen;
        if (i != byteArraySequence.myLen) {
            return false;
        }
        byte[] bArr = this.myBytes;
        byte[] bArr2 = byteArraySequence.myBytes;
        int i2 = this.myOffset;
        int i3 = byteArraySequence.myOffset;
        int i4 = 0;
        while (i4 < i) {
            if (bArr[i2] != bArr2[i3]) {
                return false;
            }
            i4++;
            i2++;
            i3++;
        }
        return true;
    }

    public byte[] getInternalBuffer() {
        byte[] bArr = this.myBytes;
        if (bArr == null) {
            $$$reportNull$$$0(2);
        }
        return bArr;
    }

    public int getLength() {
        return this.myLen;
    }

    public int getOffset() {
        return this.myOffset;
    }

    public int hashCode() {
        int i = this.myLen;
        byte[] bArr = this.myBytes;
        int i2 = this.myOffset;
        int i3 = 0;
        int i4 = 1;
        while (i3 < i) {
            i4 = (i4 * 31) + bArr[i2];
            i3++;
            i2++;
        }
        return i4;
    }

    public int length() {
        return getLength();
    }

    public byte[] toBytes() {
        byte[] bArr = this.myBytes;
        int i = this.myOffset;
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i, length() + i);
        if (bArrCopyOfRange == null) {
            $$$reportNull$$$0(3);
        }
        return bArrCopyOfRange;
    }

    public DataInputStream toInputStream() {
        return new DataInputStream(new UnsyncByteArrayInputStream(this.myBytes, this.myOffset, length()));
    }

    public String toString() {
        return IOUtil.toHexString(toBytes());
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ByteArraySequence(byte[] bArr) {
        this(bArr, 0, bArr.length);
        if (bArr == null) {
            $$$reportNull$$$0(0);
        }
    }
}
