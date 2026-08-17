package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L60 extends AbstractC2209ns {
    public static final L60 h = new L60();
    public static final I60 i = new I60();
    public volatile Object e;
    public int f;
    public byte g;

    public L60() {
        this.g = (byte) -1;
        this.e = XmlPullParser.NO_NAMESPACE;
        this.f = 0;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if (!AbstractC2209ns.a(this.e)) {
            AbstractC2209ns.a(abstractC0793Rd, 1, this.e);
        }
        if (this.f != K60.a(1)) {
            abstractC0793Rd.b(2, this.f);
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return h;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i2 = this.c;
        if (i2 != -1) {
            return i2;
        }
        int iA = !AbstractC2209ns.a(this.e) ? AbstractC2209ns.a(1, this.e) : 0;
        if (this.f != K60.a(1)) {
            iA = AbstractC0458Ef.a(this.f, AbstractC0793Rd.b(2), iA);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof L60)) {
            return super.equals(obj);
        }
        L60 l60 = (L60) obj;
        return k().equals(l60.k()) && this.f == l60.f && this.d.equals(l60.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return h.d();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iHashCode = this.d.hashCode() + ((((((k().hashCode() + AbstractC0432Df.a(AbstractC1468f90.e0, 779, 37, 1, 53)) * 37) + 2) * 53) + this.f) * 29);
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.f0.a(L60.class, J60.class);
    }

    public final String k() {
        Object obj = this.e;
        if (obj instanceof String) {
            return (String) obj;
        }
        String strC = ((U7) obj).c();
        this.e = strC;
        return strC;
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public final J60 d() {
        return this == h ? new J60() : new J60().a(this);
    }

    public L60(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.g = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.g;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.g = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new J60(c0859Tr);
    }
}
