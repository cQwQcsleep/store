package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.shaking.AbstractC3395g1;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.g1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3395g1 {
    public final boolean a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final boolean h;

    public AbstractC3395g1(AbstractC3385e1 abstractC3385e1) {
        boolean z = abstractC3385e1.b;
        boolean z2 = abstractC3385e1.c;
        boolean z3 = abstractC3385e1.d;
        boolean z4 = abstractC3385e1.e;
        boolean z5 = abstractC3385e1.f;
        boolean z6 = abstractC3385e1.g;
        boolean z7 = abstractC3385e1.h;
        boolean z8 = abstractC3385e1.i;
        this.a = z;
        this.b = z2;
        this.c = z3;
        this.d = z4;
        this.e = z5;
        this.f = z6;
        this.g = z7;
        this.h = z8;
    }

    public final boolean a(L0 l0) {
        C2752uB c2752uB = (C2752uB) l0;
        if (c2752uB.h.f().c) {
            return (c2752uB.V0 || d(l0)) ? false : true;
        }
        return true;
    }

    public boolean b(L0 l0) {
        return ((C2752uB) l0).c0() && this.d;
    }

    public boolean c(L0 l0) {
        return ((C2752uB) l0).e0() && this.e;
    }

    public final boolean d(L0 l0) {
        return (c(l0) && e(l0)) ? false : true;
    }

    public boolean e(L0 l0) {
        return ((C2752uB) l0).g0() && this.f;
    }

    public final boolean f(L0 l0) {
        C2752uB c2752uB = (C2752uB) l0;
        if (c2752uB.b0()) {
            return !c2752uB.V0 && this.g;
        }
        return true;
    }

    public static AbstractC3390f1 a(C0322w2 c0322w2) {
        return C3435o1.x.b();
    }

    public static AbstractC3390f1 a(com.android.tools.r8.graph.I2 i2) {
        return Y0.q.a();
    }

    public static AbstractC3390f1 a(com.android.tools.r8.graph.F2 f2) {
        return (AbstractC3390f1) f2.a(new Function() { // from class: iwg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC3395g1.a((I2) obj);
            }
        }, new Function() { // from class: kwg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC3395g1.a((C0245l1) obj);
            }
        }, new Function() { // from class: mwg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC3395g1.a((C0322w2) obj);
            }
        });
    }

    public static AbstractC3390f1 a(C0245l1 c0245l1) {
        return C3380d1.m.a();
    }
}
