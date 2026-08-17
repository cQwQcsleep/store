package com.android.tools.r8.references;

import com.android.tools.r8.internal.C0929Wj;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface TypeReference {
    default ArrayReference asArray() {
        return null;
    }

    default ClassReference asClass() {
        return null;
    }

    default PrimitiveReference asPrimitive() {
        return null;
    }

    String getDescriptor();

    default String getTypeName() {
        return C0929Wj.b(getDescriptor());
    }

    default boolean isArray() {
        return false;
    }

    default boolean isClass() {
        return false;
    }

    default boolean isPrimitive() {
        return false;
    }
}
