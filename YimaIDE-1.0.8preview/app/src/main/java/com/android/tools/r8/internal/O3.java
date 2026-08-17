package com.android.tools.r8.internal;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class O3 implements Ai0 {
    @Override // com.android.tools.r8.internal.Ai0
    public final AbstractC3220zi0 a(C0471Es c0471Es, Fj0 fj0) {
        Type type = fj0.b;
        boolean z = type instanceof GenericArrayType;
        if (!z && (!(type instanceof Class) || !((Class) type).isArray())) {
            return null;
        }
        Type genericComponentType = z ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
        return new P3(c0471Es, c0471Es.a(new Fj0(genericComponentType)), AbstractC1278d.b(genericComponentType));
    }
}
