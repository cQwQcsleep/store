package com.android.tools.r8.synthesis;

import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.B3;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0306u0;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.H4;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.J4;
import com.android.tools.r8.graph.K2;
import com.android.tools.r8.internal.C1574gU;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.synthesis.AbstractC3503l;
import com.sun.jna.platform.linux.Fcntl;
import defpackage.r26;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.synthesis.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3503l {
    public static final /* synthetic */ boolean s = true;
    public final B1 a;
    public final I2 b;
    public final S.b c;
    public final Origin d;
    public ProgramResource.Kind h;
    public I2 i;
    public boolean e = false;
    public boolean f = true;
    public boolean g = false;
    public K2 j = K2.n0();
    public H2 k = null;
    public boolean l = false;
    public final ArrayList m = new ArrayList();
    public final ArrayList n = new ArrayList();
    public final ArrayList o = new ArrayList();
    public final ArrayList p = new ArrayList();
    public final ArrayList q = new ArrayList();
    public final B3.b r = B3.b.f();

    public AbstractC3503l(I2 i2, S.b bVar, C3502k c3502k, B1 b1) {
        this.a = b1;
        this.b = i2;
        this.c = bVar;
        this.d = c3502k.d;
        this.i = b1.a2;
    }

    public final E0 a() {
        boolean z = this.e;
        int i = z ? Fcntl.S_ISGID : 0;
        int i2 = this.f ? 16 : 0;
        boolean z2 = this.g;
        int i3 = z2 ? 512 : 0;
        if (!s && z2 && !z) {
            x1f.a();
            return null;
        }
        com.android.tools.r8.graph.Q qG = com.android.tools.r8.graph.Q.g(i | i2 | i3 | 4097);
        List list = Collections.EMPTY_LIST;
        Iterator it = this.q.iterator();
        while (it.hasNext()) {
            C0231j1 c0231j1A = ((N) it.next()).a(b());
            if (c0231j1A.u1()) {
                this.p.add(c0231j1A);
            } else {
                this.o.add(c0231j1A);
            }
        }
        final long jHashCode = (((long) this.n.hashCode()) * 17) + (((long) this.m.hashCode()) * 13) + (((long) this.p.hashCode()) * 11) + (((long) this.o.hashCode()) * 7);
        com.android.tools.r8.graph.V vB = b();
        I2 i4 = this.b;
        ProgramResource.Kind kind = this.h;
        Origin origin = this.d;
        I2 i5 = this.i;
        K2 k2 = this.j;
        H2 h2 = this.k;
        B3.b bVar = this.r;
        C0306u0 c0306u0O0 = C0306u0.o0();
        ArrayList arrayList = this.m;
        C0210g1[] c0210g1Arr = C0210g1.o;
        C0210g1[] c0210g1Arr2 = (C0210g1[]) arrayList.toArray(c0210g1Arr);
        C0210g1[] c0210g1Arr3 = (C0210g1[]) this.n.toArray(c0210g1Arr);
        C0231j1[] c0231j1Arr = C0231j1.u;
        E0 e0A = vB.a.a(i4, kind, origin, qG, i5, k2, h2, null, list, list, list, null, list, bVar, c0306u0O0, c0210g1Arr2, c0210g1Arr3, c0231j1Arr, c0231j1Arr, this.a.d6, new D2.a() { // from class: kih
            @Override // com.android.tools.r8.graph.D2.a
            public final long a(D2 d2) {
                return AbstractC3503l.a(jHashCode, d2);
            }
        }, null);
        if (this.l) {
            H4 h4V = e0A.V();
            if (H4.d) {
                h4V.getClass();
            } else if (h4V.b.h() != 0) {
                x1f.a();
                return null;
            }
            h4V.b = new J4(true, new C1574gU());
        }
        e0A.l.a((C0231j1[]) this.o.toArray(c0231j1Arr));
        e0A.a((C0231j1[]) this.p.toArray(c0231j1Arr));
        return e0A;
    }

    public abstract com.android.tools.r8.graph.V b();

    public final void b(AbstractCollection abstractCollection) {
        this.p.clear();
        ArrayList arrayList = this.p;
        Objects.requireNonNull(arrayList);
        abstractCollection.forEach(new r26(arrayList));
    }

    public abstract AbstractC3503l c();

    public final AbstractC3503l d() {
        this.e = true;
        this.f = false;
        this.g = true;
        return c();
    }

    public final AbstractC3503l a(List list) {
        K2 k2;
        if (list.isEmpty()) {
            k2 = K2.n0();
        } else {
            k2 = new K2((I2[]) list.toArray(I2.h));
        }
        this.j = k2;
        return c();
    }

    public final AbstractC3503l a(AbstractCollection abstractCollection) {
        this.o.clear();
        ArrayList arrayList = this.o;
        Objects.requireNonNull(arrayList);
        abstractCollection.forEach(new r26(arrayList));
        return c();
    }

    public final AbstractC3503l a(Consumer consumer) {
        N n = new N(this);
        consumer.accept(n);
        this.q.add(n);
        return c();
    }

    public static /* synthetic */ long a(long j, D2 d2) {
        return j;
    }
}
