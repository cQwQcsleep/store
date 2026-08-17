package com.android.tools.r8.dex.code;

import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.US;

/* JADX INFO: renamed from: com.android.tools.r8.dex.code.z2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0139z2 extends S0 {
    public static final /* synthetic */ boolean j = true;

    public C0139z2(int i, int i2, int i3) {
        super(i, i2, i3);
        if (j || i != i3 || i == i2) {
            return;
        }
        x1f.a();
        throw null;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0602Jt c0602Jt) {
        c0602Jt.g(US.h, this.f, this.g, this.h);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String m() {
        return "MulDouble";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int r() {
        return 173;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String u() {
        return "mul-double";
    }

    public C0139z2(int i, A1 a1) {
        super(i, a1);
    }
}
