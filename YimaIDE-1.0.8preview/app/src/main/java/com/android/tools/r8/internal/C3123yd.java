package com.android.tools.r8.internal;

import java.io.Closeable;
import java.util.logging.Level;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3123yd implements InterfaceC0352Ad {
    public static final C3123yd a = new C3123yd();

    @Override // com.android.tools.r8.internal.InterfaceC0352Ad
    public final void a(Closeable closeable, Throwable th, Throwable th2) {
        AbstractC3040xd.a.log(Level.WARNING, "Suppressing exception thrown when closing " + closeable, th2);
    }
}
