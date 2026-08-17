package com.android.tools.r8.naming;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class C {
    public final C0333y b;
    public final Map c;

    public C(C0333y c0333y, IdentityHashMap identityHashMap) {
        this.b = c0333y;
        this.c = identityHashMap;
    }

    public abstract Object a();

    public final /* synthetic */ Object a(I2 i2) {
        return a();
    }

    public final Object b() {
        return this.c.computeIfAbsent(this.b.a().E1, new Function() { // from class: j41
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a((I2) obj);
            }
        });
    }
}
