package com.shadow.okhttp3;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okio.ByteString;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class Credentials {
    public static final Credentials INSTANCE = new Credentials();

    private Credentials() {
    }

    public static final String basic(String str, String str2) {
        CloseableKt.checkNotNullParameter(str, "username");
        CloseableKt.checkNotNullParameter(str2, "password");
        return basic$default(str, str2, null, 4, null);
    }

    public static /* synthetic */ String basic$default(String str, String str2, Charset charset, int i, Object obj) {
        if ((i & 4) != 0) {
            charset = StandardCharsets.ISO_8859_1;
            CloseableKt.checkNotNullExpressionValue(charset, "ISO_8859_1");
        }
        return basic(str, str2, charset);
    }

    public static final String basic(String str, String str2, Charset charset) {
        CloseableKt.checkNotNullParameter(str, "username");
        CloseableKt.checkNotNullParameter(str2, "password");
        CloseableKt.checkNotNullParameter(charset, "charset");
        return "Basic " + ByteString.Companion.encodeString(str + ':' + str2, charset).base64();
    }
}
