package org.jetbrains.kotlin.backend.common.serialization.proto;

import org.jetbrains.kotlin.protobuf.Internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public enum IrTypeOperator implements Internal.EnumLite {
    CAST(0, 1),
    IMPLICIT_CAST(1, 2),
    IMPLICIT_NOTNULL(2, 3),
    IMPLICIT_COERCION_TO_UNIT(3, 4),
    IMPLICIT_INTEGER_COERCION(4, 5),
    SAFE_CAST(5, 6),
    INSTANCEOF(6, 7),
    NOT_INSTANCEOF(7, 8),
    SAM_CONVERSION(8, 9),
    IMPLICIT_DYNAMIC_CAST(9, 10),
    REINTERPRET_CAST(10, 11);

    public static final int CAST_VALUE = 1;
    public static final int IMPLICIT_CAST_VALUE = 2;
    public static final int IMPLICIT_COERCION_TO_UNIT_VALUE = 4;
    public static final int IMPLICIT_DYNAMIC_CAST_VALUE = 10;
    public static final int IMPLICIT_INTEGER_COERCION_VALUE = 5;
    public static final int IMPLICIT_NOTNULL_VALUE = 3;
    public static final int INSTANCEOF_VALUE = 7;
    public static final int NOT_INSTANCEOF_VALUE = 8;
    public static final int REINTERPRET_CAST_VALUE = 11;
    public static final int SAFE_CAST_VALUE = 6;
    public static final int SAM_CONVERSION_VALUE = 9;
    private static Internal.EnumLiteMap<IrTypeOperator> internalValueMap = new Internal.EnumLiteMap<IrTypeOperator>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.IrTypeOperator.1
        /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
        public IrTypeOperator m1698findValueByNumber(int i) {
            return IrTypeOperator.valueOf(i);
        }
    };
    private final int value;

    IrTypeOperator(int i, int i2) {
        this.value = i2;
    }

    public static Internal.EnumLiteMap<IrTypeOperator> internalGetValueMap() {
        return internalValueMap;
    }

    public static IrTypeOperator valueOf(int i) {
        switch (i) {
            case 1:
                return CAST;
            case 2:
                return IMPLICIT_CAST;
            case 3:
                return IMPLICIT_NOTNULL;
            case 4:
                return IMPLICIT_COERCION_TO_UNIT;
            case 5:
                return IMPLICIT_INTEGER_COERCION;
            case 6:
                return SAFE_CAST;
            case 7:
                return INSTANCEOF;
            case 8:
                return NOT_INSTANCEOF;
            case 9:
                return SAM_CONVERSION;
            case 10:
                return IMPLICIT_DYNAMIC_CAST;
            case 11:
                return REINTERPRET_CAST;
            default:
                return null;
        }
    }

    public final int getNumber() {
        return this.value;
    }
}
