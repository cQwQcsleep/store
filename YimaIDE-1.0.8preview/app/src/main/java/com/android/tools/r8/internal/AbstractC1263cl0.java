package com.android.tools.r8.internal;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* JADX INFO: renamed from: com.android.tools.r8.internal.cl0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1263cl0 {
    public static final Class a;
    public static final AbstractC1180bl0 b;
    public static final boolean c;
    public static final boolean d;
    public static final long e;
    public static final boolean f;

    /* JADX WARN: Code duplicated, block: B:15:0x0039  */
    /* JADX WARN: Code duplicated, block: B:32:0x00a2 A[PHI: r4
      0x00a2: PHI (r4v20 java.lang.reflect.Field) = (r4v18 java.lang.reflect.Field), (r4v23 java.lang.reflect.Field) binds: [B:39:0x00b2, B:31:0x00a0] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:33:0x00a4  */
    static {
        Unsafe unsafe;
        AbstractC1180bl0 c1094al0;
        Field declaredField;
        Field field = null;
        try {
            unsafe = (Unsafe) AccessController.doPrivileged(new Xk0());
        } catch (Throwable unused) {
            unsafe = null;
        }
        a = AbstractC2394q2.a();
        Class<?> cls = Long.TYPE;
        boolean zC = c(cls);
        boolean zC2 = c(Integer.TYPE);
        if (unsafe == null) {
            c1094al0 = null;
        } else if (!AbstractC2394q2.b()) {
            c1094al0 = new C1094al0(unsafe);
        } else if (zC) {
            c1094al0 = new Zk0(unsafe);
        } else if (zC2) {
            c1094al0 = new Yk0(unsafe);
        } else {
            c1094al0 = null;
        }
        b = c1094al0;
        c = c1094al0 == null ? false : c1094al0.b();
        d = c1094al0 == null ? false : c1094al0.a();
        e = a(byte[].class);
        a(boolean[].class);
        b(boolean[].class);
        a(int[].class);
        b(int[].class);
        a(long[].class);
        b(long[].class);
        a(float[].class);
        b(float[].class);
        a(double[].class);
        b(double[].class);
        a(Object[].class);
        b(Object[].class);
        if (AbstractC2394q2.b()) {
            try {
                declaredField = Buffer.class.getDeclaredField("effectiveDirectAddress");
            } catch (Throwable unused2) {
                declaredField = null;
            }
            if (declaredField != null) {
                field = declaredField;
            } else {
                try {
                    declaredField = Buffer.class.getDeclaredField("address");
                } catch (Throwable unused3) {
                    declaredField = null;
                }
                if (declaredField != null && declaredField.getType() == cls) {
                    field = declaredField;
                }
            }
        } else {
            declaredField = Buffer.class.getDeclaredField("address");
            if (declaredField != null) {
                field = declaredField;
            }
        }
        if (field != null && c1094al0 != null) {
            c1094al0.a(field);
        }
        f = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    public static void a(Throwable th) {
        Logger.getLogger(AbstractC1263cl0.class.getName()).log(Level.WARNING, "platform method missing - proto runtime falling back to safer methods: " + th);
    }

    public static void b(Class cls) {
        if (d) {
            b.b(cls);
        }
    }

    public static boolean c(Class cls) {
        if (!AbstractC2394q2.b()) {
            return false;
        }
        try {
            Class cls2 = a;
            Class cls3 = Boolean.TYPE;
            cls2.getMethod("peekLong", cls, cls3);
            cls2.getMethod("pokeLong", cls, Long.TYPE, cls3);
            Class cls4 = Integer.TYPE;
            cls2.getMethod("pokeInt", cls, cls4, cls3);
            cls2.getMethod("peekInt", cls, cls3);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, cls4, cls4);
            cls2.getMethod("peekByteArray", cls, byte[].class, cls4, cls4);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static int a(Class cls) {
        if (d) {
            return b.a(cls);
        }
        return -1;
    }
}
