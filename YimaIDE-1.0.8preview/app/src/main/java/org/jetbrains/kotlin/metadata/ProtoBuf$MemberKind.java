package org.jetbrains.kotlin.metadata;

import org.jetbrains.kotlin.protobuf.Internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public enum ProtoBuf$MemberKind implements Internal.EnumLite {
    DECLARATION(0, 0),
    FAKE_OVERRIDE(1, 1),
    DELEGATION(2, 2),
    SYNTHESIZED(3, 3);

    public static final int DECLARATION_VALUE = 0;
    public static final int DELEGATION_VALUE = 2;
    public static final int FAKE_OVERRIDE_VALUE = 1;
    public static final int SYNTHESIZED_VALUE = 3;
    private static Internal.EnumLiteMap<ProtoBuf$MemberKind> internalValueMap = new Internal.EnumLiteMap<ProtoBuf$MemberKind>() { // from class: org.jetbrains.kotlin.metadata.ProtoBuf$MemberKind.1
        /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
        public ProtoBuf$MemberKind m49findValueByNumber(int i) {
            return ProtoBuf$MemberKind.valueOf(i);
        }
    };
    private final int value;

    ProtoBuf$MemberKind(int i, int i2) {
        this.value = i2;
    }

    public static Internal.EnumLiteMap<ProtoBuf$MemberKind> internalGetValueMap() {
        return internalValueMap;
    }

    public static ProtoBuf$MemberKind valueOf(int i) {
        if (i == 0) {
            return DECLARATION;
        }
        if (i == 1) {
            return FAKE_OVERRIDE;
        }
        if (i == 2) {
            return DELEGATION;
        }
        if (i != 3) {
            return null;
        }
        return SYNTHESIZED;
    }

    public final int getNumber() {
        return this.value;
    }
}
