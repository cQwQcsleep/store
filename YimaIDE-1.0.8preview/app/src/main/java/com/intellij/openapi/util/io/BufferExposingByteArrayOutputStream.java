package com.intellij.openapi.util.io;

import com.intellij.util.io.UnsyncByteArrayOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class BufferExposingByteArrayOutputStream extends UnsyncByteArrayOutputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        if (i != 1) {
            objArr[0] = "allocator";
        } else {
            objArr[0] = "com/intellij/openapi/util/io/BufferExposingByteArrayOutputStream";
        }
        if (i != 1) {
            objArr[1] = "com/intellij/openapi/util/io/BufferExposingByteArrayOutputStream";
        } else {
            objArr[1] = "getInternalBuffer";
        }
        if (i != 1) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    public BufferExposingByteArrayOutputStream() {
    }

    public byte[] getInternalBuffer() {
        byte[] bArr = this.myBuffer;
        if (bArr == null) {
            $$$reportNull$$$0(1);
        }
        return bArr;
    }

    public BufferExposingByteArrayOutputStream(int i) {
        super(i);
    }
}
