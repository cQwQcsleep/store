package com.android.tools.r8.dex;

import com.android.tools.r8.dex.I;
import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.internal.C1202c30;
import com.android.tools.r8.internal.C1573gT;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class H {
    public static final /* synthetic */ boolean y = true;
    public final int a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;
    public final int j;
    public final int k;
    public int l = -1;
    public int m = -1;
    public int n = -1;
    public int o = -1;
    public int p = -1;
    public int q = -1;
    public int r = -1;
    public int s = -1;
    public int t = -1;
    public int u = -1;
    public int v = -1;
    public int w = -1;
    public int x = -1;

    public H(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = i6;
        this.g = i7;
        this.h = i8;
        this.i = i9;
        this.j = i10;
        this.k = i11;
        boolean z = y;
        if (!z && i2 > i3) {
            x1f.a();
            throw null;
        }
        if (!z && i3 > i4) {
            x1f.a();
            throw null;
        }
        if (!z && i4 > i5) {
            x1f.a();
            throw null;
        }
        if (!z && i5 > i6) {
            x1f.a();
            throw null;
        }
        if (!z && i6 > i7) {
            x1f.a();
            throw null;
        }
        if (!z && i7 > i10) {
            x1f.a();
            throw null;
        }
        if (!z && i8 > i10) {
            x1f.a();
            throw null;
        }
        if (z || i9 <= i10) {
            return;
        }
        x1f.a();
        throw null;
    }

    public final ArrayList a(L l, int i, int i2, int i3, int i4) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new I(0, i, 1));
        arrayList.add(new I(1, i3, C0284q5.a(l.c.j).size()));
        arrayList.add(new I(2, this.c, C0284q5.a(l.c.g).size()));
        arrayList.add(new I(3, this.d, C0284q5.a(l.c.f).size()));
        arrayList.add(new I(4, this.e, C0284q5.a(l.c.i).size()));
        arrayList.add(new I(5, this.f, C0284q5.a(l.c.h).size()));
        arrayList.add(new I(6, this.g, l.c.e.length));
        arrayList.add(new I(7, this.h, C0284q5.a(l.c.k).size()));
        arrayList.add(new I(8, this.i, C0284q5.a(l.c.l).size()));
        boolean z = y;
        if (!z && !a(this.l, true)) {
            x1f.a();
            return null;
        }
        arrayList.add(new I(8193, this.l, this.x));
        if (!z && !a(this.m, false)) {
            x1f.a();
            return null;
        }
        arrayList.add(new I(8195, this.m, ((C1573gT) l.f.b.keySet()).b.l));
        if (!z && !a(this.n, true)) {
            x1f.a();
            return null;
        }
        arrayList.add(new I(4097, this.n, ((C1573gT) l.f.c.keySet()).b.l));
        if (i4 == 0) {
            i2 = 0;
        }
        arrayList.add(new I(8194, i4, i2));
        if (!z && !a(this.p, false)) {
            x1f.a();
            return null;
        }
        arrayList.add(new I(8196, this.p, ((C1573gT) l.f.e.keySet()).b.l));
        if (!z && !a(this.t, false)) {
            x1f.a();
            return null;
        }
        arrayList.add(new I(8192, this.t, ((C1202c30) l.f.i.keySet()).b.l));
        if (!z && !a(this.u, false)) {
            x1f.a();
            return null;
        }
        arrayList.add(new I(8197, this.u, ((C1573gT) l.f.j.keySet()).b.l));
        if (!z && !a(this.q, true)) {
            x1f.a();
            return null;
        }
        arrayList.add(new I(4099, this.q, ((C1573gT) l.f.f.keySet()).b.l));
        if (!z && !a(this.r, true)) {
            x1f.a();
            return null;
        }
        arrayList.add(new I(4098, this.r, ((C1573gT) l.f.g.keySet()).b.l));
        if (!z && !a(this.s, true)) {
            x1f.a();
            return null;
        }
        arrayList.add(new I(8198, this.s, ((C1573gT) l.f.h.keySet()).b.l));
        arrayList.add(new I(4096, this.v, 1));
        arrayList.sort(Comparator.comparingInt(new ToIntFunction() { // from class: c16
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((I) obj).a();
            }
        }));
        return arrayList;
    }

    public final int b() {
        if (y || a(this.o, false)) {
            return this.o;
        }
        x1f.a();
        return 0;
    }

    public final void c(int i) {
        if (y || this.r == -1) {
            this.r = i;
        } else {
            x1f.a();
        }
    }

    public final void d(int i) {
        if (y || this.q == -1) {
            this.q = i;
        } else {
            x1f.a();
        }
    }

    public final void e(int i) {
        if (y || this.p == -1) {
            this.p = i;
        } else {
            x1f.a();
        }
    }

    public final void f(int i) {
        if (y || this.t == -1) {
            this.t = i;
        } else {
            x1f.a();
        }
    }

    public final void g(int i) {
        if (y || this.u == -1) {
            this.u = i;
        } else {
            x1f.a();
        }
    }

    public final void h(int i) {
        if (y || this.o == -1) {
            this.o = i;
        } else {
            x1f.a();
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Header: ");
        sb.append(this.b - this.a);
        sb.append("\nStringIds: ");
        sb.append(this.c - this.b);
        sb.append("\ntypeIds: ");
        sb.append(this.d - this.c);
        sb.append("\nprotoIds: ");
        sb.append(this.e - this.d);
        sb.append("\nfieldIds: ");
        sb.append(this.f - this.e);
        sb.append("\nmethodIds: ");
        sb.append(this.g - this.f);
        sb.append("\nclassDefs: ");
        sb.append(this.h - this.g);
        sb.append("\ncallSiteIds: ");
        sb.append(this.i - this.h);
        sb.append("\nmethodHandleIds: ");
        sb.append(this.j - this.i);
        sb.append("\ncode: ");
        sb.append(this.m - this.l);
        sb.append("\ndebugInfo: ");
        sb.append(this.n - this.m);
        sb.append("\ntypeList: ");
        int i = this.o;
        if (i <= 0) {
            i = this.p;
        }
        sb.append(i - this.n);
        sb.append("\nstringData: ");
        int i2 = this.o;
        sb.append(i2 > 0 ? this.p - i2 : 0);
        sb.append("\nannotations: ");
        sb.append(this.t - this.p);
        sb.append("\nclassData: ");
        sb.append(this.u - this.t);
        sb.append("\nencodedArrays: ");
        sb.append(this.v - this.r);
        sb.append("\nannotationSets: ");
        sb.append(this.r - this.q);
        sb.append("\nannotationSetRefLists: ");
        sb.append(this.s - this.r);
        sb.append("\nannotationDirectories: ");
        sb.append(this.v - this.s);
        sb.append("\nmap: ");
        sb.append(this.w - this.v);
        sb.append("\nendOfFile: ");
        sb.append(this.w);
        sb.append("\n");
        return sb.toString();
    }

    public final void b(int i) {
        if (y || this.s == -1) {
            this.s = i;
        } else {
            x1f.a();
        }
    }

    public static boolean a(int i, boolean z) {
        if (i != -1) {
            return !z || i % 4 == 0;
        }
        return false;
    }

    public final void a(int i) {
    }

    public final int a() {
        return this.w;
    }
}
