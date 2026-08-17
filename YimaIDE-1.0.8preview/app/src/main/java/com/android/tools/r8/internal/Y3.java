package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.internal.InterfaceC2134n1;
import java.util.Comparator;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Y3 extends AbstractC2689tY {
    public Y3(X3 x3) {
        super(x3);
    }

    @Override // com.android.tools.r8.internal.AbstractC2689tY
    public final AbstractC2689tY a() {
        return new Y3((X3) this.a);
    }

    @Override // com.android.tools.r8.internal.AbstractC2689tY
    public final InterfaceC1792j1 b(com.android.tools.r8.graph.I2 i2) {
        C1287d4 c1287d4 = new C1287d4();
        c1287d4.b = i2;
        return c1287d4;
    }

    @Override // com.android.tools.r8.internal.AbstractC2689tY
    public final InterfaceC1622h1 c() {
        boolean z = X3.b;
        return new X3.a();
    }

    @Override // com.android.tools.r8.internal.AbstractC2689tY
    public final Comparator d() {
        return Comparator.comparing(new Function() { // from class: u1g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((InterfaceC2134n1) obj).a();
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC2689tY
    public final AbstractC2689tY f() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2689tY
    public final InterfaceC1963l1 a(C0322w2 c0322w2) {
        C2055m4.a aVarD = C2055m4.d();
        aVarD.b = c0322w2;
        return aVarD;
    }
}
