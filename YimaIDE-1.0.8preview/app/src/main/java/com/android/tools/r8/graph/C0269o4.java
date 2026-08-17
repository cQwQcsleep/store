package com.android.tools.r8.graph;

import com.android.tools.r8.ClassFileConsumer;
import com.android.tools.r8.internal.AS;
import com.android.tools.r8.internal.AbstractC2004lX;
import com.android.tools.r8.internal.AbstractC2166nO;
import com.android.tools.r8.internal.AbstractC2632so;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C0705Nt;
import com.android.tools.r8.internal.C1345dk0;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.internal.C1586gd;
import com.android.tools.r8.internal.C2520ra;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C3047xh;
import com.android.tools.r8.internal.InterfaceC2045lz;
import com.android.tools.r8.internal.Kk0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.shaking.C3417k3;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.graph.o4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0269o4 extends AbstractC0223i0 {
    public static final /* synthetic */ boolean j = true;
    public final Origin e;
    public C0178b4 f;
    public G g;
    public C0220h4 h;
    public boolean i = false;

    public C0269o4(Origin origin, C0220h4 c0220h4, C0178b4 c0178b4) {
        this.e = origin;
        this.h = c0220h4;
        this.f = c0178b4;
        c0220h4.c.add(this);
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final boolean A0() {
        return H().A0();
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final void G0() {
        if (j) {
            return;
        }
        if (this.h == null && this.f == null) {
            return;
        }
        x1f.a();
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0, com.android.tools.r8.graph.P
    public final G H() {
        if (this.g == null) {
            AbstractC2632so.a(this.e, Position.UNKNOWN, new Runnable() { // from class: lxh
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.H0();
                }
            });
        }
        if (j || this.g != null) {
            return this.g;
        }
        x1f.a();
        return null;
    }

    public final void H0() {
        C0220h4 c0220h4 = this.h;
        C0178b4 c0178b4 = this.f;
        boolean z = j;
        if (!z && c0178b4 == null) {
            x1f.a();
            return;
        }
        if (!z && c0220h4 == null) {
            x1f.a();
            return;
        }
        try {
            a(c0220h4, false);
        } catch (C0255m4 unused) {
            for (AbstractC0223i0 abstractC0223i0 : c0220h4.c) {
                abstractC0223i0.q0().g = null;
                abstractC0223i0.q0().h = c0220h4;
                abstractC0223i0.q0().f = c0178b4;
            }
            try {
                a(c0220h4, true);
            } catch (C0255m4 e) {
                throw new Kk0(e);
            }
        } catch (Exception e2) {
            throw new C0613Ke(this.e, "Could not parse code", e2);
        }
        if (j) {
            return;
        }
        E0 e0 = c0220h4.b;
        Iterator<C0231j1> it = e0.G1().iterator();
        while (it.hasNext()) {
            AbstractC0223i0 abstractC0223i0U0 = it.next().U0();
            if (!j && abstractC0223i0U0 != null) {
                abstractC0223i0U0.G0();
            }
        }
        Iterator<C0231j1> it2 = e0.L0().iterator();
        while (it2.hasNext()) {
            AbstractC0223i0 abstractC0223i0U1 = it2.next().U0();
            if (!j && abstractC0223i0U1 != null) {
                abstractC0223i0U1.G0();
            }
        }
    }

    public final void a(C0220h4 c0220h4, boolean z) {
        C0241k4 c0241k4;
        C0178b4 c0178b4 = this.f;
        boolean z2 = this.i;
        C2752uB c2752uB = c0178b4.a;
        boolean z3 = c2752uB.u1.L0;
        if (!z3) {
            z3 = c2752uB.j instanceof ClassFileConsumer;
        }
        int i = z3 ? 8 : 4;
        com.android.tools.r8.shaking.R2 r2H = c2752uB.H();
        boolean z4 = true;
        if (r2H == null) {
            c0241k4 = new C0241k4(true, true, i);
        } else {
            C3417k3 c3417k3F = c0178b4.a.H().f();
            boolean z5 = r2H.v() || c3417k3F.h || c3417k3F.i || z2;
            if (!c3417k3F.g && !c0178b4.a.k()) {
                z4 = false;
            }
            boolean z6 = c3417k3F.j;
            if (!z5 && !z4 && !z6) {
                i |= 2;
            }
            c0241k4 = new C0241k4(z4, z5, i);
        }
        C0241k4 c0241k5 = c0241k4;
        new C1586gd(c0220h4.a).a(new C0234j4(c0220h4.b, new C0248l4(c0220h4), this.f, z, this.e, c0241k5), new com.android.tools.r8.internal.H4[0], c0241k5.c);
    }

    @Override // com.android.tools.r8.graph.E
    public final boolean c(Object obj) {
        throw new C1345dk0();
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final int k(int i) {
        return H().k(i);
    }

    @Override // com.android.tools.r8.graph.E
    public final int n0() {
        throw new C1345dk0();
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final P o0() {
        return H();
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final C0269o4 q0() {
        return this;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final int t0() {
        return H().t0();
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final String toString() {
        return H().toString();
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final boolean v0() {
        return H().v0();
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final boolean w0() {
        return true;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final boolean x0() {
        return true;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final AbstractC0223i0 a(C0322w2 c0322w2, boolean z, C0322w2 c0322w3, boolean z2, B1 b1) {
        return H().a(c0322w2, z, c0322w3, z2, b1);
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final C0705Nt a(B5 b5, C0333y c0333y, AbstractC2166nO.a aVar) {
        return H().a(b5, c0333y, aVar);
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final C0705Nt a(B5 b5, B5 b6, C0333y c0333y, AbstractC3148ys abstractC3148ys, AS as, AbstractC2004lX abstractC2004lX, com.android.tools.r8.graph.proto.j jVar) {
        return H().a(b5, b6, c0333y, abstractC3148ys, as, abstractC2004lX, jVar);
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final void a(B5 b5, Z5 z5) {
        H().a(b5, z5);
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final void a(C0195e0 c0195e0, C3047xh c3047xh) {
        H().a(c0195e0, c3047xh);
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final String a(C0231j1 c0231j1, C1581ga0 c1581ga0) {
        G gH = H();
        gH.getClass();
        return new C2520ra(gH, c0231j1, c1581ga0).toString();
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final InterfaceC2045lz a(C0333y c0333y, C0231j1 c0231j1) {
        return H().a(c0333y, c0231j1);
    }
}
