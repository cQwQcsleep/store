package com.android.tools.r8.internal;

import defpackage.g3c;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class A extends AbstractC2559s {
    public static final Unsafe a;
    public static final long b;
    public static final long c;
    public static final long d;
    public static final long e;
    public static final long f;

    static {
        Unsafe unsafe;
        try {
            try {
                unsafe = Unsafe.getUnsafe();
            } catch (PrivilegedActionException e2) {
                g3c.a("Could not initialize intrinsics", e2.getCause());
                return;
            }
        } catch (SecurityException unused) {
            unsafe = (Unsafe) AccessController.doPrivileged(new C3156z());
        }
        try {
            c = unsafe.objectFieldOffset(C.class.getDeclaredField("d"));
            b = unsafe.objectFieldOffset(C.class.getDeclaredField("c"));
            d = unsafe.objectFieldOffset(C.class.getDeclaredField("b"));
            e = unsafe.objectFieldOffset(B.class.getDeclaredField("a"));
            f = unsafe.objectFieldOffset(B.class.getDeclaredField("b"));
            a = unsafe;
        } catch (NoSuchFieldException e3) {
            rc6.a(e3);
        } catch (RuntimeException e4) {
            throw e4;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2559s
    public final C2901w a(C c2) {
        return (C2901w) a.getAndSetObject(c2, b, C2901w.b);
    }

    @Override // com.android.tools.r8.internal.AbstractC2559s
    public final B b(C c2) {
        return (B) a.getAndSetObject(c2, c, B.c);
    }

    @Override // com.android.tools.r8.internal.AbstractC2559s
    public final void a(B b2, B b3) {
        a.putObject(b2, f, b3);
    }

    @Override // com.android.tools.r8.internal.AbstractC2559s
    public final boolean a(C c2, B b2, B b3) {
        return a.compareAndSwapObject(c2, c, b2, b3);
    }

    @Override // com.android.tools.r8.internal.AbstractC2559s
    public final void a(B b2, Thread thread) {
        a.putObject(b2, e, thread);
    }

    @Override // com.android.tools.r8.internal.AbstractC2559s
    public final boolean a(C c2, Object obj, Object obj2) {
        return a.compareAndSwapObject(c2, d, obj, obj2);
    }
}
