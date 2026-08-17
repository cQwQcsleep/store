package com.android.tools.r8.kotlin;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.internal.C3100yI;
import com.android.tools.r8.internal.GI;
import com.android.tools.r8.internal.KI;
import com.android.tools.r8.shaking.InterfaceC3369b0;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class t0 implements InterfaceC3369b0 {
    public final KI a;
    public final r0 b;

    public t0(KI ki, r0 r0Var) {
        this.a = ki;
        this.b = r0Var;
    }

    public final /* synthetic */ void a(Consumer consumer, C3100yI c3100yI) {
        consumer.accept(new GI(this.a, c3100yI));
    }

    public final boolean b(final Consumer consumer, C0333y c0333y) {
        if (this.a != null || this.b != null) {
            return this.b.b(new Consumer() { // from class: qbi
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a(consumer, (C3100yI) obj);
                }
            }, c0333y);
        }
        consumer.accept(GI.c);
        return false;
    }

    @Override // com.android.tools.r8.shaking.InterfaceC3369b0
    public final void a(InterfaceC0189d1 interfaceC0189d1) {
        r0 r0Var = this.b;
        if (r0Var != null) {
            r0Var.a(interfaceC0189d1);
        }
    }
}
