package com.android.tools.r8.internal;

import defpackage.g3c;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Nr, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0703Nr {
    public final L0 a;
    public final Object b;
    public final L0 c;
    public final C0677Mr d;
    public final Method e;

    public C0703Nr(L0 l0, Object obj, L0 l1, C0677Mr c0677Mr, Class cls) {
        if (l0 == null) {
            w01.a("Null containingTypeDefaultInstance");
            throw null;
        }
        if (c0677Mr.c == Mm0.g && l1 == null) {
            w01.a("Null messageDefaultInstance");
            throw null;
        }
        this.a = l0;
        this.b = obj;
        this.c = l1;
        this.d = c0677Mr;
        if (!ZA.class.isAssignableFrom(cls)) {
            this.e = null;
            return;
        }
        try {
            this.e = cls.getMethod("valueOf", Integer.TYPE);
        } catch (NoSuchMethodException e) {
            String name = cls.getName();
            StringBuilder sb = new StringBuilder(name.length() + 52);
            sb.append("Generated message class \"");
            sb.append(name);
            sb.append("\" missing method \"valueOf\".");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    public final Object a(Object obj) {
        if (this.d.c.b != Om0.j) {
            return obj;
        }
        try {
            return this.e.invoke(null, (Integer) obj);
        } catch (IllegalAccessException e) {
            g3c.a("Couldn't use Java reflection to implement protocol message reflection.", e);
            return null;
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            g3c.a("Unexpected exception thrown by generated accessor method.", cause);
            return null;
        }
    }

    public final Object b(Object obj) {
        return this.d.c.b == Om0.j ? Integer.valueOf(((ZA) obj).a()) : obj;
    }
}
