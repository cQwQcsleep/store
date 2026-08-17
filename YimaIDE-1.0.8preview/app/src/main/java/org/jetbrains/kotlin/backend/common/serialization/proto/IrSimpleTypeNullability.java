package org.jetbrains.kotlin.backend.common.serialization.proto;

import org.jetbrains.kotlin.protobuf.Internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public enum IrSimpleTypeNullability implements Internal.EnumLite {
    MARKED_NULLABLE(0, 0),
    NOT_SPECIFIED(1, 1),
    DEFINITELY_NOT_NULL(2, 2);

    public static final int DEFINITELY_NOT_NULL_VALUE = 2;
    public static final int MARKED_NULLABLE_VALUE = 0;
    public static final int NOT_SPECIFIED_VALUE = 1;
    private static Internal.EnumLiteMap<IrSimpleTypeNullability> internalValueMap = new Internal.EnumLiteMap<IrSimpleTypeNullability>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeNullability.1
        /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
        public IrSimpleTypeNullability m1536findValueByNumber(int i) {
            return IrSimpleTypeNullability.valueOf(i);
        }
    };
    private final int value;

    IrSimpleTypeNullability(int i, int i2) {
        this.value = i2;
    }

    public static Internal.EnumLiteMap<IrSimpleTypeNullability> internalGetValueMap() {
        return internalValueMap;
    }

    public static IrSimpleTypeNullability valueOf(int i) {
        if (i == 0) {
            return MARKED_NULLABLE;
        }
        if (i == 1) {
            return NOT_SPECIFIED;
        }
        if (i != 2) {
            return null;
        }
        return DEFINITELY_NOT_NULL;
    }

    public final int getNumber() {
        return this.value;
    }
}
