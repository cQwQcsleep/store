package com.android.tools.r8.internal;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayDeque;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Bd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0378Bd implements Closeable {
    public static final InterfaceC0352Ad c;
    public final InterfaceC0352Ad a;
    public final ArrayDeque b = new ArrayDeque(4);

    static {
        InterfaceC0352Ad c3209zd;
        try {
            c3209zd = new C3209zd(Throwable.class.getMethod("addSuppressed", Throwable.class));
        } catch (Throwable unused) {
            c3209zd = null;
        }
        if (c3209zd == null) {
            c3209zd = C3123yd.a;
        }
        c = c3209zd;
    }

    public C0378Bd(InterfaceC0352Ad interfaceC0352Ad) {
        interfaceC0352Ad.getClass();
        this.a = interfaceC0352Ad;
    }

    public final Closeable a(Closeable closeable) {
        if (closeable != null) {
            this.b.addFirst(closeable);
        }
        return closeable;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        Throwable th = null;
        while (!this.b.isEmpty()) {
            Closeable closeable = (Closeable) this.b.removeFirst();
            try {
                closeable.close();
            } catch (Throwable th2) {
                if (th == null) {
                    th = th2;
                } else {
                    this.a.a(closeable, th, th2);
                }
            }
        }
        if (th != null) {
            Object obj = AbstractC1595gh0.a;
            if (IOException.class.isInstance(th)) {
                throw ((Throwable) IOException.class.cast(th));
            }
            if (th instanceof RuntimeException) {
                throw ((RuntimeException) th);
            }
            if (th instanceof Error) {
                throw ((Error) th);
            }
            x01.a(th);
        }
    }
}
