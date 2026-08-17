package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class H4 {
    public final String a;
    public byte[] b;
    public H4 c;

    public H4(String str) {
        this.a = str;
    }

    public static void a(Ag0 ag0, int i, int i2, X7 x7) {
        if ((i & 4096) != 0 && ag0.c < 49) {
            x7.d(ag0.a("Synthetic")).c(0);
        }
        if (i2 != 0) {
            x7.d(ag0.a("Signature")).c(2).d(i2);
        }
        if ((i & 131072) != 0) {
            x7.d(ag0.a("Deprecated")).c(0);
        }
    }

    public X7 a() {
        return new X7(this.b);
    }

    public static int a(Ag0 ag0, int i, int i2) {
        int i3;
        if ((i & 4096) == 0 || ag0.c >= 49) {
            i3 = 0;
        } else {
            ag0.a("Synthetic");
            i3 = 6;
        }
        if (i2 != 0) {
            ag0.a("Signature");
            i3 += 8;
        }
        if ((i & 131072) == 0) {
            return i3;
        }
        ag0.a("Deprecated");
        return i3 + 6;
    }

    public H4 a(C1586gd c1586gd, int i, int i2) {
        H4 h4 = new H4(this.a);
        byte[] bArr = new byte[i2];
        h4.b = bArr;
        System.arraycopy(c1586gd.b, i, bArr, 0, i2);
        return h4;
    }
}
