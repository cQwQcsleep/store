package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.internal.InterfaceC2134n1;
import java.util.Comparator;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Wd0 extends AbstractC2689tY {
    public Wd0(Vd0 vd0) {
        super(vd0);
    }

    @Override // com.android.tools.r8.internal.AbstractC2689tY
    public final AbstractC2689tY a() {
        return new Wd0((Vd0) this.a);
    }

    @Override // com.android.tools.r8.internal.AbstractC2689tY
    public final InterfaceC1792j1 b(com.android.tools.r8.graph.I2 i2) {
        Xd0 xd0 = new Xd0();
        xd0.b = i2;
        return xd0;
    }

    @Override // com.android.tools.r8.internal.AbstractC2689tY
    public final InterfaceC1622h1 c() {
        return new Vd0.a();
    }

    @Override // com.android.tools.r8.internal.AbstractC2689tY
    public final Comparator d() {
        return Comparator.comparing(new Function() { // from class: wkf
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((InterfaceC2134n1) obj).b();
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC2689tY
    public final AbstractC2689tY f() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2689tY
    public final InterfaceC1963l1 a(C0322w2 c0322w2) {
        Zd0 zd0 = new Zd0();
        zd0.b = c0322w2;
        return zd0;
    }
}
