package org.jetbrains.kotlin.metadata;

import org.jetbrains.kotlin.protobuf.Internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public enum ProtoBuf$Modality implements Internal.EnumLite {
    FINAL(0, 0),
    OPEN(1, 1),
    ABSTRACT(2, 2),
    SEALED(3, 3);

    public static final int ABSTRACT_VALUE = 2;
    public static final int FINAL_VALUE = 0;
    public static final int OPEN_VALUE = 1;
    public static final int SEALED_VALUE = 3;
    private static Internal.EnumLiteMap<ProtoBuf$Modality> internalValueMap = new Internal.EnumLiteMap<ProtoBuf$Modality>() { // from class: org.jetbrains.kotlin.metadata.ProtoBuf$Modality.1
        /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
        public ProtoBuf$Modality m50findValueByNumber(int i) {
            return ProtoBuf$Modality.valueOf(i);
        }
    };
    private final int value;

    ProtoBuf$Modality(int i, int i2) {
        this.value = i2;
    }

    public static Internal.EnumLiteMap<ProtoBuf$Modality> internalGetValueMap() {
        return internalValueMap;
    }

    public static ProtoBuf$Modality valueOf(int i) {
        if (i == 0) {
            return FINAL;
        }
        if (i == 1) {
            return OPEN;
        }
        if (i == 2) {
            return ABSTRACT;
        }
        if (i != 3) {
            return null;
        }
        return SEALED;
    }

    public final int getNumber() {
        return this.value;
    }
}
