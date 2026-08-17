package com.android.tools.r8.internal;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1195c implements WildcardType, Serializable {
    public final Type b;
    public final Type c;

    public C1195c(Type[] typeArr, Type[] typeArr2) {
        if (typeArr2.length > 1) {
            j2d.a();
            throw null;
        }
        if (typeArr.length != 1) {
            j2d.a();
            throw null;
        }
        if (typeArr2.length != 1) {
            Objects.requireNonNull(typeArr[0]);
            Type type = typeArr[0];
            if ((type instanceof Class) && ((Class) type).isPrimitive()) {
                j2d.a();
                throw null;
            }
            this.c = null;
            this.b = AbstractC1278d.a(typeArr[0]);
            return;
        }
        Objects.requireNonNull(typeArr2[0]);
        Type type2 = typeArr2[0];
        if ((type2 instanceof Class) && ((Class) type2).isPrimitive()) {
            j2d.a();
            throw null;
        }
        if (typeArr[0] != Object.class) {
            j2d.a();
            throw null;
        }
        this.c = AbstractC1278d.a(typeArr2[0]);
        this.b = Object.class;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof WildcardType) && AbstractC1278d.a(this, (WildcardType) obj);
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getLowerBounds() {
        Type type = this.c;
        return type != null ? new Type[]{type} : AbstractC1278d.a;
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getUpperBounds() {
        return new Type[]{this.b};
    }

    public final int hashCode() {
        Type type = this.c;
        return (this.b.hashCode() + 31) ^ (type != null ? type.hashCode() + 31 : 1);
    }

    public final String toString() {
        if (this.c != null) {
            return "? super " + AbstractC1278d.c(this.c);
        }
        if (this.b == Object.class) {
            return "?";
        }
        return "? extends " + AbstractC1278d.c(this.b);
    }
}
