package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Y80 extends AbstractC2209ns {
    public static final Y80 k = new Y80();
    public static final W80 l = new W80();
    public List e;
    public volatile Object f;
    public volatile Object g;
    public List h;
    public List i;
    public byte j;

    public Y80() {
        this.j = (byte) -1;
        List list = Collections.EMPTY_LIST;
        this.e = list;
        this.f = XmlPullParser.NO_NAMESPACE;
        this.g = XmlPullParser.NO_NAMESPACE;
        this.h = list;
        this.i = list;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        for (int i = 0; i < this.e.size(); i++) {
            abstractC0793Rd.a(1, (TN) this.e.get(i));
        }
        if (!AbstractC2209ns.a(this.f)) {
            AbstractC2209ns.a(abstractC0793Rd, 2, this.f);
        }
        if (!AbstractC2209ns.a(this.g)) {
            AbstractC2209ns.a(abstractC0793Rd, 3, this.g);
        }
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            abstractC0793Rd.a(4, (TN) this.h.get(i2));
        }
        for (int i3 = 0; i3 < this.i.size(); i3++) {
            abstractC0793Rd.a(5, (TN) this.i.get(i3));
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
        int iA = 0;
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            iA = AbstractC1292d60.a((TN) this.e.get(i2), AbstractC0793Rd.b(1), iA);
        }
        if (!AbstractC2209ns.a(this.f)) {
            iA += AbstractC2209ns.a(2, this.f);
        }
        if (!AbstractC2209ns.a(this.g)) {
            iA += AbstractC2209ns.a(3, this.g);
        }
        for (int i3 = 0; i3 < this.h.size(); i3++) {
            iA = AbstractC1292d60.a((TN) this.h.get(i3), AbstractC0793Rd.b(4), iA);
        }
        for (int i4 = 0; i4 < this.i.size(); i4++) {
            iA = AbstractC1292d60.a((TN) this.i.get(i4), AbstractC0793Rd.b(5), iA);
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
        if (!(obj instanceof Y80)) {
            return super.equals(obj);
        }
        Y80 y80 = (Y80) obj;
        if (!this.e.equals(y80.e)) {
            return false;
        }
        Object obj2 = this.f;
        if (obj2 instanceof String) {
            strC = (String) obj2;
        } else {
            strC = ((U7) obj2).c();
            this.f = strC;
        }
        Object obj3 = y80.f;
        if (obj3 instanceof String) {
            strC2 = (String) obj3;
        } else {
            strC2 = ((U7) obj3).c();
            y80.f = strC2;
        }
        return strC.equals(strC2) && k().equals(y80.k()) && this.h.equals(y80.h) && this.i.equals(y80.i) && this.d.equals(y80.d);
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
        int iHashCode = AbstractC1468f90.I0.hashCode() + 779;
        if (this.e.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + this.e.hashCode();
        }
        int iA = Z50.a(iHashCode, 37, 2, 53);
        Object obj = this.f;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            this.f = strC;
        }
        int iHashCode2 = k().hashCode() + ((((strC.hashCode() + iA) * 37) + 3) * 53);
        if (this.h.size() > 0) {
            iHashCode2 = Z50.a(iHashCode2, 37, 4, 53) + this.h.hashCode();
        }
        if (this.i.size() > 0) {
            iHashCode2 = Z50.a(iHashCode2, 37, 5, 53) + this.i.hashCode();
        }
        int iHashCode3 = this.d.hashCode() + (iHashCode2 * 29);
        this.b = iHashCode3;
        return iHashCode3;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.J0.a(Y80.class, X80.class);
    }

    public final String k() {
        Object obj = this.g;
        if (obj instanceof String) {
            return (String) obj;
        }
        String strC = ((U7) obj).c();
        this.g = strC;
        return strC;
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public final X80 d() {
        return this == k ? new X80() : new X80().a(this);
    }

    public Y80(AbstractC0911Vr abstractC0911Vr) {
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
        return new X80(c0859Tr);
    }
}
