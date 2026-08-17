package com.android.tools.r8.internal;

import java.util.concurrent.Future;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Fr, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0496Fr {
    public static Object a(Future future) {
        Object obj;
        if (!future.isDone()) {
            k2d.a(Xf0.a("Future was expected to be done: %s", new Object[]{future}));
            return null;
        }
        boolean z = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }
}
