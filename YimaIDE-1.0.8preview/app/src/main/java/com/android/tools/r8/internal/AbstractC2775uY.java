package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.uY, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2775uY {
    public static AbstractC2775uY b(C0333y c0333y) {
        AbstractC1543g4 abstractC1543g4 = c0333y.d;
        Vd0 vd0 = c0333y.q;
        return (abstractC1543g4.isEmpty() && vd0.a()) ? c() : new C2274of(abstractC1543g4, vd0);
    }

    public static C1233cS c() {
        return C1233cS.a;
    }

    public C2274of a() {
        return null;
    }

    public abstract AbstractC2775uY a(Vd0 vd0);

    public abstract AbstractC2775uY a(AbstractC1543g4 abstractC1543g4);

    public abstract AbstractC2775uY a(Function function);

    public abstract void a(com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.B5 b6);

    public abstract void a(C0322w2 c0322w2, Consumer consumer);

    public abstract void a(C0333y c0333y);

    public abstract void d();

    public boolean b() {
        return this instanceof C1233cS;
    }
}
