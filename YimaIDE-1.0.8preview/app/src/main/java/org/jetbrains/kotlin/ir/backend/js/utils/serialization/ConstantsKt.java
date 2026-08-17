package org.jetbrains.kotlin.ir.backend.js.utils.serialization;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.text.Charsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"SerializationCharset", "Ljava/nio/charset/Charset;", "getSerializationCharset", "()Ljava/nio/charset/Charset;", "org.jetbrains.kotlin:backend.js"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ConstantsKt {
    private static final Charset SerializationCharset = Charsets.UTF_8;

    public static final Charset getSerializationCharset() {
        return SerializationCharset;
    }
}
