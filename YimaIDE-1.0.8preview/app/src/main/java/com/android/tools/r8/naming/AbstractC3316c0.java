package com.android.tools.r8.naming;

import com.android.tools.r8.graph.C0322w2;
import java.util.HashMap;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.naming.c0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3316c0 {
    public final HashMap a = new HashMap();
    public final Function b;

    public AbstractC3316c0(Function function) {
        this.b = function;
    }

    public abstract Object a(C0322w2 c0322w2);

    public final /* synthetic */ Object a(C0322w2 c0322w2, Object obj) {
        return a(c0322w2);
    }

    public final Object b(C0322w2 c0322w2) {
        return this.a.get(this.b.apply(c0322w2));
    }

    public final Object c(final C0322w2 c0322w2) {
        return this.a.computeIfAbsent(this.b.apply(c0322w2), new Function() { // from class: yig
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(c0322w2, obj);
            }
        });
    }
}
