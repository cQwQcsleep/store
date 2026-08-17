package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.af0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1082af0 extends AbstractC1932kf0 implements InterfaceC1168bf0, InterfaceC1762if0 {
    public static final /* synthetic */ boolean i = true;
    public final VB e;
    public Ze0 f;
    public String g;
    public C2543rl0 h;

    public C1082af0(VB vb) {
        this.e = vb;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1762if0
    public final AbstractC0890Uw a() {
        return this.e;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1168bf0
    public final boolean b() {
        return this.g != null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1168bf0
    public final boolean c() {
        return this.h != null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1168bf0
    public final C2543rl0 d() {
        if (i || this.h != null) {
            return this.h;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1168bf0
    public final String e() {
        return this.g;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1168bf0
    public final Ze0 f() {
        return this.f;
    }

    @Override // com.android.tools.r8.internal.AbstractC1932kf0
    public final C1082af0 i() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1932kf0
    public final InterfaceC1168bf0 j() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1932kf0
    public final InterfaceC1762if0 l() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1932kf0
    public final boolean r() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC1932kf0
    public final boolean s() {
        return true;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1168bf0
    public final void a(String str) {
        this.g = str;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1168bf0
    public final void a(C2543rl0 c2543rl0) {
        this.h = c2543rl0;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1168bf0
    public final void a(Ze0 ze0) {
        this.f = ze0;
    }
}
