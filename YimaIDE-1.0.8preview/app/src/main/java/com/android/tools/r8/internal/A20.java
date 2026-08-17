package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import java.util.IdentityHashMap;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class A20 {
    public static final /* synthetic */ boolean c = true;
    public final Set a = AbstractC2780ub0.c();
    public final IdentityHashMap b = new IdentityHashMap();

    public final synchronized void a(com.android.tools.r8.graph.E0 e0, C0322w2 c0322w2, com.android.tools.r8.graph.B5 b5) {
        try {
            if (!c) {
                com.android.tools.r8.graph.I2 i2W0 = c0322w2.w0();
                com.android.tools.r8.graph.I2 type = e0.getType();
                i2W0.getClass();
                if (!com.android.tools.r8.graph.I2.a(i2W0, type)) {
                    throw new AssertionError();
                }
            }
            this.b.put(b5.getReference(), c0322w2);
            if (e0.isInterface()) {
                this.a.add(e0.getType());
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
