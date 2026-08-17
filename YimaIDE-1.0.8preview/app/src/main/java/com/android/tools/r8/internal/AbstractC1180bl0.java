package com.android.tools.r8.internal;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bl0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1180bl0 {
    public final Unsafe a;

    public AbstractC1180bl0(Unsafe unsafe) {
        this.a = unsafe;
    }

    public abstract byte a(Object obj, long j);

    public abstract void a(Object obj, long j, byte b);

    public boolean a() {
        Unsafe unsafe = this.a;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("arrayBaseOffset", Class.class);
            cls.getMethod("arrayIndexScale", Class.class);
            Class cls2 = Long.TYPE;
            cls.getMethod("getInt", Object.class, cls2);
            cls.getMethod("putInt", Object.class, cls2, Integer.TYPE);
            cls.getMethod("getLong", Object.class, cls2);
            cls.getMethod("putLong", Object.class, cls2, cls2);
            cls.getMethod("getObject", Object.class, cls2);
            cls.getMethod("putObject", Object.class, cls2, Object.class);
            return true;
        } catch (Throwable th) {
            AbstractC1263cl0.a(th);
            return false;
        }
    }

    public final int b(Class cls) {
        return this.a.arrayIndexScale(cls);
    }

    public abstract boolean b();

    public final long c(Object obj, long j) {
        return this.a.getLong(obj, j);
    }

    public final int b(Object obj, long j) {
        return this.a.getInt(obj, j);
    }

    public final long a(Field field) {
        return this.a.objectFieldOffset(field);
    }

    public final int a(Class cls) {
        return this.a.arrayBaseOffset(cls);
    }

    public final void a(Object obj, long j, int i) {
        this.a.putInt(obj, j, i);
    }
}
