package org.jetbrains.kotlin.backend.common.serialization.proto;

import org.jetbrains.kotlin.protobuf.Internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public enum IrSyntheticBodyKind implements Internal.EnumLite {
    ENUM_VALUES(0, 1),
    ENUM_VALUEOF(1, 2),
    ENUM_ENTRIES(2, 3);

    public static final int ENUM_ENTRIES_VALUE = 3;
    public static final int ENUM_VALUEOF_VALUE = 2;
    public static final int ENUM_VALUES_VALUE = 1;
    private static Internal.EnumLiteMap<IrSyntheticBodyKind> internalValueMap = new Internal.EnumLiteMap<IrSyntheticBodyKind>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.IrSyntheticBodyKind.1
        /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
        public IrSyntheticBodyKind m1601findValueByNumber(int i) {
            return IrSyntheticBodyKind.valueOf(i);
        }
    };
    private final int value;

    IrSyntheticBodyKind(int i, int i2) {
        this.value = i2;
    }

    public static Internal.EnumLiteMap<IrSyntheticBodyKind> internalGetValueMap() {
        return internalValueMap;
    }

    public static IrSyntheticBodyKind valueOf(int i) {
        if (i == 1) {
            return ENUM_VALUES;
        }
        if (i == 2) {
            return ENUM_VALUEOF;
        }
        if (i != 3) {
            return null;
        }
        return ENUM_ENTRIES;
    }

    public final int getNumber() {
        return this.value;
    }
}
