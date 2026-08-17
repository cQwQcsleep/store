package org.jetbrains.kotlin.metadata.js;

import org.jetbrains.kotlin.protobuf.Internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public enum JsProtoBuf$Library$Kind implements Internal.EnumLite {
    PLAIN(0, 1),
    AMD(1, 2),
    COMMON_JS(2, 3),
    UMD(3, 4);

    public static final int AMD_VALUE = 2;
    public static final int COMMON_JS_VALUE = 3;
    public static final int PLAIN_VALUE = 1;
    public static final int UMD_VALUE = 4;
    private static Internal.EnumLiteMap<JsProtoBuf$Library$Kind> internalValueMap = new Internal.EnumLiteMap<JsProtoBuf$Library$Kind>() { // from class: org.jetbrains.kotlin.metadata.js.JsProtoBuf$Library$Kind.1
        /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
        public JsProtoBuf$Library$Kind m54findValueByNumber(int i) {
            return JsProtoBuf$Library$Kind.valueOf(i);
        }
    };
    private final int value;

    JsProtoBuf$Library$Kind(int i, int i2) {
        this.value = i2;
    }

    public static Internal.EnumLiteMap<JsProtoBuf$Library$Kind> internalGetValueMap() {
        return internalValueMap;
    }

    public static JsProtoBuf$Library$Kind valueOf(int i) {
        if (i == 1) {
            return PLAIN;
        }
        if (i == 2) {
            return AMD;
        }
        if (i == 3) {
            return COMMON_JS;
        }
        if (i != 4) {
            return null;
        }
        return UMD;
    }

    public final int getNumber() {
        return this.value;
    }
}
