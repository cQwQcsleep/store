package com.android.tools.r8;

import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class ByteDataView {
    static final /* synthetic */ boolean d = true;
    private byte[] a;
    private final int b;
    private final int c;

    public ByteDataView(byte[] bArr, int i, int i2) {
        boolean z = d;
        if (!z && i < 0) {
            x1f.a();
            throw null;
        }
        if (!z && i2 < 0) {
            x1f.a();
            throw null;
        }
        if (!z && i + i2 > bArr.length) {
            x1f.a();
            throw null;
        }
        this.a = bArr;
        this.b = i;
        this.c = i2;
    }

    public static ByteDataView of(byte[] bArr) {
        return new ByteDataView(bArr, 0, bArr.length);
    }

    public byte[] copyByteData() {
        byte[] bArr = this.a;
        int i = this.b;
        return Arrays.copyOfRange(bArr, i, this.c + i);
    }

    public byte[] getBuffer() {
        if (d || this.a != null) {
            return this.a;
        }
        x1f.a();
        return null;
    }

    public int getLength() {
        if (d || this.a != null) {
            return this.c;
        }
        x1f.a();
        return 0;
    }

    public int getOffset() {
        if (d || this.a != null) {
            return this.b;
        }
        x1f.a();
        return 0;
    }

    public void invalidate() {
        this.a = null;
    }
}
