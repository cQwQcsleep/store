package com.android.tools.r8.graph.proto;

import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.E2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC0493Fo;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.AbstractC3122yc0;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C0705Nt;
import com.android.tools.r8.internal.NJ;
import com.android.tools.r8.internal.Y6;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class j {
    public static final j d = new j();
    public static final /* synthetic */ boolean e = true;
    public final List a;
    public final c b;
    public final k c;

    public j(List list, k kVar, c cVar) {
        boolean z = e;
        if (!z && cVar == null) {
            x1f.a();
            throw null;
        }
        this.a = list;
        this.c = kVar;
        this.b = cVar;
        if (z || !g()) {
            return;
        }
        x1f.a();
        throw null;
    }

    public static j h() {
        return d;
    }

    public final C0322w2 a(B5 b5, B1 b1) {
        E2 e2A;
        if (g()) {
            return b5.getReference();
        }
        if (g()) {
            e2A = b5.getReference().C0();
        } else {
            k kVar = this.c;
            I2 i2F = kVar != null ? kVar.f() : b5.G();
            I2[] i2Arr = b5.E().b;
            if (!g()) {
                int length = i2Arr.length;
                c cVar = this.b;
                I2[] i2Arr2 = new I2[this.a.size() + (length - (c.a(Integer.MAX_VALUE, cVar.a) - Y6.a(!b5.e().z0() && cVar.a(0).c())))];
                int i = !b5.e().z0() ? 1 : 0;
                int i2 = 0;
                for (int i3 = 0; i3 < i2Arr.length; i3++) {
                    b bVarA = this.b.a(i3 + i);
                    bVarA.getClass();
                    if (bVarA instanceof a) {
                        i2Arr2[i2] = i2Arr[i3];
                        i2++;
                    } else if (bVarA instanceof k) {
                        k kVarB = bVarA.b();
                        if (!e && i2Arr[i3] != kVarB.g()) {
                            x1f.a();
                            return null;
                        }
                        i2Arr2[i2] = kVarB.f();
                        i2++;
                    } else {
                        continue;
                    }
                }
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    i2Arr2[i2] = ((AbstractC0493Fo) it.next()).a(b1);
                    i2++;
                }
                i2Arr = i2Arr2;
            }
            e2A = b1.a(i2F, i2Arr);
        }
        C0322w2 reference = b5.getReference();
        return b1.a(reference.f, e2A, reference.g);
    }

    public final List b() {
        return this.a;
    }

    public final k c() {
        return this.c;
    }

    public final boolean d() {
        k kVar = this.c;
        return kVar != null && kVar.d.W0();
    }

    public final boolean e() {
        return !this.a.isEmpty();
    }

    public final boolean equals(Object obj) {
        if (obj != null && j.class == obj.getClass()) {
            j jVar = (j) obj;
            if (this.a.equals(jVar.a) && Objects.equals(this.c, jVar.c) && this.b.equals(jVar.b)) {
                return true;
            }
        }
        return false;
    }

    public final boolean f() {
        return this.c != null;
    }

    public final boolean g() {
        return this.a.isEmpty() && this.c == null && this.b.c();
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.c, this.b);
    }

    public final boolean i() {
        return f() || this.a.size() > 0 || this.b.b() || c.a(Integer.MAX_VALUE, this.b.a) > 0;
    }

    public j() {
        this.a = Collections.EMPTY_LIST;
        this.c = null;
        this.b = c.f;
    }

    public final j a(j jVar) {
        k kVarA;
        if (g()) {
            return jVar;
        }
        if (jVar.g()) {
            return this;
        }
        AbstractC0551Hu abstractC0551HuA = AbstractC0551Hu.g().b((Iterable) this.a).b((Iterable) jVar.a).a();
        if (f()) {
            kVarA = this.c;
            kVarA.getClass();
            if (jVar.f()) {
                kVarA = kVarA.a(jVar.c);
            }
        } else {
            kVarA = jVar.c;
        }
        return new j(abstractC0551HuA, kVarA, this.b.a(jVar.b));
    }

    public final c a() {
        return this.b;
    }

    public final AbstractC0890Uw[] a(C0333y c0333y, C0705Nt c0705Nt, NJ nj) {
        boolean z = e;
        if (!z && this.c == null) {
            x1f.a();
            return null;
        }
        if (!z && this.c.e == null) {
            x1f.a();
            return null;
        }
        AbstractC3122yc0 abstractC3122yc0 = this.c.e;
        abstractC3122yc0.getClass();
        return abstractC3122yc0.a(c0333y, c0705Nt.i(), c0705Nt, nj);
    }

    public final void a(C0333y c0333y, B5 b5, AbstractC3148ys abstractC3148ys) {
        AbstractC3122yc0 abstractC3122yc0A = this.c.e.b(c0333y, b5.G(), c0333y.A(), abstractC3148ys);
        if (e || abstractC3122yc0A.b(c0333y, b5)) {
            return;
        }
        x1f.a();
    }

    public static j a(List list, k kVar, c cVar) {
        if (list.isEmpty() && kVar == null && cVar.c()) {
            return d;
        }
        return new j(list, kVar, cVar);
    }

    public final j a(List list) {
        if (list.isEmpty()) {
            return this;
        }
        ArrayList arrayList = new ArrayList(list.size() + this.a.size());
        arrayList.addAll(this.a);
        arrayList.addAll(list);
        return new j(arrayList, this.c, this.b);
    }
}
