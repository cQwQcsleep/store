package org.jetbrains.kotlin.metadata;

import org.jetbrains.kotlin.protobuf.Internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public enum ProtoBuf$Effect$EffectConditionKind implements Internal.EnumLite {
    CONCLUSION_CONDITION(0, 0),
    RETURNS_CONDITION(1, 1),
    HOLDSIN_CONDITION(2, 2);

    public static final int CONCLUSION_CONDITION_VALUE = 0;
    public static final int HOLDSIN_CONDITION_VALUE = 2;
    public static final int RETURNS_CONDITION_VALUE = 1;
    private static Internal.EnumLiteMap<ProtoBuf$Effect$EffectConditionKind> internalValueMap = new Internal.EnumLiteMap<ProtoBuf$Effect$EffectConditionKind>() { // from class: org.jetbrains.kotlin.metadata.ProtoBuf$Effect$EffectConditionKind.1
        /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
        public ProtoBuf$Effect$EffectConditionKind m46findValueByNumber(int i) {
            return ProtoBuf$Effect$EffectConditionKind.valueOf(i);
        }
    };
    private final int value;

    ProtoBuf$Effect$EffectConditionKind(int i, int i2) {
        this.value = i2;
    }

    public static Internal.EnumLiteMap<ProtoBuf$Effect$EffectConditionKind> internalGetValueMap() {
        return internalValueMap;
    }

    public static ProtoBuf$Effect$EffectConditionKind valueOf(int i) {
        if (i == 0) {
            return CONCLUSION_CONDITION;
        }
        if (i == 1) {
            return RETURNS_CONDITION;
        }
        if (i != 2) {
            return null;
        }
        return HOLDSIN_CONDITION;
    }

    public final int getNumber() {
        return this.value;
    }
}
