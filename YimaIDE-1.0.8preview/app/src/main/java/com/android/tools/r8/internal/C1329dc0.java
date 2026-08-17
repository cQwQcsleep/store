package com.android.tools.r8.internal;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0333y;
import java.util.function.BiConsumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dc0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1329dc0 extends AbstractC1636h8 {
    public C1329dc0(C0333y c0333y) {
        super(c0333y);
    }

    public final void a(com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.B5 b6) {
        C1414ec0 c1414ec0 = (C1414ec0) b(b5);
        C1414ec0 c1414ec1 = (C1414ec0) b(b6);
        if (!C1414ec0.e && c1414ec0.c != null) {
            x1f.a();
        } else if (c1414ec0 == c1414ec1) {
            c1414ec0.getClass();
        } else {
            c1414ec0.c = c1414ec1;
            c1414ec1.d.add(c1414ec0);
        }
    }

    public final C1329dc0 a(PY py) {
        py.forEach(new BiConsumer() { // from class: smg
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a((B5) obj, (B5) obj2);
            }
        });
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1636h8
    public final JR a(com.android.tools.r8.graph.B5 b5) {
        return new C1414ec0(b5);
    }
}
