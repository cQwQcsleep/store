package com.android.tools.r8.graph.proto;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0304t5;
import com.android.tools.r8.graph.C0306u0;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.proto.b;
import com.android.tools.r8.graph.proto.c;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.BU;
import com.android.tools.r8.internal.C0399By;
import com.android.tools.r8.internal.C1359dx;
import com.android.tools.r8.internal.C2224o3;
import com.android.tools.r8.internal.C3153yx;
import com.android.tools.r8.internal.InterfaceC0425Cy;
import com.android.tools.r8.internal.InterfaceC0943Wx;
import com.android.tools.r8.internal.InterfaceC2494rA;
import com.android.tools.r8.internal.InterfaceC2942wU;
import com.android.tools.r8.internal.J;
import com.android.tools.r8.internal.Y6;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class c {
    public static final C0399By e = new C0399By();
    public static final c f = new c();
    public static final /* synthetic */ boolean g = true;
    public final InterfaceC0425Cy a;
    public final int b;
    public final d c;
    public final boolean d;

    public c(C0399By c0399By, int i, d dVar, boolean z) {
        boolean z2 = g;
        if (!z2 && c0399By == null) {
            x1f.a();
            throw null;
        }
        if (!z2 && dVar == null) {
            x1f.a();
            throw null;
        }
        if (!z2 && c0399By.isEmpty() && c0399By != e) {
            x1f.a();
            throw null;
        }
        if (!z2 && c0399By.isEmpty()) {
            dVar.getClass();
            if ((dVar instanceof f) && !z) {
                x01.a("should use empty.");
                throw null;
            }
        }
        if (!z2 && i < 0) {
            x1f.a();
            throw null;
        }
        this.a = c0399By;
        this.b = i;
        this.c = dVar;
        this.d = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:73:0x0054, code lost:
    
        r8 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public c a(c cVar) {
        int iB;
        InterfaceC0943Wx interfaceC0943Wx;
        if (c()) {
            return cVar;
        }
        if (cVar.c()) {
            return this;
        }
        a aVarA = a().a(this);
        InterfaceC2942wU it = this.a.b().iterator();
        InterfaceC2942wU it2 = cVar.a.b().iterator();
        int i = 0;
        while (it2.hasNext()) {
            InterfaceC0943Wx interfaceC0943Wx2 = (InterfaceC0943Wx) it2.next();
            int iA = interfaceC0943Wx2.a();
            b bVarA = (b) interfaceC0943Wx2.getValue();
            if (it.hasNext()) {
                interfaceC0943Wx = (InterfaceC0943Wx) it.next();
                it.previous();
                while (interfaceC0943Wx != null && interfaceC0943Wx.a() <= iA + i) {
                    if (((b) ((InterfaceC0943Wx) it.next()).getValue()).c()) {
                        i++;
                    }
                    if (it.hasNext()) {
                        interfaceC0943Wx = (InterfaceC0943Wx) it.next();
                        it.previous();
                    } else {
                        interfaceC0943Wx = null;
                    }
                }
                if (interfaceC0943Wx != null && interfaceC0943Wx.a() == iA + i) {
                    b bVar = (b) interfaceC0943Wx.getValue();
                    if (!g && bVar.c()) {
                        x1f.a();
                        return null;
                    }
                    bVarA = bVar.a(bVarA);
                }
                aVarA.a(iA + i, bVarA);
            } else {
                interfaceC0943Wx = null;
            }
        }
        J c1359dx = this.b <= 30 ? new C1359dx() : new C3153yx(16);
        c1359dx.b = -1;
        for (int i2 = 0; i2 < this.b; i2++) {
            if (!a(i2).c()) {
                int iB2 = b(i2);
                if (!cVar.a(iB2).c() && (iB = cVar.b(iB2)) != i2 - a(i2, aVarA.a)) {
                    if (i2 != iB) {
                        c1359dx.b(i2, iB);
                    } else {
                        c1359dx.remove(i2);
                    }
                }
            }
        }
        if (!g) {
            if (Y6.a(cVar.d) + Y6.a(this.d) > 1) {
                x1f.a();
                return null;
            }
        }
        a aVarA2 = aVarA.a(this.b);
        aVarA2.c = c1359dx.isEmpty() ? f.a : new e(c1359dx);
        aVarA2.d = this.d || cVar.d;
        return aVarA2.a();
    }

    public final int b(int i) {
        return this.c.a(i - a(i, this.a));
    }

    public final boolean c() {
        return this == f;
    }

    public int d() {
        if (g || !c()) {
            return this.b;
        }
        x1f.a();
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            c cVar = (c) obj;
            if (this.a.equals(cVar.a) && this.c.equals(cVar.c) && this.b == cVar.b && this.d == cVar.d) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.c, Integer.valueOf(this.b), Boolean.valueOf(this.d));
    }

    public final boolean b() {
        d dVar = this.c;
        dVar.getClass();
        return !(dVar instanceof f);
    }

    public static class a {
        public final C0399By a = new C0399By();
        public int b = -1;
        public d c = f.a;
        public boolean d;

        public c a() {
            if (this.a.isEmpty()) {
                d dVar = this.c;
                dVar.getClass();
                if ((dVar instanceof f) && !this.d) {
                    return c.f;
                }
            }
            return new c(this.a.isEmpty() ? c.e : this.a, this.b, this.c, this.d);
        }

        public final a a(c cVar) {
            cVar.a(new InterfaceC2494rA() { // from class: xig
                @Override // com.android.tools.r8.internal.InterfaceC2494rA
                public final void a(int i, Object obj) {
                    this.a.a(i, (b) obj);
                }
            });
            return this;
        }

        public a a(int i, b bVar) {
            this.a.a(i, bVar);
            return this;
        }

        public a a(int i) {
            this.b = i;
            return this;
        }
    }

    public c() {
        this.a = e;
        this.b = -1;
        this.c = f.a;
        this.d = false;
    }

    public static int a(int i, InterfaceC0425Cy interfaceC0425Cy) {
        InterfaceC2942wU it = interfaceC0425Cy.b().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            InterfaceC0943Wx interfaceC0943Wx = (InterfaceC0943Wx) it.next();
            int iA = interfaceC0943Wx.a();
            b bVar = (b) interfaceC0943Wx.getValue();
            if (iA >= i) {
                if (g || iA > i || !bVar.c()) {
                    break;
                    break;
                    break;
                }
                x1f.a();
                return 0;
            }
            if (bVar.c()) {
                i2++;
            }
        }
        return i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public b a(int i) {
        return (b) this.a.getOrDefault(Integer.valueOf(i), b.a);
    }

    public final int a(int i, int i2) {
        return this.c.a(i - i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
    
        if (r0.d == false) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final c a(final C0333y c0333y, final C2224o3 c2224o3, final AbstractC3148ys abstractC3148ys) {
        if (!c()) {
            final a aVarA = a();
            a(new InterfaceC2494rA() { // from class: cig
                @Override // com.android.tools.r8.internal.InterfaceC2494rA
                public final void a(int i, Object obj) {
                    c.a(c0333y, c2224o3, abstractC3148ys, aVarA, i, (b) obj);
                }
            });
            if (aVarA.a.isEmpty()) {
                d dVar = aVarA.c;
                dVar.getClass();
                if (dVar instanceof f) {
                }
            }
            a(new InterfaceC2494rA() { // from class: jig
                @Override // com.android.tools.r8.internal.InterfaceC2494rA
                public final void a(int i, Object obj) {
                    c.a(aVarA, i, (b) obj);
                }
            });
            a aVarA2 = aVarA.a(this.b);
            aVarA2.d = this.d;
            return aVarA2.a();
        }
        return this;
    }

    public static /* synthetic */ void a(C0333y c0333y, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, a aVar, int i, b bVar) {
        b bVarA = bVar.a(c0333y, abstractC3148ys, abstractC3148ys2);
        if (bVarA != bVar) {
            aVar.a(i, bVarA);
        }
    }

    public static a a() {
        return new a();
    }

    public static void a(a aVar, int i, b bVar) {
        if (aVar.a.a(i)) {
            return;
        }
        aVar.a(i, bVar);
    }

    public final void a(InterfaceC2494rA interfaceC2494rA) {
        InterfaceC2942wU it = this.a.b().iterator();
        while (it.hasNext()) {
            InterfaceC0943Wx interfaceC0943Wx = (InterfaceC0943Wx) it.next();
            interfaceC2494rA.a(interfaceC0943Wx.a(), (b) interfaceC0943Wx.getValue());
        }
    }

    public final Consumer a(final C0231j1 c0231j1) {
        return new Consumer() { // from class: nig
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(c0231j1, (C0231j1.a) obj);
            }
        };
    }

    public final void a(C0231j1 c0231j1, C0231j1.a aVar) {
        List list;
        if (aVar.g.isEmpty()) {
            return;
        }
        if (!b()) {
            BU it = this.a.values().iterator();
            while (it.hasNext()) {
                if (((b) it.next()).c()) {
                }
            }
            return;
        }
        ArrayList arrayList = new ArrayList(aVar.g.b.length);
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < c0231j1.c1().size(); i3++) {
            if (!a((!c0231j1.z0()) + i3).c()) {
                C0304t5 c0304t5 = aVar.g;
                c0304t5.getClass();
                if (!C0304t5.e && i3 < 0) {
                    x1f.a();
                    return;
                } else if (i3 < c0304t5.c) {
                    i2++;
                } else {
                    arrayList.add(aVar.g.j(i3));
                }
            }
        }
        if (arrayList.isEmpty()) {
            aVar.g = C0304t5.d;
            return;
        }
        if (!b()) {
            i = i2;
            list = arrayList;
        } else {
            if (i2 > 0) {
                aVar.g = C0304t5.d;
                return;
            }
            List listAsList = Arrays.asList(new C0306u0[c0231j1.c1().size()]);
            for (int i4 = i2; i4 < c0231j1.c1().size(); i4++) {
                listAsList.set(this.c.a((!c0231j1.z0()) + i4) - (!c0231j1.z0() ? 1 : 0), (C0306u0) arrayList.get(i4 - i2));
            }
            list = listAsList;
        }
        aVar.g = C0304t5.a((C0306u0[]) list.toArray(C0306u0.f), i);
    }
}
