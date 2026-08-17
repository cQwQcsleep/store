package com.intellij.openapi.util.io;

import com.intellij.util.io.UnsyncByteArrayInputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class BufferExposingByteArrayInputStream extends UnsyncByteArrayInputStream {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        if (i != 1) {
            objArr[0] = "buf";
        } else {
            objArr[0] = "com/intellij/openapi/util/io/BufferExposingByteArrayInputStream";
        }
        if (i != 1) {
            objArr[1] = "com/intellij/openapi/util/io/BufferExposingByteArrayInputStream";
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BufferExposingByteArrayInputStream(byte[] bArr) {
        super(bArr);
        if (bArr == null) {
            $$$reportNull$$$0(0);
        }
    }
}
