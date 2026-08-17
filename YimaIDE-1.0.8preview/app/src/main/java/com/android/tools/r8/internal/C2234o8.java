package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0223i0;
import com.android.tools.r8.graph.C0322w2;
import java.util.HashMap;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.o8, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2234o8 {
    public static final /* synthetic */ boolean f = true;
    public final AbstractC2004lX a;
    public final HashMap b;
    public final AbstractC2004lX c;
    public final boolean d;
    public AbstractC2004lX e;

    public C2234o8(AbstractC2004lX abstractC2004lX, int i, C0322w2 c0322w2, boolean z, AbstractC2004lX abstractC2004lX2) {
        this.b = new HashMap((abstractC2004lX == null ? 0 : 1) + 1 + i);
        if (abstractC2004lX2 == null) {
            AbstractC2004lX.c.a aVarA = AbstractC2004lX.c.s().a(0).a(c0322w2);
            aVarA.e = z;
            abstractC2004lX2 = aVarA.a();
        }
        if (abstractC2004lX != null) {
            this.a = c(abstractC2004lX);
            this.d = z;
            this.c = c(AbstractC0223i0.a(abstractC2004lX, abstractC2004lX2, z));
        } else {
            this.a = null;
            this.d = false;
            this.c = c(abstractC2004lX2);
        }
    }

    public final AbstractC2004lX a(boolean z, Supplier supplier, C0322w2 c0322w2) {
        AbstractC2004lX abstractC2004lXA;
        if (this.e == null) {
            if (z) {
                int iMax = Integer.MIN_VALUE;
                int iMin = Integer.MAX_VALUE;
                for (AbstractC2004lX abstractC2004lX : (Iterable) supplier.get()) {
                    if (!f && abstractC2004lX != abstractC2004lX.h()) {
                        x1f.a();
                        return null;
                    }
                    int i = abstractC2004lX.b;
                    iMin = Math.min(iMin, i);
                    iMax = Math.max(iMax, i);
                }
                if (iMin == Integer.MAX_VALUE) {
                    abstractC2004lXA = this.c;
                } else {
                    AbstractC2004lX.c.a aVarS = AbstractC2004lX.c.s();
                    if (iMin < iMax) {
                        iMin--;
                    }
                    AbstractC2004lX.c.a aVarA = aVarS.a(iMin).a(c0322w2);
                    aVarA.c = this.a;
                    abstractC2004lXA = aVarA.a();
                }
                this.e = abstractC2004lXA;
            } else {
                boolean z2 = AbstractC2004lX.g;
                this.e = AbstractC2004lX.c.h;
            }
        }
        return this.e;
    }

    public final AbstractC2004lX b(AbstractC2004lX abstractC2004lX) {
        if (!abstractC2004lX.f || this.a == null) {
            AbstractC2004lX.a aVarB = abstractC2004lX.b();
            aVarB.c = a(abstractC2004lX.d);
            return c(aVarB.c().a());
        }
        if (f || !abstractC2004lX.k()) {
            return c(AbstractC0223i0.a(this.a, abstractC2004lX, true));
        }
        x1f.a();
        return null;
    }

    public final AbstractC2004lX c(AbstractC2004lX abstractC2004lX) {
        AbstractC2004lX abstractC2004lX2 = (AbstractC2004lX) this.b.putIfAbsent(abstractC2004lX, abstractC2004lX);
        return abstractC2004lX2 != null ? abstractC2004lX2 : abstractC2004lX;
    }

    public final AbstractC2004lX a(AbstractC2004lX abstractC2004lX) {
        AbstractC2004lX abstractC2004lXA;
        if (abstractC2004lX == null) {
            return this.a;
        }
        AbstractC2004lX abstractC2004lX2 = abstractC2004lX.d;
        if (abstractC2004lX2 == null && this.a == null) {
            return c(abstractC2004lX);
        }
        if (abstractC2004lX2 == null && this.d) {
            return this.a;
        }
        AbstractC2004lX abstractC2004lXA2 = a(abstractC2004lX2);
        if (abstractC2004lX.n()) {
            AbstractC2004lX.b.a aVarA = AbstractC2004lX.b.s().a(abstractC2004lX.c);
            aVarA.c = abstractC2004lXA2;
            aVarA.f = true;
            abstractC2004lXA = aVarA.a();
        } else {
            AbstractC2004lX.a aVarB = abstractC2004lX.b();
            aVarB.c = abstractC2004lXA2;
            abstractC2004lXA = aVarB.c().a();
        }
        return c(abstractC2004lXA);
    }
}
