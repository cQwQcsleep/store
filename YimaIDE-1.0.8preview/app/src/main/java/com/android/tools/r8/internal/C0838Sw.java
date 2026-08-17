package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Sw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0838Sw extends C0864Tw {
    public final /* synthetic */ C0864Tw f;
    public final /* synthetic */ C0864Tw g;

    public C0838Sw() {
        C0812Rw c0812Rw = C0864Tw.e;
        C0786Qw c0786Qw = C0864Tw.d;
        this.g = c0812Rw;
        this.f = c0786Qw;
    }

    @Override // com.android.tools.r8.internal.C0864Tw
    public final boolean a() {
        return this.g.a() || this.f.a();
    }

    @Override // com.android.tools.r8.internal.C0864Tw
    public final boolean b() {
        return this.g.b() || this.f.b();
    }

    @Override // com.android.tools.r8.internal.C0864Tw
    public final boolean c() {
        return this.g.b() || this.f.b();
    }
}
