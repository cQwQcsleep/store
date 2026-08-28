package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;

/* renamed from: com.shadow.okio.-DeprecatedUtf8, reason: invalid class name */
/* loaded from: /workspace/unpacked/classes2.dex */
public final class DeprecatedUtf8 {
    public static final DeprecatedUtf8 INSTANCE = new DeprecatedUtf8();

    private DeprecatedUtf8() {
    }

    public final long size(String str) {
        CloseableKt.checkNotNullParameter(str, "string");
        return Utf8.size$default(str, 0, 0, 3, null);
    }

    public final long size(String str, int i, int i2) {
        CloseableKt.checkNotNullParameter(str, "string");
        return Utf8.size(str, i, i2);
    }
}
