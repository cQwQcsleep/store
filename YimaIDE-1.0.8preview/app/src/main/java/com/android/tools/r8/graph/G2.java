package com.android.tools.r8.graph;

import java.io.UTFDataFormatException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class G2 {
    public static final /* synthetic */ boolean c = true;
    public int a = 0;
    public final /* synthetic */ H2 b;

    public G2(H2 h2) {
        this.b = h2;
    }

    public final boolean a() {
        int i = this.a;
        byte[] bArr = this.b.f;
        return i < bArr.length && (bArr[i] & 255) != 0;
    }

    public final char b() throws UTFDataFormatException {
        byte b;
        int i;
        if (!a()) {
            z0e.a();
            return (char) 0;
        }
        byte[] bArr = this.b.f;
        int i2 = this.a;
        int i3 = i2 + 1;
        this.a = i3;
        char c2 = (char) (bArr[i2] & 255);
        if (!c && c2 == 0) {
            x1f.a();
            return (char) 0;
        }
        if (c2 < 128) {
            return c2;
        }
        if ((c2 & 224) == 192) {
            this.a = i2 + 2;
            b = bArr[i3];
            int i4 = b & 255;
            if ((b & 192) != 128) {
                throw H2.e(c2, i4);
            }
            i = (c2 & 31) << 6;
        } else {
            if ((c2 & 240) != 224) {
                throw H2.k(c2);
            }
            byte b2 = bArr[i3];
            int i5 = b2 & 255;
            this.a = i2 + 3;
            b = bArr[i2 + 2];
            int i6 = b & 255;
            if ((b2 & 192) != 128 || (b & 192) != 128) {
                throw H2.a(c2, i5, i6);
            }
            i = ((c2 & 15) << 12) | ((b2 & 63) << 6);
        }
        return (char) ((b & 63) | i);
    }
}
