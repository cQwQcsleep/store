package com.android.tools.r8.internal;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1110b implements ParameterizedType, Serializable {
    public final Type b;
    public final Type c;
    public final Type[] d;

    public C1110b(Type type, Type type2, Type... typeArr) {
        Objects.requireNonNull(type2);
        if (type2 instanceof Class) {
            Class cls = (Class) type2;
            boolean z = Modifier.isStatic(cls.getModifiers()) || cls.getEnclosingClass() == null;
            if (type == null && !z) {
                j2d.a();
                throw null;
            }
        }
        this.b = type == null ? null : AbstractC1278d.a(type);
        this.c = AbstractC1278d.a(type2);
        Type[] typeArr2 = (Type[]) typeArr.clone();
        this.d = typeArr2;
        int length = typeArr2.length;
        for (int i = 0; i < length; i++) {
            Objects.requireNonNull(this.d[i]);
            Type type3 = this.d[i];
            if ((type3 instanceof Class) && ((Class) type3).isPrimitive()) {
                j2d.a();
                throw null;
            }
            Type[] typeArr3 = this.d;
            typeArr3[i] = AbstractC1278d.a(typeArr3[i]);
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof ParameterizedType) && AbstractC1278d.a(this, (ParameterizedType) obj);
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type[] getActualTypeArguments() {
        return (Type[]) this.d.clone();
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getOwnerType() {
        return this.b;
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getRawType() {
        return this.c;
    }

    public final int hashCode() {
        int iHashCode = Arrays.hashCode(this.d) ^ this.c.hashCode();
        Type type = this.b;
        return (type != null ? type.hashCode() : 0) ^ iHashCode;
    }

    public final String toString() {
        int length = this.d.length;
        if (length == 0) {
            return AbstractC1278d.c(this.c);
        }
        StringBuilder sb = new StringBuilder((length + 1) * 30);
        sb.append(AbstractC1278d.c(this.c));
        sb.append("<");
        sb.append(AbstractC1278d.c(this.d[0]));
        for (int i = 1; i < length; i++) {
            sb.append(", ");
            sb.append(AbstractC1278d.c(this.d[i]));
        }
        sb.append(">");
        return sb.toString();
    }
}
