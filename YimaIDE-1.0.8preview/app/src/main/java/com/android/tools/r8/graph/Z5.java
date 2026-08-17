package com.android.tools.r8.graph;

import com.android.tools.r8.dex.code.InterfaceC0017b;
import com.android.tools.r8.dex.code.InterfaceC0027d;
import com.android.tools.r8.internal.AbstractC1597gi0;
import com.android.tools.r8.internal.AbstractC2004lX;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C1041a8;
import com.android.tools.r8.internal.C1341di0;
import com.android.tools.r8.internal.C1512fi0;
import com.android.tools.r8.internal.EnumC2326pC;
import java.util.ListIterator;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Z5 {
    public static final /* synthetic */ boolean d = true;
    public final C0333y a;
    public final InterfaceC0265o0 b;
    public AbstractC1597gi0 c = C1512fi0.c;

    public Z5(C0333y c0333y, InterfaceC0265o0 interfaceC0265o0) {
        this.a = c0333y;
        this.b = interfaceC0265o0;
    }

    public final void a(D0 d0, int i) {
        B1 b1A = this.a.a();
        C0322w2 c0322w2P0 = d0.g.p0();
        boolean z = c0322w2P0 == b1A.U5 || c0322w2P0 == b1A.V5;
        if (!d && i > d0.h.size()) {
            x1f.a();
            return;
        }
        for (int i2 = 0; i2 < i; i2++) {
            O2 o2 = (O2) d0.h.get(i2);
            int iOrdinal = o2.H0().ordinal();
            if (iOrdinal == 7) {
                a((E2) o2.C0().c);
            } else if (iOrdinal == 8) {
                a(z ? 1 : 2, (C0336y2) o2.B0().c);
            } else if (iOrdinal == 10) {
                f((I2) o2.F0().c);
            } else if (!d && !o2.R0() && !o2.S0() && !o2.Q0() && !o2.N0() && !o2.Y0()) {
                x1f.a();
                return;
            }
            if (this.c.c()) {
                return;
            }
        }
    }

    public abstract void a(C0245l1 c0245l1);

    public abstract void a(C0322w2 c0322w2);

    public AbstractC3148ys b() {
        boolean z = d;
        if (!z && !this.b.d0()) {
            x1f.a();
            return null;
        }
        if (z || this.b.d0()) {
            return this.b.c().e().U0().a(this.a);
        }
        x1f.a();
        return null;
    }

    public abstract void b(I2 i2);

    public abstract void b(C0322w2 c0322w2);

    public void c() {
        f(this.a.a().d2);
    }

    public abstract void c(C0245l1 c0245l1);

    public void d(C0322w2 c0322w2) {
        boolean z = d;
        if (!z && !this.b.d0()) {
            x1f.a();
            return;
        }
        EnumC2326pC enumC2326pCA = EnumC2326pC.a(c0322w2, this.b.c(), this.a, b());
        if (enumC2326pCA.b()) {
            a(c0322w2);
        } else if (z || enumC2326pCA.e()) {
            g(c0322w2);
        } else {
            x1f.a();
        }
    }

    public void e(I2 i2) {
        a(i2, true);
    }

    public abstract void e(C0245l1 c0245l1);

    public abstract void e(C0322w2 c0322w2);

    public abstract void f(I2 i2);

    public void f(C0322w2 c0322w2) {
        e(c0322w2);
    }

    public abstract void g(C0245l1 c0245l1);

    public abstract void g(C0322w2 c0322w2);

    public void h(C0245l1 c0245l1) {
        g(c0245l1);
    }

    public abstract void h(C0322w2 c0322w2);

    public void f(C0245l1 c0245l1) {
        e(c0245l1);
    }

    public final void c(C0322w2 c0322w2) {
        d(c0322w2);
    }

    public void c(I2 i2) {
        f(i2);
    }

    public void b(C0245l1 c0245l1) {
        a(c0245l1);
    }

    public void b(C0245l1 c0245l1, C1041a8 c1041a8) {
        e(c0245l1);
    }

    public void d(C0245l1 c0245l1) {
        c(c0245l1);
    }

    public void d(I2 i2) {
        f(i2);
    }

    public void a(C0297s5 c0297s5) {
    }

    public void a(D0 d0) {
        B1 b1A = this.a.a();
        C0322w2 c0322w2P0 = d0.g.p0();
        if (c0322w2P0 != b1A.U5 && c0322w2P0 != b1A.V5) {
            a(2, d0.g);
        }
        f(d0.f.e);
        a(d0, d0.h.size());
    }

    public final void a() {
        if (!d && !this.c.d()) {
            x1f.a();
        } else {
            this.c = C1341di0.c;
        }
    }

    public void a(AbstractC2004lX abstractC2004lX) {
        if (d || abstractC2004lX.k()) {
            return;
        }
        x1f.a();
    }

    public void a(C0245l1 c0245l1, C1041a8 c1041a8) {
        a(c0245l1);
    }

    public void a(InterfaceC0017b interfaceC0017b) {
        a(interfaceC0017b.getField());
    }

    public void a(InterfaceC0027d interfaceC0027d) {
        e(interfaceC0027d.getField());
    }

    public void a(I2 i2, ListIterator listIterator, boolean z) {
        f(i2);
    }

    public void a(I2 i2, boolean z) {
        f(i2);
    }

    public void a(I2 i2) {
        f(i2);
    }

    public void a(int i, C0336y2 c0336y2) {
        switch (c0336y2.e.ordinal()) {
            case 0:
                h(c0336y2.o0());
                break;
            case 1:
                f(c0336y2.o0());
                break;
            case 2:
                d(c0336y2.o0());
                break;
            case XmlPullParser.END_TAG /* 3 */:
                b(c0336y2.o0());
                break;
            case 4:
                e(c0336y2.p0());
                break;
            case XmlPullParser.CDSECT /* 5 */:
                h(c0336y2.p0());
                break;
            case XmlPullParser.ENTITY_REF /* 6 */:
                C0322w2 c0322w2P0 = c0336y2.p0();
                d(c0322w2P0.f);
                a(c0322w2P0);
                break;
            case 7:
                a(c0336y2.p0());
                break;
            case 8:
                b(c0336y2.p0());
                break;
            case 9:
                g(c0336y2.p0());
                break;
            default:
                x1f.a();
                break;
        }
    }

    public void a(int i) {
    }

    public final void a(E2 e2) {
        f(e2.e);
        for (I2 i2 : e2.f.b) {
            f(i2);
        }
    }
}
