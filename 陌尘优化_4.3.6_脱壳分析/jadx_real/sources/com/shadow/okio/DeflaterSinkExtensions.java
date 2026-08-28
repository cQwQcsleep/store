package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import java.util.zip.Deflater;

/* renamed from: com.shadow.okio.-DeflaterSinkExtensions, reason: invalid class name */
/* loaded from: /workspace/unpacked/classes2.dex */
public final class DeflaterSinkExtensions {
    public static final DeflaterSink deflate(Sink sink, Deflater deflater) {
        CloseableKt.checkNotNullParameter(sink, "<this>");
        CloseableKt.checkNotNullParameter(deflater, "deflater");
        return new DeflaterSink(sink, deflater);
    }

    public static /* synthetic */ DeflaterSink deflate$default(Sink sink, Deflater deflater, int i, Object obj) {
        if ((i & 1) != 0) {
            deflater = new Deflater();
        }
        CloseableKt.checkNotNullParameter(sink, "<this>");
        CloseableKt.checkNotNullParameter(deflater, "deflater");
        return new DeflaterSink(sink, deflater);
    }
}
