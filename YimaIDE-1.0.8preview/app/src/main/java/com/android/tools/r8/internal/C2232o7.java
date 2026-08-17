package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.o7, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2232o7 implements InterfaceC0560Id {
    public static final C2232o7 f = new C2232o7(true, true);
    public static final C2232o7 g = new C2232o7(false, true);
    public static final C2232o7 h = new C2232o7(false, false);
    public static final /* synthetic */ boolean i = true;
    public final boolean d;
    public final boolean e;

    public C2232o7(boolean z, boolean z2) {
        if (!i && z && !z2) {
            x1f.a();
            throw null;
        }
        this.d = z;
        this.e = z2;
    }

    public static C2232o7 a(boolean z, boolean z2) {
        if (!z) {
            return z2 ? g : h;
        }
        if (i || z2) {
            return f;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0560Id
    public final AbstractC2173nV a() {
        if (i || !this.d || this.e) {
            return AbstractC2173nV.a(this.e);
        }
        x1f.a();
        return null;
    }
}
