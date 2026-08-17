package com.intellij.util.io;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.io.ByteArraySequence;
import com.intellij.util.ArrayUtil;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class UnsyncByteArrayOutputStream extends OutputStream {
    private final ByteArrayAllocator myAllocator;
    protected byte[] myBuffer;
    protected int myCount;
    private boolean myIsShared;

    @FunctionalInterface
    public interface ByteArrayAllocator {
        byte[] allocate(int i);
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 3 || i == 4 || i == 5 || i == 6) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 3 || i == 4 || i == 5 || i == 6) ? 2 : 3];
        switch (i) {
            case 1:
                objArr[0] = "allocator";
                break;
            case 2:
                objArr[0] = "b";
                break;
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "com/intellij/util/io/UnsyncByteArrayOutputStream";
                break;
            default:
                objArr[0] = "buffer";
                break;
        }
        if (i == 3) {
            objArr[1] = "toNewByteArray";
        } else if (i == 4 || i == 5) {
            objArr[1] = "toByteArray";
        } else if (i != 6) {
            objArr[1] = "com/intellij/util/io/UnsyncByteArrayOutputStream";
        } else {
            objArr[1] = "asByteArraySequence";
        }
        if (i == 2) {
            objArr[2] = "write";
        } else if (i != 3 && i != 4 && i != 5 && i != 6) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i != 3 && i != 4 && i != 5 && i != 6) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public UnsyncByteArrayOutputStream(byte[] bArr) {
        if (bArr == null) {
            $$$reportNull$$$0(0);
        }
        this.myAllocator = new ByteArrayAllocator() { // from class: y0f
            @Override // com.intellij.util.io.UnsyncByteArrayOutputStream.ByteArrayAllocator
            public final byte[] allocate(int i) {
                return ArrayUtil.newByteArray(i);
            }
        };
        this.myBuffer = bArr;
    }

    private void grow(int i) {
        byte[] bArr = this.myBuffer;
        byte[] bArrAllocate = this.myAllocator.allocate(i > bArr.length ? Math.max(bArr.length << 1, i) : bArr.length);
        byte[] bArr2 = this.myBuffer;
        System.arraycopy(bArr2, 0, bArrAllocate, 0, bArr2.length);
        this.myBuffer = bArrAllocate;
    }

    public void reset() {
        this.myCount = 0;
    }

    public int size() {
        return this.myCount;
    }

    public byte[] toByteArray() {
        byte[] bArr = this.myBuffer;
        if (bArr.length == this.myCount) {
            this.myIsShared = true;
            if (bArr == null) {
                $$$reportNull$$$0(4);
            }
            return bArr;
        }
        byte[] newByteArray = toNewByteArray();
        if (newByteArray == null) {
            $$$reportNull$$$0(5);
        }
        return newByteArray;
    }

    public ByteArraySequence toByteArraySequence() {
        return this.myCount == 0 ? ByteArraySequence.EMPTY : new ByteArraySequence(this.myBuffer, 0, this.myCount);
    }

    public InputStream toInputStream() {
        return new UnsyncByteArrayInputStream(this.myBuffer, 0, this.myCount);
    }

    public byte[] toNewByteArray() {
        return Arrays.copyOf(this.myBuffer, this.myCount);
    }

    public String toString() {
        return new String(this.myBuffer, 0, this.myCount, StandardCharsets.UTF_8);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        int i3;
        if (bArr == null) {
            $$$reportNull$$$0(2);
        }
        if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) > bArr.length || i3 < 0) {
            qc6.a();
            return;
        }
        if (i2 == 0) {
            return;
        }
        int i4 = this.myCount + i2;
        if (i4 > this.myBuffer.length || this.myIsShared) {
            grow(i4);
            this.myIsShared = false;
        }
        System.arraycopy(bArr, i, this.myBuffer, this.myCount, i2);
        this.myCount = i4;
    }

    public UnsyncByteArrayOutputStream(int i) {
        this(ArrayUtil.newByteArray(i));
    }

    public UnsyncByteArrayOutputStream() {
        this(32);
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        int i2 = this.myCount + 1;
        if (i2 > this.myBuffer.length || this.myIsShared) {
            grow(i2);
            this.myIsShared = false;
        }
        this.myBuffer[this.myCount] = (byte) i;
        this.myCount = i2;
    }
}
