package com.android.tools.r8.graph;

import com.android.tools.r8.dex.code.InterfaceC0022c;
import com.android.tools.r8.internal.AS;
import com.android.tools.r8.internal.AbstractC2004lX;
import com.android.tools.r8.internal.AbstractC2166nO;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C0705Nt;
import com.android.tools.r8.internal.C0867Tz;
import com.android.tools.r8.internal.C1041a8;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.internal.C1749iX;
import com.android.tools.r8.internal.C3047xh;
import com.android.tools.r8.internal.InterfaceC2045lz;
import com.android.tools.r8.internal.Kk0;
import com.android.tools.r8.internal.SK;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.graph.i0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0223i0 extends E {
    public static final /* synthetic */ boolean d = true;

    public static AbstractC2004lX a(AbstractC2004lX abstractC2004lX, AbstractC2004lX abstractC2004lX2, boolean z) {
        com.android.tools.r8.utils.structural.x xVar;
        AbstractC2004lX abstractC2004lXH = abstractC2004lX2.h();
        boolean z2 = d;
        if (!z2 && abstractC2004lXH.f != z) {
            x1f.a();
            return null;
        }
        if (!z) {
            if (z2 || !abstractC2004lXH.p()) {
                return abstractC2004lX2.a(abstractC2004lX);
            }
            x1f.a();
            return null;
        }
        if (!abstractC2004lXH.p() && !abstractC2004lXH.e) {
            return abstractC2004lX2.a(abstractC2004lXH, abstractC2004lX);
        }
        abstractC2004lX.getClass();
        boolean z3 = abstractC2004lX instanceof C1749iX;
        if (!z3 || !abstractC2004lXH.p()) {
            if (!z2 && abstractC2004lX.p()) {
                x1f.a();
                return null;
            }
            AbstractC2004lX.a aVarA = abstractC2004lXH.b().a(abstractC2004lX.c);
            if (abstractC2004lX.k()) {
                aVarA.c = abstractC2004lX.d;
            }
            if (!abstractC2004lXH.p()) {
                aVarA.a(abstractC2004lXH.f());
            } else {
                if (!z2 && z3) {
                    x1f.a();
                    return null;
                }
                if (!z2 && !abstractC2004lX.f) {
                    x1f.a();
                    return null;
                }
                if (!z2 && abstractC2004lX.f() != 0) {
                    x1f.a();
                    return null;
                }
            }
            if (abstractC2004lX.e) {
                aVarA.d = true;
            }
            return abstractC2004lX2.a(abstractC2004lXH, aVarA.a());
        }
        C0867Tz c0867Tz = abstractC2004lX.a().h;
        int iF = abstractC2004lXH.f();
        AbstractC2004lX abstractC2004lXA = null;
        for (int i = 0; i <= iF; i++) {
            int i2 = 0;
            while (true) {
                int[] iArr = c0867Tz.b;
                if (i2 >= iArr.length) {
                    xVar = null;
                    break;
                }
                if (iArr[i2] == i) {
                    xVar = (com.android.tools.r8.utils.structural.x) c0867Tz.c.get(i2);
                    break;
                }
                i2++;
            }
            AbstractC2004lX abstractC2004lX3 = (AbstractC2004lX) xVar;
            if (abstractC2004lX3 != null) {
                abstractC2004lXA = abstractC2004lX3;
            }
        }
        if (!d && abstractC2004lXA == null) {
            x1f.a();
            return null;
        }
        if (abstractC2004lX.k()) {
            abstractC2004lXA = abstractC2004lXA.a(abstractC2004lX.d);
        }
        return abstractC2004lX2.k() ? abstractC2004lX2.a(abstractC2004lXH, abstractC2004lXA) : abstractC2004lXA;
    }

    public abstract boolean A0();

    public boolean B0() {
        return false;
    }

    public boolean C0() {
        return false;
    }

    public final boolean D0() {
        return r0() != null;
    }

    public boolean E0() {
        return this instanceof C0244l0;
    }

    public boolean F0() {
        return this instanceof C0244l0;
    }

    public void G0() {
    }

    public G H() {
        throw new Kk0(getClass().getCanonicalName() + ".asCfCode()");
    }

    public J0 N() {
        return null;
    }

    public abstract C0705Nt a(B5 b5, C0333y c0333y, AbstractC2166nO.a aVar);

    public abstract String a(C0231j1 c0231j1, C1581ga0 c1581ga0);

    public abstract void a(B5 b5, Z5 z5);

    public abstract void a(C0195e0 c0195e0, C3047xh c3047xh);

    public int k(int i) {
        throw new Kk0(getClass().getTypeName());
    }

    public P o0() {
        throw new Kk0(getClass().getCanonicalName() + ".asCfWritableCode()");
    }

    public InterfaceC0170a3 p0() {
        throw new Kk0(getClass().getCanonicalName() + ".asDexWritableCode()");
    }

    public C0269o4 q0() {
        throw new Kk0(getClass().getCanonicalName() + ".asLazyCfCode()");
    }

    public SK r0() {
        return null;
    }

    public void s0() {
    }

    public abstract int t0();

    public abstract String toString();

    public U5 u() {
        return null;
    }

    public boolean u0() {
        return this instanceof J;
    }

    public boolean v0() {
        return false;
    }

    public boolean w0() {
        return false;
    }

    public boolean x0() {
        return this instanceof C0244l0;
    }

    public boolean y0() {
        return false;
    }

    public boolean z0() {
        return this instanceof C0244l0;
    }

    public C1041a8 a(InterfaceC0022c interfaceC0022c) {
        return null;
    }

    public void a(C0322w2 c0322w2, boolean z, Consumer consumer) {
    }

    public C0705Nt a(B5 b5, B5 b6, C0333y c0333y, AbstractC3148ys abstractC3148ys, AS as, AbstractC2004lX abstractC2004lX, com.android.tools.r8.graph.proto.j jVar) {
        throw new Kk0("Unexpected attempt to build IR graph for inlining from: " + getClass().getCanonicalName());
    }

    public InterfaceC2045lz a(C0333y c0333y, C0231j1 c0231j1) {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public void a(com.android.tools.r8.dex.X x) {
        throw new Kk0();
    }

    public AbstractC0223i0 a(C0322w2 c0322w2, boolean z, C0322w2 c0322w3, boolean z2, B1 b1) {
        throw new Kk0();
    }

    public AbstractC3148ys a(C0333y c0333y) {
        return c0333y.g;
    }
}
