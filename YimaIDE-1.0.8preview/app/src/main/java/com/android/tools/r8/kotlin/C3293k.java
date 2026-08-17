package com.android.tools.r8.kotlin;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.internal.FH;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.kotlin.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3293k extends AbstractC3295m {
    public final u0 b;
    public final String c;

    public C3293k(u0 u0Var, String str) {
        this.b = u0Var;
        this.c = str;
    }

    public final /* synthetic */ void a(Consumer consumer, String str) {
        consumer.accept(new FH(str, this.c));
    }

    @Override // com.android.tools.r8.kotlin.AbstractC3295m
    public final boolean b(final Consumer consumer, C0333y c0333y) {
        return this.b.a(new Consumer() { // from class: oeh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(consumer, (String) obj);
            }
        }, c0333y, "kotlin/Any");
    }

    @Override // com.android.tools.r8.shaking.InterfaceC3369b0
    public final void a(InterfaceC0189d1 interfaceC0189d1) {
        this.b.a(interfaceC0189d1);
    }
}
