package com.android.tools.r8.internal;

import java.io.Closeable;
import java.lang.reflect.Method;
import java.util.logging.Level;

/* JADX INFO: renamed from: com.android.tools.r8.internal.zd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3209zd implements InterfaceC0352Ad {
    public final Method a;

    public C3209zd(Method method) {
        this.a = method;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0352Ad
    public final void a(Closeable closeable, Throwable th, Throwable th2) {
        if (th == th2) {
            return;
        }
        try {
            this.a.invoke(th, th2);
        } catch (Throwable unused) {
            AbstractC3040xd.a.log(Level.WARNING, "Suppressing exception thrown when closing " + closeable, th2);
        }
    }
}
