package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.h8, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1636h8 {
    public final C0333y a;
    public final ConcurrentHashMap b = new ConcurrentHashMap();
    public final ConcurrentHashMap c = new ConcurrentHashMap();

    public AbstractC1636h8(C0333y c0333y) {
        this.a = c0333y;
    }

    public abstract JR a(com.android.tools.r8.graph.B5 b5);

    public final /* synthetic */ JR a(com.android.tools.r8.graph.B5 b5, C0322w2 c0322w2) {
        return a(b5);
    }

    public final JR b(final com.android.tools.r8.graph.B5 b5) {
        return (JR) this.b.computeIfAbsent(b5.getReference(), new Function() { // from class: m2h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(b5, (C0322w2) obj);
            }
        });
    }
}
