package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import java.util.zip.Inflater;

/* renamed from: com.shadow.okio.-InflaterSourceExtensions, reason: invalid class name */
/* loaded from: /workspace/unpacked/classes2.dex */
public final class InflaterSourceExtensions {
    public static final InflaterSource inflate(Source source, Inflater inflater) {
        CloseableKt.checkNotNullParameter(source, "<this>");
        CloseableKt.checkNotNullParameter(inflater, "inflater");
        return new InflaterSource(source, inflater);
    }

    public static /* synthetic */ InflaterSource inflate$default(Source source, Inflater inflater, int i, Object obj) {
        if ((i & 1) != 0) {
            inflater = new Inflater();
        }
        CloseableKt.checkNotNullParameter(source, "<this>");
        CloseableKt.checkNotNullParameter(inflater, "inflater");
        return new InflaterSource(source, inflater);
    }
}
