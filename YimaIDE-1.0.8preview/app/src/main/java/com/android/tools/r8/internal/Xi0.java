package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Xi0 implements Ai0 {
    @Override // com.android.tools.r8.internal.Ai0
    public final AbstractC3220zi0 a(C0471Es c0471Es, Fj0 fj0) {
        Class superclass = fj0.a;
        if (!Enum.class.isAssignableFrom(superclass) || superclass == Enum.class) {
            return null;
        }
        if (!superclass.isEnum()) {
            superclass = superclass.getSuperclass();
        }
        return new C2111mj0(superclass);
    }
}
