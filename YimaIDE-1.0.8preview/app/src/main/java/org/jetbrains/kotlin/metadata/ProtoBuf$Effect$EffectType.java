package org.jetbrains.kotlin.metadata;

import org.jetbrains.kotlin.protobuf.Internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public enum ProtoBuf$Effect$EffectType implements Internal.EnumLite {
    RETURNS_CONSTANT(0, 0),
    CALLS(1, 1),
    RETURNS_NOT_NULL(2, 2),
    RETURNS_RESULT_OF(3, 3);

    public static final int CALLS_VALUE = 1;
    public static final int RETURNS_CONSTANT_VALUE = 0;
    public static final int RETURNS_NOT_NULL_VALUE = 2;
    public static final int RETURNS_RESULT_OF_VALUE = 3;
    private static Internal.EnumLiteMap<ProtoBuf$Effect$EffectType> internalValueMap = new Internal.EnumLiteMap<ProtoBuf$Effect$EffectType>() { // from class: org.jetbrains.kotlin.metadata.ProtoBuf$Effect$EffectType.1
        /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
        public ProtoBuf$Effect$EffectType m47findValueByNumber(int i) {
            return ProtoBuf$Effect$EffectType.valueOf(i);
        }
    };
    private final int value;

    ProtoBuf$Effect$EffectType(int i, int i2) {
        this.value = i2;
    }

    public static Internal.EnumLiteMap<ProtoBuf$Effect$EffectType> internalGetValueMap() {
        return internalValueMap;
    }

    public static ProtoBuf$Effect$EffectType valueOf(int i) {
        if (i == 0) {
            return RETURNS_CONSTANT;
        }
        if (i == 1) {
            return CALLS;
        }
        if (i == 2) {
            return RETURNS_NOT_NULL;
        }
        if (i != 3) {
            return null;
        }
        return RETURNS_RESULT_OF;
    }

    public final int getNumber() {
        return this.value;
    }
}
