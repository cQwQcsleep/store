package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.w5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2912w5 extends C2998x5 {
    public C2912w5(C2741u5 c2741u5, Character ch) {
        super(c2741u5, ch);
        if (c2741u5.b.length == 64) {
            return;
        }
        j2d.a();
        throw null;
    }

    @Override // com.android.tools.r8.internal.C2998x5, com.android.tools.r8.internal.AbstractC3083y5
    public final void a(StringBuilder sb, byte[] bArr, int i) {
        int i2 = 0;
        DX.a(0, i, bArr.length);
        for (int i3 = i; i3 >= 3; i3 -= 3) {
            int i4 = i2 + 2;
            int i5 = ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2] & 255) << 16);
            i2 += 3;
            int i6 = i5 | (bArr[i4] & 255);
            sb.append(this.b.b[i6 >>> 18]);
            sb.append(this.b.b[(i6 >>> 12) & 63]);
            sb.append(this.b.b[(i6 >>> 6) & 63]);
            sb.append(this.b.b[i6 & 63]);
        }
        if (i2 < i) {
            a(sb, bArr, i2, i - i2);
        }
    }

    @Override // com.android.tools.r8.internal.C2998x5
    public final AbstractC3083y5 a(C2741u5 c2741u5) {
        return new C2912w5(c2741u5, null);
    }
}
