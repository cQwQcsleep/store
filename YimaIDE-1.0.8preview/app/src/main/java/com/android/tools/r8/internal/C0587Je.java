package com.android.tools.r8.internal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Je, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0587Je {
    public static final /* synthetic */ boolean e = true;
    public final Consumer a;
    public final Thread b;
    public final ConcurrentHashMap c = new ConcurrentHashMap();
    public int d = 0;

    public C0587Je(C2752uB c2752uB) {
        this.a = c2752uB.u1.z;
        this.b = c2752uB.T;
    }

    public final void a(AbstractC0431De abstractC0431De) {
        String string = abstractC0431De.a(new StringBuilder()).toString();
        String string2 = abstractC0431De.b(new StringBuilder()).toString();
        boolean z = e;
        if (!z && !string.endsWith(string2)) {
            x1f.a();
            return;
        }
        Consumer consumer = this.a;
        if (consumer != null) {
            consumer.accept(string);
        }
        if (z || this.c.put(string, string) == null) {
            return;
        }
        x01.a("Duplicated use of context descriptor: ".concat(string));
    }
}
