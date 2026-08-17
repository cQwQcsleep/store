package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class U60 extends AbstractC2209ns {
    public static final U60 k = new U60();
    public static final S60 l = new S60();
    public volatile Object e;
    public C2149n80 f;
    public List g;
    public List h;
    public S70 i;
    public byte j;

    public U60() {
        this.j = (byte) -1;
        this.e = XmlPullParser.NO_NAMESPACE;
        List list = Collections.EMPTY_LIST;
        this.g = list;
        this.h = list;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if (!AbstractC2209ns.a(this.e)) {
            AbstractC2209ns.a(abstractC0793Rd, 1, this.e);
        }
        if (this.f != null) {
            abstractC0793Rd.a(2, l());
        }
        for (int i = 0; i < this.g.size(); i++) {
            abstractC0793Rd.a(3, (TN) this.g.get(i));
        }
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            abstractC0793Rd.a(4, (TN) this.h.get(i2));
        }
        if (this.i != null) {
            abstractC0793Rd.a(5, k());
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return k;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int iA = !AbstractC2209ns.a(this.e) ? AbstractC2209ns.a(1, this.e) : 0;
        if (this.f != null) {
            iA += AbstractC0793Rd.a(l()) + AbstractC0793Rd.b(2);
        }
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            iA = AbstractC1292d60.a((TN) this.g.get(i2), AbstractC0793Rd.b(3), iA);
        }
        for (int i3 = 0; i3 < this.h.size(); i3++) {
            iA = AbstractC1292d60.a((TN) this.h.get(i3), AbstractC0793Rd.b(4), iA);
        }
        if (this.i != null) {
            iA += AbstractC0793Rd.a(k()) + AbstractC0793Rd.b(5);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        String strC;
        String strC2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof U60)) {
            return super.equals(obj);
        }
        U60 u60 = (U60) obj;
        Object obj2 = this.e;
        if (obj2 instanceof String) {
            strC = (String) obj2;
        } else {
            strC = ((U7) obj2).c();
            this.e = strC;
        }
        Object obj3 = u60.e;
        if (obj3 instanceof String) {
            strC2 = (String) obj3;
        } else {
            strC2 = ((U7) obj3).c();
            u60.e = strC2;
        }
        if (!strC.equals(strC2)) {
            return false;
        }
        C2149n80 c2149n80 = this.f;
        if ((c2149n80 != null) != (u60.f != null)) {
            return false;
        }
        if ((c2149n80 != null && !l().equals(u60.l())) || !this.g.equals(u60.g) || !this.h.equals(u60.h)) {
            return false;
        }
        S70 s70 = this.i;
        if ((s70 != null) != (u60.i != null)) {
            return false;
        }
        return (s70 == null || k().equals(u60.k())) && this.d.equals(u60.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return k.d();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        String strC;
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iA = AbstractC0432Df.a(AbstractC1468f90.O0, 779, 37, 1, 53);
        Object obj = this.e;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            this.e = strC;
        }
        int iHashCode = strC.hashCode() + iA;
        if (this.f != null) {
            iHashCode = l().hashCode() + Z50.a(iHashCode, 37, 2, 53);
        }
        if (this.g.size() > 0) {
            iHashCode = this.g.hashCode() + Z50.a(iHashCode, 37, 3, 53);
        }
        if (this.h.size() > 0) {
            iHashCode = this.h.hashCode() + Z50.a(iHashCode, 37, 4, 53);
        }
        if (this.i != null) {
            iHashCode = k().hashCode() + Z50.a(iHashCode, 37, 5, 53);
        }
        int iHashCode2 = this.d.hashCode() + (iHashCode * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.P0.a(U60.class, T60.class);
    }

    public final S70 k() {
        S70 s70 = this.i;
        return s70 == null ? S70.h : s70;
    }

    public final C2149n80 l() {
        C2149n80 c2149n80 = this.f;
        return c2149n80 == null ? C2149n80.h : c2149n80;
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public final T60 d() {
        return this == k ? new T60() : new T60().a(this);
    }

    public U60(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.j = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.j;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.j = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new T60(c0859Tr);
    }
}
