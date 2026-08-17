package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sL, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2591sL {
    public static final /* synthetic */ boolean c = true;
    public final Y7 a;
    public int b = 0;

    public C2591sL(F7 f7) {
        this.a = f7;
    }

    public final void a(int i, int i2) {
        boolean z = c;
        if (!z && i2 <= 0) {
            x1f.a();
            return;
        }
        if (!z && this.b != 0) {
            x1f.a();
            return;
        }
        this.a.a(W7.a(i));
        Y7 y7 = this.a;
        if (i2 <= 255) {
            y7.a(W7.a(i2));
        } else {
            y7.a(0);
            W7.a(i2, this.a);
        }
        this.b = i2;
    }

    public final void b(int i) {
        if (!c && this.b <= 0) {
            x1f.a();
        } else {
            this.b--;
            this.a.a(W7.a(i));
        }
    }

    public final void a(int i) {
        boolean z = c;
        if (!z && !gL.f(i)) {
            x1f.a();
        } else if (z || this.b == 0) {
            this.a.a(W7.a(i));
        } else {
            x1f.a();
        }
    }
}
