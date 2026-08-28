package com.shadow.okio.internal;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okio.TypedOptions;
import java.io.IOException;

/* renamed from: com.shadow.okio.internal.-BufferedSource, reason: invalid class name */
/* loaded from: /workspace/unpacked/classes2.dex */
public final class BufferedSource {
    public static final <T> T commonSelect(com.shadow.okio.BufferedSource bufferedSource, TypedOptions<T> typedOptions) throws IOException {
        CloseableKt.checkNotNullParameter(bufferedSource, "<this>");
        CloseableKt.checkNotNullParameter(typedOptions, "options");
        int iSelect = bufferedSource.select(typedOptions.getOptions$okio());
        if (iSelect == -1) {
            return null;
        }
        return typedOptions.get(iSelect);
    }
}
