package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Xe0 extends AbstractC1932kf0 implements InterfaceC1168bf0, InterfaceC1762if0 {
    public final C2496rC e;
    public Ze0 f;
    public String g;
    public C2543rl0 h;

    public Xe0(C2496rC c2496rC) {
        this.e = c2496rC;
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
        return this.h;
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
    public final Xe0 g() {
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

    @Override // com.android.tools.r8.internal.InterfaceC1168bf0
    public final void a(String str) {
        this.g = str;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1168bf0
    public final void a(Ze0 ze0) {
        this.f = ze0;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1168bf0
    public final void a(C2543rl0 c2543rl0) {
        this.h = c2543rl0;
    }
}
