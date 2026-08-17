package org.jetbrains.kotlin.metadata;

import org.jetbrains.kotlin.protobuf.Internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public enum ProtoBuf$Effect$InvocationKind implements Internal.EnumLite {
    AT_MOST_ONCE(0, 0),
    EXACTLY_ONCE(1, 1),
    AT_LEAST_ONCE(2, 2);

    public static final int AT_LEAST_ONCE_VALUE = 2;
    public static final int AT_MOST_ONCE_VALUE = 0;
    public static final int EXACTLY_ONCE_VALUE = 1;
    private static Internal.EnumLiteMap<ProtoBuf$Effect$InvocationKind> internalValueMap = new Internal.EnumLiteMap<ProtoBuf$Effect$InvocationKind>() { // from class: org.jetbrains.kotlin.metadata.ProtoBuf$Effect$InvocationKind.1
        /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
        public ProtoBuf$Effect$InvocationKind m48findValueByNumber(int i) {
            return ProtoBuf$Effect$InvocationKind.valueOf(i);
        }
    };
    private final int value;

    ProtoBuf$Effect$InvocationKind(int i, int i2) {
        this.value = i2;
    }

    public static Internal.EnumLiteMap<ProtoBuf$Effect$InvocationKind> internalGetValueMap() {
        return internalValueMap;
    }

    public static ProtoBuf$Effect$InvocationKind valueOf(int i) {
        if (i == 0) {
            return AT_MOST_ONCE;
        }
        if (i == 1) {
            return EXACTLY_ONCE;
        }
        if (i != 2) {
            return null;
        }
        return AT_LEAST_ONCE;
    }

    public final int getNumber() {
        return this.value;
    }
}
