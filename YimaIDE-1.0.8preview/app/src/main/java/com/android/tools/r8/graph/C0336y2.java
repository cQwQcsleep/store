package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0336y2;
import com.android.tools.r8.internal.C0497Fs;
import com.android.tools.r8.internal.UK;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.utils.structural.A;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.hkh;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.graph.y2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0336y2 extends X3 implements InterfaceC0221h5, UK {
    public static final /* synthetic */ boolean i = true;
    public final EnumC0329x2 e;
    public final AbstractC0287r2 f;
    public final boolean g;
    public final C0322w2 h;

    public C0336y2(EnumC0329x2 enumC0329x2, AbstractC0287r2 abstractC0287r2, boolean z, C0322w2 c0322w2) {
        this.e = enumC0329x2;
        this.f = abstractC0287r2;
        this.g = z;
        this.h = c0322w2;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public final C0497Fs a(AbstractC3345r0 abstractC3345r0) {
        String strD;
        String string;
        String string2;
        boolean z;
        int i2;
        if (this.e.g()) {
            C0322w2 c0322w2P0 = p0();
            strD = abstractC3345r0.d(c0322w2P0.f);
            C0322w2 c0322w2 = this.h;
            string = c0322w2 != null ? abstractC3345r0.a(c0322w2).toString() : abstractC3345r0.a(c0322w2P0).toString();
            string2 = c0322w2P0.i.a(abstractC3345r0);
            if (!c0322w2P0.f.Z0().equals("Ljava/lang/invoke/LambdaMetafactory;")) {
                z = this.g;
            } else {
                if (!i && this.g) {
                    x1f.a();
                    return null;
                }
                z = false;
            }
        } else {
            if (!i && !this.e.a()) {
                x1f.a();
                return null;
            }
            C0245l1 c0245l1O0 = o0();
            strD = abstractC3345r0.d(c0245l1O0.f);
            string = abstractC3345r0.a(c0245l1O0).toString();
            string2 = abstractC3345r0.c(c0245l1O0.i).toString();
            z = this.g;
        }
        String str = string2;
        boolean z2 = z;
        String str2 = strD;
        String str3 = string;
        switch (this.e.ordinal()) {
            case 0:
                i2 = 4;
                break;
            case 1:
                i2 = 2;
                break;
            case 2:
                i2 = 3;
                break;
            case XmlPullParser.END_TAG /* 3 */:
                i2 = 1;
                break;
            case 4:
                i2 = 6;
                break;
            case XmlPullParser.CDSECT /* 5 */:
                i2 = 5;
                break;
            case XmlPullParser.ENTITY_REF /* 6 */:
                i2 = 8;
                break;
            case 7:
            case 9:
                i2 = 7;
                break;
            case 8:
                i2 = 9;
                break;
            default:
                hkh.a();
                return null;
        }
        return new C0497Fs(i2, str2, str3, str, z2);
    }

    @Override // com.android.tools.r8.internal.UK
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        a(oVar);
    }

    @Override // com.android.tools.r8.graph.E
    public final boolean c(Object obj) {
        if (obj instanceof C0336y2) {
            C0336y2 c0336y2 = (C0336y2) obj;
            if (this.e.equals(c0336y2.e) && this.f.equals(c0336y2.f) && this.g == c0336y2.g && Objects.equals(this.h, c0336y2.h)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public final String l0() {
        return toString();
    }

    @Override // com.android.tools.r8.graph.E
    public final int n0() {
        return Objects.hash(this.e, Integer.valueOf(this.f.n0()), Boolean.valueOf(this.g), this.h);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: dui
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                C0336y2.a(a);
            }
        };
    }

    public final C0245l1 o0() {
        if (i || this.e.a()) {
            return (C0245l1) this.f;
        }
        x1f.a();
        return null;
    }

    public final C0322w2 p0() {
        if (i || this.e.g()) {
            return (C0322w2) this.f;
        }
        x1f.a();
        return null;
    }

    public final boolean q0() {
        return this.e.a();
    }

    public final boolean r0() {
        return this.e.g();
    }

    public final String toString() {
        return "MethodHandle: {" + this.e + ", " + this.f.m0() + "}";
    }

    @Override // com.android.tools.r8.internal.UK
    public final int y() {
        return 6;
    }

    public static C0336y2 a(C0497Fs c0497Fs, C0178b4 c0178b4, I2 i2) {
        AbstractC0287r2 abstractC0287r2A;
        EnumC0329x2 enumC0329x2A = EnumC0329x2.a(c0497Fs, c0178b4, i2);
        if (enumC0329x2A.a()) {
            String str = c0497Fs.b;
            String str2 = c0497Fs.c;
            String str3 = c0497Fs.d;
            abstractC0287r2A = c0178b4.a.a.a(c0178b4.f(str), c0178b4.e(str3), c0178b4.d(str2));
        } else {
            String str4 = c0497Fs.b;
            abstractC0287r2A = c0178b4.a(c0178b4.f(str4), c0497Fs.c, c0497Fs.d);
        }
        return c0178b4.a.a.a(enumC0329x2A, abstractC0287r2A, c0497Fs.e, (C0322w2) null);
    }

    public final void a(C0333y c0333y, com.android.tools.r8.dex.M m) {
        if (m.a(this)) {
            boolean zS0 = this.f.s0();
            AbstractC0287r2 abstractC0287r2 = this.f;
            if (zS0) {
                abstractC0287r2.o0().a(c0333y, m);
                return;
            }
            C0322w2 c0322w2Q0 = abstractC0287r2.q0();
            if (this.h != null) {
                if (c0322w2Q0.b(c0333y, m)) {
                    C0322w2 c0322w2 = this.h;
                    c0322w2.getClass();
                    H2 h2A = c0333y.w().a(c0322w2);
                    h2A.getClass();
                    m.a(h2A);
                    return;
                }
                return;
            }
            if (c0322w2Q0.b(c0333y, m)) {
                H2 h2A2 = c0333y.w().a(c0322w2Q0);
                h2A2.getClass();
                m.a(h2A2);
            }
        }
    }

    @Override // com.android.tools.r8.internal.UK
    public final int a(UK uk, AbstractC3519a abstractC3519a) {
        return a((C0336y2) uk, abstractC3519a);
    }

    public static int a(C0336y2 c0336y2) {
        return c0336y2.e.b;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: eui
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return C0336y2.a((C0336y2) obj);
            }
        }).a(new Function() { // from class: gui
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0336y2) obj).o0();
            }
        }, new Predicate() { // from class: fui
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((C0336y2) obj).q0();
            }
        }).a(new Function() { // from class: iui
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0336y2) obj).p0();
            }
        }, new Predicate() { // from class: hui
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((C0336y2) obj).r0();
            }
        }).b(new Predicate() { // from class: jui
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((C0336y2) obj).g;
            }
        }).j(new Function() { // from class: kui
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0336y2) obj).h;
            }
        });
    }

    @Override // com.android.tools.r8.graph.X3
    public final int a(C0284q5 c0284q5) {
        return C0284q5.a(this, c0284q5.l);
    }
}
