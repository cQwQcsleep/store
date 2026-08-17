package com.android.tools.r8.ir.optimize.info;

import com.android.tools.r8.graph.AbstractC0217h1;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0231j1;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface x {
    void a(C0210g1 c0210g1, v vVar);

    default void a(AbstractC0217h1 abstractC0217h1) {
        final g gVarG0 = abstractC0217h1.G0();
        if (gVarG0.d()) {
            abstractC0217h1.a(new Consumer() { // from class: gqi
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a(gVarG0, (C0210g1) obj);
                }
            }, new Consumer() { // from class: hqi
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a(gVarG0, (C0231j1) obj);
                }
            });
        }
    }

    void a(C0231j1 c0231j1, w wVar);

    /* synthetic */ default void a(g gVar, C0210g1 c0210g1) {
        a(c0210g1, gVar.c());
    }

    /* synthetic */ default void a(g gVar, C0231j1 c0231j1) {
        a(c0231j1, gVar.a());
    }
}
