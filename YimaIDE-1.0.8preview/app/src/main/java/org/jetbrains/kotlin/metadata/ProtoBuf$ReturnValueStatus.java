package org.jetbrains.kotlin.metadata;

import org.jetbrains.kotlin.protobuf.Internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public enum ProtoBuf$ReturnValueStatus implements Internal.EnumLite {
    UNSPECIFIED(0, 0),
    MUST_USE(1, 1),
    EXPLICITLY_IGNORABLE(2, 2);

    public static final int EXPLICITLY_IGNORABLE_VALUE = 2;
    public static final int MUST_USE_VALUE = 1;
    public static final int UNSPECIFIED_VALUE = 0;
    private static Internal.EnumLiteMap<ProtoBuf$ReturnValueStatus> internalValueMap = new Internal.EnumLiteMap<ProtoBuf$ReturnValueStatus>() { // from class: org.jetbrains.kotlin.metadata.ProtoBuf$ReturnValueStatus.1
        /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
        public ProtoBuf$ReturnValueStatus m51findValueByNumber(int i) {
            return ProtoBuf$ReturnValueStatus.valueOf(i);
        }
    };
    private final int value;

    ProtoBuf$ReturnValueStatus(int i, int i2) {
        this.value = i2;
    }

    public static Internal.EnumLiteMap<ProtoBuf$ReturnValueStatus> internalGetValueMap() {
        return internalValueMap;
    }

    public static ProtoBuf$ReturnValueStatus valueOf(int i) {
        if (i == 0) {
            return UNSPECIFIED;
        }
        if (i == 1) {
            return MUST_USE;
        }
        if (i != 2) {
            return null;
        }
        return EXPLICITLY_IGNORABLE;
    }

    public final int getNumber() {
        return this.value;
    }
}
