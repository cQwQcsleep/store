package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;

/* renamed from: com.shadow.okio.-GzipSinkExtensions, reason: invalid class name */
/* loaded from: /workspace/unpacked/classes2.dex */
public final class GzipSinkExtensions {
    public static final GzipSink gzip(Sink sink) {
        CloseableKt.checkNotNullParameter(sink, "<this>");
        return new GzipSink(sink);
    }
}
