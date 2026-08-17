package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Li, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0643Li extends AbstractC2209ns {
    public static final C0643Li l = new C0643Li();
    public static final C0513Gi m = new C0513Gi();
    public int e;
    public volatile Object f;
    public List g;
    public C0720Oi h;
    public List i;
    public AJ j;
    public byte k;

    public C0643Li() {
        this.k = (byte) -1;
        this.f = XmlPullParser.NO_NAMESPACE;
        List list = Collections.EMPTY_LIST;
        this.g = list;
        this.i = list;
        this.j = C3101yJ.d;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if ((this.e & 1) != 0) {
            AbstractC2209ns.a(abstractC0793Rd, 1, this.f);
        }
        for (int i = 0; i < this.g.size(); i++) {
            abstractC0793Rd.a(2, (TN) this.g.get(i));
        }
        if ((this.e & 2) != 0) {
            abstractC0793Rd.a(3, l());
        }
        for (int i2 = 0; i2 < this.i.size(); i2++) {
            abstractC0793Rd.a(4, (TN) this.i.get(i2));
        }
        for (int i3 = 0; i3 < this.j.size(); i3++) {
            AbstractC2209ns.a(abstractC0793Rd, 5, this.j.e(i3));
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return l;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        int iA = (this.e & 1) != 0 ? AbstractC2209ns.a(1, this.f) : 0;
        for (int i3 = 0; i3 < this.g.size(); i3++) {
            iA = AbstractC1292d60.a((TN) this.g.get(i3), AbstractC0793Rd.b(2), iA);
        }
        if ((this.e & 2) != 0) {
            iA += AbstractC0793Rd.a(l()) + AbstractC0793Rd.b(3);
        }
        for (int i4 = 0; i4 < this.i.size(); i4++) {
            iA = AbstractC1292d60.a((TN) this.i.get(i4), AbstractC0793Rd.b(4), iA);
        }
        int iA2 = 0;
        while (true) {
            int size = this.j.size();
            AJ aj = this.j;
            if (i2 >= size) {
                int iC = this.d.c() + aj.size() + iA + iA2;
                this.c = iC;
                return iC;
            }
            Object objE = aj.e(i2);
            iA2 += objE instanceof String ? AbstractC0793Rd.a((String) objE) : AbstractC0793Rd.a((U7) objE);
            i2++;
        }
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == l ? new C0539Hi() : new C0539Hi().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0643Li)) {
            return super.equals(obj);
        }
        C0643Li c0643Li = (C0643Li) obj;
        int i = this.e & 1;
        if ((i != 0) != ((c0643Li.e & 1) != 0)) {
            return false;
        }
        if ((i == 0 || k().equals(c0643Li.k())) && this.g.equals(c0643Li.g) && m() == c0643Li.m()) {
            return (!m() || l().equals(c0643Li.l())) && this.i.equals(c0643Li.i) && this.j.equals(c0643Li.j) && this.d.equals(c0643Li.d);
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        l.getClass();
        return new C0539Hi();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC0877Uj.o.hashCode() + 779;
        if ((this.e & 1) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + k().hashCode();
        }
        if (this.g.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 2, 53) + this.g.hashCode();
        }
        if (m()) {
            iHashCode = Z50.a(iHashCode, 37, 3, 53) + l().hashCode();
        }
        if (this.i.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 4, 53) + this.i.hashCode();
        }
        if (this.j.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 5, 53) + this.j.hashCode();
        }
        int iHashCode2 = this.d.hashCode() + (iHashCode * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC0877Uj.p.a(C0643Li.class, C0539Hi.class);
    }

    public final String k() {
        Object obj = this.f;
        if (obj instanceof String) {
            return (String) obj;
        }
        U7 u7 = (U7) obj;
        String strC = u7.c();
        if (u7.a()) {
            this.f = strC;
        }
        return strC;
    }

    public final C0720Oi l() {
        C0720Oi c0720Oi = this.h;
        return c0720Oi == null ? C0720Oi.k : c0720Oi;
    }

    public final boolean m() {
        return (this.e & 2) != 0;
    }

    public C0643Li(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.k = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.k;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        for (int i = 0; i < this.g.size(); i++) {
            if (!((C0798Ri) this.g.get(i)).a()) {
                this.k = (byte) 0;
                return false;
            }
        }
        if (m() && !l().a()) {
            this.k = (byte) 0;
            return false;
        }
        this.k = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C0539Hi(c0859Tr);
    }
}
