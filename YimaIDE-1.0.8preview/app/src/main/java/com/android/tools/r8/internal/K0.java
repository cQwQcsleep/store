package com.android.tools.r8.internal;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public final class K0 extends FilterInputStream {
    public int a;

    public K0(ByteArrayInputStream byteArrayInputStream, int i) {
        super(byteArrayInputStream);
        this.a = i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int available() {
        return Math.min(super.available(), this.a);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.a;
        if (i3 <= 0) {
            return -1;
        }
        int i4 = super.read(bArr, i, Math.min(i2, i3));
        if (i4 >= 0) {
            this.a -= i4;
        }
        return i4;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final long skip(long j) throws IOException {
        long jSkip = super.skip(Math.min(j, this.a));
        if (jSkip >= 0) {
            this.a = (int) (((long) this.a) - jSkip);
        }
        return jSkip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        if (this.a <= 0) {
            return -1;
        }
        int i = super.read();
        if (i >= 0) {
            this.a--;
        }
        return i;
    }
}
