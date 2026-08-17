package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.g6, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1547g6 extends AbstractC0890Uw {
    public static final /* synthetic */ boolean j = true;
    public final US i;

    public AbstractC1547g6(US us, C2543rl0 c2543rl0, C2543rl0 c2543rl1, C2543rl0 c2543rl2) {
        super(c2543rl0);
        this.i = us;
        b(c2543rl1);
        b(c2543rl2);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public int F2() {
        return N2();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public int G2() {
        return N2();
    }

    public US K2() {
        return this.i;
    }

    public abstract boolean L2();

    public final C2543rl0 M2() {
        return (C2543rl0) this.c.get(0);
    }

    public int N2() {
        return (!f(P2()) || P2().k().F().R2()) ? 255 : 15;
    }

    public final void O2() {
        if (!j && !L2()) {
            x1f.a();
            return;
        }
        if (L2() && !P2().H() && ((C2543rl0) this.c.get(0)).H()) {
            C2543rl0 c2543rl0 = (C2543rl0) this.c.get(0);
            ArrayList arrayList = this.c;
            arrayList.set(0, (C2543rl0) arrayList.get(1));
            this.c.set(1, c2543rl0);
        }
    }

    public C2543rl0 P2() {
        return (C2543rl0) this.c.get(1);
    }

    public abstract double a(double d, double d2);

    public abstract float a(float f, float f2);

    public abstract int a(int i, int i2);

    public abstract long a(long j2, long j3);

    public abstract B1 a(C0333y c0333y, B1 b1, B1 b2);

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(com.android.tools.r8.ir.regalloc.f fVar) {
        if (P2().T() && ((C2543rl0) this.c.get(0)).T()) {
            int iA = fVar.a((C2543rl0) this.c.get(0), this.e);
            int iA2 = fVar.a(P2(), this.e);
            int iA3 = fVar.a(this.b, this.e);
            if ((iA == iA3 || (L2() && iA2 == iA3)) && iA <= 15 && iA2 <= 15) {
                C2752uB c2752uBC = fVar.c();
                c2752uBC.getClass();
                if (!c2752uBC.a(EnumC3077y2.y) || !(this instanceof C3023xP)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean d(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        return false;
    }

    public boolean f(C2543rl0 c2543rl0) {
        return this.i == US.e && c2543rl0.J() && QS.a(c2543rl0.k().F().j);
    }

    public final boolean g(C2543rl0 c2543rl0) {
        return this.i == US.e && c2543rl0.J() && c2543rl0.k().F().R2();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean t1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final AbstractC1547g6 y() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.N a(com.android.tools.r8.ir.optimize.W w, com.android.tools.r8.graph.B5 b5) {
        return com.android.tools.r8.ir.optimize.N.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
        il.a(this, k5);
        il.b(this, k5);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public AbstractC2624sj0 a(C0333y c0333y) {
        return AbstractC2005lY.a(this.i);
    }
}
