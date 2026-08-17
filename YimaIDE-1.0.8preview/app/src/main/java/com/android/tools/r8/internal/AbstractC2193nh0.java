package com.android.tools.r8.internal;

import java.lang.Exception;
import java.util.function.IntConsumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nh0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2193nh0<E extends Exception> {
    public final int a(InterfaceC2279oh0 interfaceC2279oh0) {
        return a() ? b() : interfaceC2279oh0.a();
    }

    public abstract boolean a();

    public abstract int b();

    public void a(IntConsumer intConsumer) throws Exception {
        while (a()) {
            intConsumer.accept(b());
        }
    }
}
