package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC3179zC;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.Ck0;
import com.android.tools.r8.internal.EX;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.naming.C3354w;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.shaking.Y2;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class R2 {
    public static final /* synthetic */ int M = 0;
    public final AbstractC0551Hu A;
    public final AbstractC0551Hu B;
    public final AbstractC0551Hu C;
    public final boolean D;
    public final E2 E;
    public final E3 F;
    public final E3 G;
    public final E3 H;
    public final boolean I;
    public final boolean J;
    public final boolean K;
    public final int L;
    public final String a;
    public final com.android.tools.r8.graph.B1 b;
    public final AbstractC0551Hu c;
    public final C2752uB.n d;
    public final String e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public final Path l;
    public final boolean m;
    public final Path n;
    public final boolean o;
    public final Path p;
    public final Path q;
    public final boolean r;
    public final String s;
    public final C3417k3 t;
    public final E2 u;
    public final E2 v;
    public final E2 w;
    public final AbstractC0551Hu x;
    public final boolean y;
    public final Path z;

    public R2(String str, com.android.tools.r8.graph.B1 b1, List list, List list2, C2752uB.n nVar, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, Path path, boolean z7, Path path2, boolean z8, Path path3, Path path4, boolean z9, String str3, C3417k3 c3417k3, E2 e2, E2 e3, E2 e4, Set set, boolean z10, Path path5, AbstractC0551Hu abstractC0551Hu, AbstractC0551Hu abstractC0551Hu2, AbstractC0551Hu abstractC0551Hu3, boolean z11, E2 e5, E3 e6, E3 e7, E3 e8, boolean z12, boolean z13, boolean z14, int i) {
        this.a = str;
        this.b = b1;
        AbstractC0551Hu.a(list);
        this.c = AbstractC0551Hu.a(list2);
        this.d = nVar;
        this.e = str2;
        this.f = z;
        this.g = z2;
        this.h = z3;
        this.i = z4;
        this.j = z5;
        this.k = z6;
        this.l = path;
        this.m = z7;
        this.n = path2;
        this.o = z8;
        this.p = path3;
        this.q = path4;
        this.r = z9;
        this.s = str3;
        this.t = c3417k3;
        this.u = e2;
        this.v = e3;
        this.w = e4;
        this.x = AbstractC0551Hu.a(set);
        this.y = z10;
        this.z = path5;
        this.A = abstractC0551Hu;
        this.B = abstractC0551Hu2;
        this.C = abstractC0551Hu3;
        this.D = z11;
        this.E = e5;
        this.F = e6;
        this.G = e7;
        this.H = e8;
        this.I = z12;
        this.J = z13;
        this.K = z14;
        this.L = i;
    }

    public static a a(com.android.tools.r8.graph.B1 b1, C2742u50 c2742u50) {
        return new a(b1, c2742u50);
    }

    public boolean A() {
        return this.m;
    }

    public boolean B() {
        return this.j;
    }

    public boolean C() {
        return this.r;
    }

    public E3 b() {
        return this.G;
    }

    public E3 c() {
        return this.F;
    }

    public Path d() {
        return this.q;
    }

    public E2 e() {
        return this.w;
    }

    public C3417k3 f() {
        return this.t;
    }

    public E3 g() {
        return this.H;
    }

    public final E2 h() {
        return this.u;
    }

    public List<K0> i() {
        return this.c;
    }

    public int j() {
        return this.L;
    }

    public C2752uB.n k() {
        return this.d;
    }

    public String l() {
        return this.e;
    }

    public Path m() {
        return this.l;
    }

    public Path n() {
        return this.p;
    }

    public Path o() {
        return this.n;
    }

    public String p() {
        return this.s;
    }

    public List<Y2> q() {
        return this.x;
    }

    public Path r() {
        return this.z;
    }

    public boolean s() {
        return this.q != null;
    }

    public final boolean t() {
        return AbstractC3179zC.b(this.x, new EX() { // from class: b3c
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return ((Y2) obj).w();
            }
        });
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        C3417k3 c3417k3 = this.t;
        if (c3417k3.a || c3417k3.b || c3417k3.c || c3417k3.d || c3417k3.e || c3417k3.f || c3417k3.j || c3417k3.k || c3417k3.l || c3417k3.m || c3417k3.n || c3417k3.o || c3417k3.p || c3417k3.q || c3417k3.r || c3417k3.s || c3417k3.t) {
            c3417k3.a(sb);
            sb.append(Wf0.c);
        }
        Ck0 it = this.x.iterator();
        while (it.hasNext()) {
            ((Y2) it.next()).a(sb);
            sb.append(Wf0.c);
        }
        return sb.toString();
    }

    public boolean u() {
        return this.I;
    }

    public boolean v() {
        return this.D;
    }

    public boolean w() {
        return this.i;
    }

    public boolean x() {
        return this.h;
    }

    public boolean y() {
        return this.k;
    }

    public boolean z() {
        return this.y;
    }

    public static class a {
        public static final /* synthetic */ boolean R = true;
        public boolean A;
        public Path B;
        public Path C;
        public Path D;
        public Path E;
        public boolean F;
        public Origin G;
        public Position H;
        public final D3 J;
        public final D3 K;
        public final D3 L;
        public boolean M;
        public boolean N;
        public boolean O;
        public boolean P;
        public int Q;
        public final C2742u50 d;
        public boolean g;
        public boolean h;
        public boolean l;
        public Path m;
        public boolean n;
        public Path o;
        public boolean p;
        public Path q;
        public Path r;
        public boolean s;
        public String t;
        public final com.android.tools.r8.graph.B1 z;
        public final ArrayList a = new ArrayList();
        public final ArrayList b = new ArrayList();
        public final ArrayList c = new ArrayList();
        public C2752uB.n e = C2752uB.n.b;
        public String f = XmlPullParser.NO_NAMESPACE;
        public boolean i = true;
        public boolean j = true;
        public boolean k = true;
        public final ArrayList u = new ArrayList();
        public final E2.a v = E2.a();
        public final E2.a w = E2.a();
        public final E2.a x = E2.a();
        public final LinkedHashSet y = new LinkedHashSet();
        public final E2.a I = E2.a();

        public a(com.android.tools.r8.graph.B1 b1, C2742u50 c2742u50) {
            boolean z = E3.c;
            D3 d3 = new D3();
            C0473Eu c0473EuG = AbstractC0551Hu.g();
            c0473EuG.a(new G3("META-INF/services/*", false));
            AbstractC0551Hu abstractC0551HuA = c0473EuG.a();
            d3.b.a(abstractC0551HuA.size() > 0 ? new H3(abstractC0551HuA) : new F3());
            this.J = d3;
            D3 d4 = new D3();
            C0473Eu c0473EuG2 = AbstractC0551Hu.g();
            c0473EuG2.a(new G3("META-INF/services/*", false));
            AbstractC0551Hu abstractC0551HuA2 = c0473EuG2.a();
            d4.b.a(abstractC0551HuA2.size() > 0 ? new H3(abstractC0551HuA2) : new F3());
            this.K = d4;
            D3 d5 = new D3();
            d5.a = false;
            this.L = d5;
            this.M = false;
            this.N = false;
            this.O = false;
            this.P = false;
            this.Q = 0;
            this.z = b1;
            this.d = c2742u50;
        }

        public R2 a() {
            if (this.M && !this.j) {
                this.u.addAll(C3417k3.u);
            }
            if (this.e == C2752uB.n.b && this.j) {
                this.e = C2752uB.n.c;
            }
            return b();
        }

        public final R2 b() {
            String strJoin = String.join(System.lineSeparator(), this.a);
            com.android.tools.r8.graph.B1 b1 = this.z;
            ArrayList arrayList = this.b;
            ArrayList arrayList2 = this.c;
            C2752uB.n nVar = this.e;
            String str = this.f;
            boolean z = this.g;
            boolean z2 = this.h;
            boolean z3 = this.i;
            boolean z4 = this.j;
            boolean z5 = this.k;
            boolean z6 = this.l;
            Path path = this.m;
            boolean z7 = this.n;
            Path path2 = this.o;
            boolean z8 = this.p;
            Path path3 = this.q;
            Path path4 = this.r;
            boolean z9 = this.s;
            String str2 = this.t;
            C3417k3 c3417k3A = C3417k3.a(this.u);
            E2 e2A = this.v.a();
            E2 e2A2 = this.w.a();
            E2 e2A3 = this.x.a();
            LinkedHashSet linkedHashSet = this.y;
            boolean z10 = this.A;
            Path path5 = this.B;
            AbstractC0551Hu abstractC0551HuA = C3354w.a(this.d, this.C);
            AbstractC0551Hu abstractC0551HuA2 = C3354w.a(this.d, this.D);
            AbstractC0551Hu abstractC0551HuA3 = C3354w.a(this.d, this.E);
            boolean z11 = this.F;
            E2 e2A4 = this.I.a();
            D3 d3 = this.J;
            d3.getClass();
            E3 e3 = new E3(d3.b.a(), d3.a);
            D3 d4 = this.K;
            d4.getClass();
            E3 e4 = new E3(d4.b.a(), d4.a);
            D3 d5 = this.L;
            d5.getClass();
            R2 r2 = new R2(strJoin, b1, arrayList, arrayList2, nVar, str, z, z2, z3, z4, z5, z6, path, z7, path2, z8, path3, path4, z9, str2, c3417k3A, e2A, e2A2, e2A3, linkedHashSet, z10, path5, abstractC0551HuA, abstractC0551HuA2, abstractC0551HuA3, z11, e2A4, e3, e4, new E3(d5.b.a(), d5.a), this.N, this.O, this.P, this.Q);
            this.d.a();
            return r2;
        }

        public final void c(I3 i3) {
            this.L.b.a(i3);
        }

        public final void d(F2 f2) {
            this.v.a(f2);
        }

        public a e() {
            this.k = false;
            return this;
        }

        public final Origin f() {
            return this.G;
        }

        public final Position g() {
            return this.H;
        }

        public final boolean h() {
            return this.r != null;
        }

        public final boolean i() {
            return this.N;
        }

        public final boolean j() {
            return this.F;
        }

        public final boolean k() {
            return this.j;
        }

        public final void l() {
            this.r = null;
        }

        public a d() {
            this.i = false;
            return this;
        }

        public final void c(F2 f2) {
            this.w.a(f2);
        }

        public void c(boolean z) {
            this.A = z;
        }

        public a c() {
            this.j = false;
            return this;
        }

        public void a(boolean z) {
            this.h = z;
        }

        public void a(Path path) {
            if (R || this.p) {
                this.q = path;
            } else {
                x1f.a();
            }
        }

        public a a(List<String> list) {
            this.u.addAll(list);
            return this;
        }

        public void a(Y2 y2) {
            this.y.add(y2);
        }

        public final void a(F2 f2) {
            this.I.a(f2);
        }

        public final void a(I3 i3) {
            this.K.b.a(i3);
        }

        public final void a(String str) {
            this.a.add(str);
        }

        public void b(String str) {
            this.t = str;
        }

        public final void b(F2 f2) {
            this.x.a(f2);
        }

        public final a b(I3 i3) {
            this.J.b.a(i3);
            return this;
        }

        public void b(boolean z) {
            this.p = z;
        }
    }

    public E2 a() {
        return this.E;
    }
}
