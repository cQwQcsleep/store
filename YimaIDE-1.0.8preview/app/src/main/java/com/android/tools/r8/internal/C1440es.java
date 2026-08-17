package com.android.tools.r8.internal;

import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.android.tools.r8.internal.es, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1440es {
    public final Method a;
    public final Method b;
    public final Method c;
    public final Method d;
    public final Method e;
    public final Method f;
    public final Method g;
    public final Method h;

    public C1440es(String str, Class cls, Class cls2) {
        this.a = AbstractC2209ns.a(cls, C40.a("get", str, "List"), new Class[0]);
        this.b = AbstractC2209ns.a(cls2, C40.a("get", str, "List"), new Class[0]);
        String strA = F40.a("get", str);
        Class cls3 = Integer.TYPE;
        Method methodA = AbstractC2209ns.a(cls, strA, new Class[]{cls3});
        this.c = methodA;
        this.d = AbstractC2209ns.a(cls2, F40.a("get", str), new Class[]{cls3});
        Class<?> returnType = methodA.getReturnType();
        AbstractC2209ns.a(cls2, F40.a("set", str), new Class[]{cls3, returnType});
        this.e = AbstractC2209ns.a(cls2, F40.a("add", str), new Class[]{returnType});
        this.f = AbstractC2209ns.a(cls, C40.a("get", str, "Count"), new Class[0]);
        this.g = AbstractC2209ns.a(cls2, C40.a("get", str, "Count"), new Class[0]);
        this.h = AbstractC2209ns.a(cls2, F40.a("clear", str), new Class[0]);
    }
}
