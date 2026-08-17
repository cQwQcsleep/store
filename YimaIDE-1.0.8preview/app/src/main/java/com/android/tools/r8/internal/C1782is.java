package com.android.tools.r8.internal;

import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.android.tools.r8.internal.is, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1782is {
    public final Method a;
    public final Method b;
    public final Method c;
    public final Method d;
    public final Method e;
    public final Method f;
    public final Method g;

    public C1782is(String str, Class cls, Class cls2, String str2, boolean z, boolean z2) {
        Method methodA = AbstractC2209ns.a(cls, F40.a("get", str), new Class[0]);
        this.a = methodA;
        this.b = AbstractC2209ns.a(cls2, F40.a("get", str), new Class[0]);
        this.c = AbstractC2209ns.a(cls2, F40.a("set", str), new Class[]{methodA.getReturnType()});
        this.d = z2 ? AbstractC2209ns.a(cls, F40.a("has", str), new Class[0]) : null;
        this.e = z2 ? AbstractC2209ns.a(cls2, F40.a("has", str), new Class[0]) : null;
        AbstractC2209ns.a(cls2, F40.a("clear", str), new Class[0]);
        this.f = z ? AbstractC2209ns.a(cls, C40.a("get", str2, "Case"), new Class[0]) : null;
        this.g = z ? AbstractC2209ns.a(cls2, C40.a("get", str2, "Case"), new Class[0]) : null;
    }
}
