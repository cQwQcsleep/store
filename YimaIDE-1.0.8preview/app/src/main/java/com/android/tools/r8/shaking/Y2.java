package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.graph.S5;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC0728Oq;
import com.android.tools.r8.internal.Bc0;
import com.android.tools.r8.internal.C1605gm0;
import com.android.tools.r8.internal.C2753uC;
import com.android.tools.r8.internal.E6;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.shaking.C3461t3;
import com.android.tools.r8.shaking.K3;
import com.android.tools.r8.shaking.Y2;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Y2 extends I2 {
    public static final /* synthetic */ boolean q = true;
    public boolean o;
    public boolean p;

    public Y2(Origin origin, Position position, String str, List list, C3470v2 c3470v2, C3470v2 c3470v3, boolean z, O2 o2, F2 f2, List list2, K3 k3, boolean z2, List list3) {
        super(origin, position, str, list, c3470v2, c3470v3, z, o2, f2, list2, k3, z2, list3);
        this.o = false;
        this.p = false;
    }

    public final boolean A() {
        final E6 e6 = new E6(true);
        c().a(new Consumer() { // from class: q1g
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                e6.b(false);
            }
        }, new Predicate() { // from class: s1g
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Y2.a((K3) obj);
            }
        });
        return e6.a() && b().isEmpty() && a().a == 0 && h().a == 0 && !this.g && d() == O2.c && this.j.isEmpty() && e() == null && g().isEmpty();
    }

    public final void B() {
        this.o = true;
    }

    public String C() {
        return null;
    }

    public abstract String D();

    public String E() {
        return null;
    }

    public final Iterable a(C0333y c0333y, S5 s5, Collection collection) {
        List<com.android.tools.r8.graph.I2> listA = c().a();
        if (listA != null) {
            return com.android.tools.r8.graph.D2.a((Collection) listA, (InterfaceC0189d1) new X2(this, c0333y));
        }
        if (k()) {
            K3 k3E = e();
            k3E.getClass();
            if (k3E instanceof K3.b) {
                com.android.tools.r8.graph.I2 i2B = e().b();
                C1605gm0 c1605gm0 = c0333y.I;
                if (c1605gm0 == null || !c1605gm0.d(i2B)) {
                    return com.android.tools.r8.graph.D2.a((Collection) s5.g(i2B), (InterfaceC0189d1) c0333y);
                }
                C1605gm0 c1605gm1 = c0333y.I;
                if (!C1605gm0.d && !c1605gm1.a.containsKey(i2B)) {
                    x1f.a();
                    return null;
                }
                com.android.tools.r8.graph.E0 e0D = c0333y.d((com.android.tools.r8.graph.I2) c1605gm1.a.get(i2B));
                if (!q && (e0D == null || !e0D.a0())) {
                    x1f.a();
                    return null;
                }
                com.android.tools.r8.graph.D2 d2X = e0D.X();
                int i = AbstractC0551Hu.c;
                return AbstractC0728Oq.a(new Bc0(d2X), com.android.tools.r8.graph.D2.a((Collection) s5.g(i2B), (InterfaceC0189d1) c0333y));
            }
        }
        return collection;
    }

    @Override // com.android.tools.r8.shaking.I2
    public boolean equals(Object obj) {
        if (!(obj instanceof Y2)) {
            return false;
        }
        Y2 y2 = (Y2) obj;
        if (this.o == y2.o && Objects.equals(D(), y2.D()) && Objects.equals(C(), y2.C())) {
            return super.equals(y2);
        }
        return false;
    }

    @Override // com.android.tools.r8.shaking.I2
    public int hashCode() {
        int iHashCode = (D().hashCode() * 9) + (this.o ? 1 : 0);
        String strC = C();
        return (iHashCode * 3) + (strC != null ? strC.hashCode() : 0) + super.hashCode();
    }

    public boolean l() {
        return this instanceof C3472w;
    }

    public C3452s m() {
        return null;
    }

    public U1 n() {
        return null;
    }

    public D2 o() {
        return null;
    }

    public C3412j3 p() {
        return null;
    }

    public C3427m3 q() {
        return null;
    }

    public U3 r() {
        return null;
    }

    public W3 s() {
        return null;
    }

    public final void t() {
        this.p = true;
    }

    public Iterable<S3> u() {
        List<C3461t3> listG = g();
        return AbstractC0728Oq.a((Iterable[]) Arrays.copyOf(new Iterable[]{K3.a(b()), F2.a(c()), K3.a(this.j), K3.a(e()), listG == null ? new defpackage.s() : C2753uC.b(listG, new Function() { // from class: t1g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C3461t3) obj).j();
            }
        })}, 5));
    }

    public boolean v() {
        return this instanceof C3452s;
    }

    public boolean w() {
        return false;
    }

    public boolean x() {
        return this instanceof D2;
    }

    public boolean y() {
        return this instanceof C3427m3;
    }

    public boolean z() {
        return this instanceof W3;
    }

    public static /* synthetic */ boolean a(K3 k3) {
        return !k3.d();
    }

    @Override // com.android.tools.r8.shaking.I2
    public StringBuilder a(StringBuilder sb) {
        sb.append("-");
        sb.append(D());
        Wf0.a(sb, ",", C());
        Wf0.a(sb, " ", E());
        sb.append(' ');
        super.a(sb);
        return sb;
    }
}
