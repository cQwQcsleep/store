package com.android.tools.r8.internal;

import java.nio.charset.Charset;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nc0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2183nc0 implements I0 {
    public I0 a;
    public H0 b;
    public J0 c;
    public boolean d;

    public C2183nc0(J0 j0, I0 i0, boolean z) {
        Charset charset = AbstractC1556gB.a;
        j0.getClass();
        this.c = j0;
        this.a = i0;
        this.d = z;
    }

    @Override // com.android.tools.r8.internal.I0
    public final void a() {
        c();
    }

    public final J0 b() {
        if (this.c == null) {
            this.c = this.b.i();
        }
        return this.c;
    }

    public final void c() {
        I0 i0;
        if (this.b != null) {
            this.c = null;
        }
        if (!this.d || (i0 = this.a) == null) {
            return;
        }
        i0.a();
        this.d = false;
    }
}
