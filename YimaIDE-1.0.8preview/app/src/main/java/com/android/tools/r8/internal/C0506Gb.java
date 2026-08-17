package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Gb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0506Gb extends AbstractC0779Qp {
    public boolean c;

    public C0506Gb(int i, AbstractC0779Qp abstractC0779Qp) {
        super(i, abstractC0779Qp);
    }

    @Override // com.android.tools.r8.internal.AbstractC0779Qp
    public final J2 a(int i, C3052xj0 c3052xj0, String str, boolean z) {
        if (this.c) {
            k2d.a("Cannot call a visit method after visitEnd has been called");
            return null;
        }
        int i2 = i >>> 24;
        if (i2 != 19) {
            w01.a(F40.a("Invalid type reference sort 0x", Integer.toHexString(i2)));
            return null;
        }
        AbstractC0480Fb.a(i);
        C0636Lb.d(49, str);
        return new C0376Bb(super.a(i, c3052xj0, str, z));
    }

    @Override // com.android.tools.r8.internal.AbstractC0779Qp
    public final void a(H4 h4) {
        if (!this.c) {
            super.a(h4);
        } else {
            k2d.a("Cannot call a visit method after visitEnd has been called");
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0779Qp
    public final void a() {
        if (!this.c) {
            this.c = true;
            super.a();
        } else {
            k2d.a("Cannot call a visit method after visitEnd has been called");
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0779Qp
    public final J2 a(String str, boolean z) {
        if (!this.c) {
            C0636Lb.d(49, str);
            return new C0376Bb(super.a(str, z));
        }
        k2d.a("Cannot call a visit method after visitEnd has been called");
        return null;
    }
}
