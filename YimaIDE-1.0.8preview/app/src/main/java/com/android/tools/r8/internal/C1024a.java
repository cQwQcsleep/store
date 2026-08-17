package com.android.tools.r8.internal;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1024a implements GenericArrayType, Serializable {
    public final Type b;

    public C1024a(Type type) {
        Objects.requireNonNull(type);
        this.b = AbstractC1278d.a(type);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof GenericArrayType) && AbstractC1278d.a(this, (GenericArrayType) obj);
    }

    @Override // java.lang.reflect.GenericArrayType
    public final Type getGenericComponentType() {
        return this.b;
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    public final String toString() {
        return AbstractC1278d.c(this.b) + "[]";
    }
}
