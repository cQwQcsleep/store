package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC2173nV;
import com.android.tools.r8.internal.Kk0;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.graph.a5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0172a5 extends T4 {
    public final T4.c b;
    public final List c;
    public final List d;
    public final List e;

    public AbstractC0172a5(T4.c cVar, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3) {
        this.b = cVar;
        this.c = arrayList;
        this.d = arrayList2;
        this.e = arrayList3;
    }

    @Override // com.android.tools.r8.graph.T4
    public final void a(Consumer consumer, Consumer consumer2, Consumer consumer3, Consumer consumer4) {
        T4.c cVar = this.b;
        if (cVar != null) {
            consumer.accept(cVar);
        }
        List list = this.c;
        if (list != null) {
            list.forEach(consumer);
        }
        this.d.forEach(consumer2);
        this.e.forEach(consumer4);
    }

    @Override // com.android.tools.r8.graph.T4
    public final AbstractC2173nV b(InterfaceC0332x5 interfaceC0332x5, C0333y c0333y) {
        throw new Kk0("Should not be called on MultipleFieldResolutionResult");
    }

    @Override // com.android.tools.r8.graph.T4
    public final H0 c(D2 d2, C0333y c0333y, C0229j c0229j) {
        throw new Kk0("Should not be called on MultipleFieldResolutionResult");
    }

    @Override // com.android.tools.r8.graph.T4
    public final boolean v() {
        return true;
    }

    @Override // com.android.tools.r8.graph.T4
    public final boolean x() {
        throw new Kk0("Should not be called on MultipleFieldResolutionResult");
    }

    @Override // com.android.tools.r8.graph.T4
    public final H0 b(D2 d2, C0333y c0333y, C0229j c0229j) {
        throw new Kk0("Should not be called on MultipleFieldResolutionResult");
    }

    @Override // com.android.tools.r8.graph.T4
    public final H0 a(D2 d2, C0333y c0333y) {
        throw new Kk0("Should not be called on MultipleFieldResolutionResult");
    }

    @Override // com.android.tools.r8.graph.T4
    public final H0 a(D2 d2, C0333y c0333y, C0229j c0229j) {
        throw new Kk0("Should not be called on MultipleFieldResolutionResult");
    }

    @Override // com.android.tools.r8.graph.T4
    public final B4 a(D2 d2, C0333y c0333y, Z3 z3, InterfaceC0318v5 interfaceC0318v5) {
        throw new Kk0("Should not be called on MultipleFieldResolutionResult");
    }

    @Override // com.android.tools.r8.graph.T4
    public final B4 a(D2 d2, C0333y c0333y, D2 d3, D2 d4) {
        throw new Kk0("Should not be called on MultipleFieldResolutionResult");
    }

    @Override // com.android.tools.r8.graph.T4
    public final C4 a(com.android.tools.r8.shaking.V0 v0, C0229j c0229j) {
        throw new Kk0("Should not be called on MultipleFieldResolutionResult");
    }

    @Override // com.android.tools.r8.graph.T4
    public final InterfaceC0331x4 a(E0 e0, C0229j c0229j) {
        throw new Kk0("Should not be called on MultipleFieldResolutionResult");
    }

    @Override // com.android.tools.r8.graph.D4
    public final AbstractC2173nV a(InterfaceC0332x5 interfaceC0332x5, C0333y c0333y, C0229j c0229j) {
        throw new Kk0("Should not be called on MultipleFieldResolutionResult");
    }
}
