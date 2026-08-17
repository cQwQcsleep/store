package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;

/* JADX INFO: renamed from: com.android.tools.r8.internal.v5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2826v5 extends C2998x5 {
    public final char[] d;

    public C2826v5(C2741u5 c2741u5) {
        super(c2741u5, null);
        this.d = new char[512];
        if (c2741u5.b.length != 16) {
            j2d.a();
            throw null;
        }
        for (int i = 0; i < 256; i++) {
            char[] cArr = this.d;
            char[] cArr2 = c2741u5.b;
            cArr[i] = cArr2[i >>> 4];
            cArr[i | Fcntl.S_IRUSR] = cArr2[i & 15];
        }
    }

    @Override // com.android.tools.r8.internal.C2998x5, com.android.tools.r8.internal.AbstractC3083y5
    public final void a(StringBuilder sb, byte[] bArr, int i) {
        DX.a(0, i, bArr.length);
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = bArr[i2] & 255;
            sb.append(this.d[i3]);
            sb.append(this.d[i3 | Fcntl.S_IRUSR]);
        }
    }

    @Override // com.android.tools.r8.internal.C2998x5
    public final AbstractC3083y5 a(C2741u5 c2741u5) {
        return new C2826v5(c2741u5);
    }
}
