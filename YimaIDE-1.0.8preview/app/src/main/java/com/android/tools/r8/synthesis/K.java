package com.android.tools.r8.synthesis;

import com.android.tools.r8.internal.C1586gd;
import com.android.tools.r8.internal.H4;
import com.android.tools.r8.internal.X7;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class K extends H4 {
    public static final /* synthetic */ boolean g = true;
    public final S.b d;
    public final String e;
    public final S f;

    public K(S.b bVar, String str, S s) {
        super("com.android.tools.r8.SynthesizedClassV2");
        this.d = bVar;
        this.e = str;
        this.f = s;
    }

    @Override // com.android.tools.r8.internal.H4
    public final H4 a(C1586gd c1586gd, int i, int i2) {
        short sC = c1586gd.c(i);
        int iC = c1586gd.c(i + 2);
        int i3 = i + 4;
        byte[] bArr = new byte[iC];
        int i4 = 0;
        while (i4 < iC) {
            bArr[i4] = (byte) (c1586gd.b[i3] & 255);
            i4++;
            i3++;
        }
        S.b bVar = null;
        if (!g && sC < 0) {
            x1f.a();
            return null;
        }
        S s = this.f;
        if (sC <= 0) {
            s.getClass();
        } else if (sC <= s.R.size()) {
            bVar = (S.b) s.R.get(sC - 1);
        }
        return new K(bVar, new String(bArr, StandardCharsets.UTF_8), this.f);
    }

    @Override // com.android.tools.r8.internal.H4
    public final X7 a() {
        int i;
        if (!g && ((i = this.d.b) < 0 || i > 32767)) {
            x1f.a();
            return null;
        }
        X7 x7 = new X7();
        x7.d(this.d.b);
        byte[] bytes = this.e.getBytes(StandardCharsets.UTF_8);
        x7.d(bytes.length);
        x7.a(bytes, 0, bytes.length);
        return x7;
    }
}
