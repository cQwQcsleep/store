package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.iI, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1734iI {
    public final InterfaceC2242oE a;

    public C1734iI(C1672hd c1672hd) {
        this.a = c1672hd;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C1734iI) && KB.a(this.a, ((C1734iI) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        InterfaceC2242oE interfaceC2242oE = this.a;
        KB.c(interfaceC2242oE, "<this>");
        Class clsA = ((InterfaceC0869Ub) interfaceC2242oE).a();
        KB.a((Object) clsA, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
        return clsA.getName();
    }
}
