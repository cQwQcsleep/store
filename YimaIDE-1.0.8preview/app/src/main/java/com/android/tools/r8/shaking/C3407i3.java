package com.android.tools.r8.shaking;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.i3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3407i3 extends C3432n3.a {
    public static final /* synthetic */ boolean s = true;
    public C3427m3 r = null;

    @Override // com.android.tools.r8.shaking.I2.a
    public final I2.a c() {
        return this;
    }

    @Override // com.android.tools.r8.shaking.I2.a
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final C3412j3 a() {
        if (s || this.r != null) {
            return new C3412j3(this.a, b(), this.d, this.e.a(), this.f, this.g, this.h, this.i, this.j, this.k.a(), this.l, this.m, this.n, this.r, null);
        }
        x01.a("Option -if without a subsequent rule.");
        return null;
    }
}
