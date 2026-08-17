package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class A10 {
    public static final C2903w00 a(C1450f00 c1450f00, Ej0 ej0) {
        KB.c(c1450f00, "<this>");
        KB.c(ej0, "typeTable");
        int i = c1450f00.d;
        if ((i & 8) == 8) {
            C2903w00 c2903w00 = c1450f00.h;
            KB.b(c2903w00, "getReturnType(...)");
            return c2903w00;
        }
        if ((i & 16) == 16) {
            return (C2903w00) ej0.a.get(c1450f00.i);
        }
        k2d.a("No returnType in ProtoBuf.Property");
        return null;
    }

    public static final C2903w00 a(J00 j00, Ej0 ej0) {
        KB.c(j00, "<this>");
        KB.c(ej0, "typeTable");
        int i = j00.d;
        if ((i & 4) == 4) {
            C2903w00 c2903w00 = j00.g;
            KB.b(c2903w00, "getType(...)");
            return c2903w00;
        }
        if ((i & 8) == 8) {
            return (C2903w00) ej0.a.get(j00.h);
        }
        k2d.a("No type in ProtoBuf.ValueParameter");
        return null;
    }
}
