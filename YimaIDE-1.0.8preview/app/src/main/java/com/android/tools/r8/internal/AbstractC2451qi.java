package com.android.tools.r8.internal;

import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qi, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2451qi extends AbstractC2621si {
    @Override // com.android.tools.r8.internal.AbstractC2621si
    public final AbstractC1597gi0 a(C2280oi c2280oi) {
        ((Oe0) this).f.add((AbstractC1932kf0) c2280oi.a);
        return C1512fi0.c;
    }

    public abstract void a(InterfaceC2194ni interfaceC2194ni, Function function);

    @Override // com.android.tools.r8.internal.AbstractC2621si
    public final AbstractC1597gi0 b(C2280oi c2280oi) {
        a(c2280oi, new Function() { // from class: a4i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.b(obj);
            }
        });
        return C1512fi0.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC2621si
    public final C2280oi a(Object obj) {
        return new C2280oi(obj);
    }
}
