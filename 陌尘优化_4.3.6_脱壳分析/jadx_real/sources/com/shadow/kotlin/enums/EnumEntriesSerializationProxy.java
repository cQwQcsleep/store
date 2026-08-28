package com.shadow.kotlin.enums;

import com.shadow.kotlin.io.CloseableKt;
import java.io.Serializable;
import java.lang.Enum;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class EnumEntriesSerializationProxy<E extends Enum<E>> implements Serializable {
    private static final long serialVersionUID = 0;
    private final Class<E> c;

    public EnumEntriesSerializationProxy(E[] eArr) {
        CloseableKt.checkNotNullParameter(eArr, "entries");
        Class<E> cls = (Class<E>) eArr.getClass().getComponentType();
        CloseableKt.checkNotNull(cls);
        this.c = cls;
    }

    private final Object readResolve() {
        E[] enumConstants = this.c.getEnumConstants();
        CloseableKt.checkNotNullExpressionValue(enumConstants, "getEnumConstants(...)");
        return new EnumEntriesList(enumConstants);
    }
}
