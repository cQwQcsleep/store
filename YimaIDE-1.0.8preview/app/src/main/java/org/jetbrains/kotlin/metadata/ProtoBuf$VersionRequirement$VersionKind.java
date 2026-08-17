package org.jetbrains.kotlin.metadata;

import org.jetbrains.kotlin.protobuf.Internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public enum ProtoBuf$VersionRequirement$VersionKind implements Internal.EnumLite {
    LANGUAGE_VERSION(0, 0),
    COMPILER_VERSION(1, 1),
    API_VERSION(2, 2);

    public static final int API_VERSION_VALUE = 2;
    public static final int COMPILER_VERSION_VALUE = 1;
    public static final int LANGUAGE_VERSION_VALUE = 0;
    private static Internal.EnumLiteMap<ProtoBuf$VersionRequirement$VersionKind> internalValueMap = new Internal.EnumLiteMap<ProtoBuf$VersionRequirement$VersionKind>() { // from class: org.jetbrains.kotlin.metadata.ProtoBuf$VersionRequirement$VersionKind.1
        /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
        public ProtoBuf$VersionRequirement$VersionKind m52findValueByNumber(int i) {
            return ProtoBuf$VersionRequirement$VersionKind.valueOf(i);
        }
    };
    private final int value;

    ProtoBuf$VersionRequirement$VersionKind(int i, int i2) {
        this.value = i2;
    }

    public static Internal.EnumLiteMap<ProtoBuf$VersionRequirement$VersionKind> internalGetValueMap() {
        return internalValueMap;
    }

    public static ProtoBuf$VersionRequirement$VersionKind valueOf(int i) {
        if (i == 0) {
            return LANGUAGE_VERSION;
        }
        if (i == 1) {
            return COMPILER_VERSION;
        }
        if (i != 2) {
            return null;
        }
        return API_VERSION;
    }

    public final int getNumber() {
        return this.value;
    }
}
