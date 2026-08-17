package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Qb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0765Qb extends AbstractC1966l20 {
    public boolean b;

    public C0765Qb(int i, AbstractC1966l20 abstractC1966l20) {
        super(i, abstractC1966l20);
    }

    @Override // com.android.tools.r8.internal.AbstractC1966l20
    public final J2 a(int i, C3052xj0 c3052xj0, String str, boolean z) {
        if (this.b) {
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
        AbstractC1966l20 abstractC1966l20 = this.a;
        return new C0376Bb(abstractC1966l20 != null ? abstractC1966l20.a(i, c3052xj0, str, z) : null);
    }

    @Override // com.android.tools.r8.internal.AbstractC1966l20
    public final void a(H4 h4) {
        if (!this.b) {
            super.a(h4);
        } else {
            k2d.a("Cannot call a visit method after visitEnd has been called");
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1966l20
    public final void a() {
        if (!this.b) {
            this.b = true;
            AbstractC1966l20 abstractC1966l20 = this.a;
            if (abstractC1966l20 != null) {
                abstractC1966l20.a();
                return;
            }
            return;
        }
        k2d.a("Cannot call a visit method after visitEnd has been called");
    }

    @Override // com.android.tools.r8.internal.AbstractC1966l20
    public final J2 a(String str, boolean z) {
        if (!this.b) {
            C0636Lb.d(49, str);
            AbstractC1966l20 abstractC1966l20 = this.a;
            return new C0376Bb(abstractC1966l20 != null ? abstractC1966l20.a(str, z) : null);
        }
        k2d.a("Cannot call a visit method after visitEnd has been called");
        return null;
    }
}
