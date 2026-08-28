package com.shadow.okhttp3.internal.http;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okhttp3.MediaType;
import com.shadow.okhttp3.ResponseBody;
import com.shadow.okio.BufferedSource;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class RealResponseBody extends ResponseBody {
    private final long contentLength;
    private final String contentTypeString;
    private final BufferedSource source;

    public RealResponseBody(String str, long j, BufferedSource bufferedSource) {
        CloseableKt.checkNotNullParameter(bufferedSource, "source");
        this.contentTypeString = str;
        this.contentLength = j;
        this.source = bufferedSource;
    }

    @Override // com.shadow.okhttp3.ResponseBody
    public long contentLength() {
        return this.contentLength;
    }

    @Override // com.shadow.okhttp3.ResponseBody
    public MediaType contentType() {
        String str = this.contentTypeString;
        if (str != null) {
            return MediaType.Companion.parse(str);
        }
        return null;
    }

    @Override // com.shadow.okhttp3.ResponseBody
    public BufferedSource source() {
        return this.source;
    }
}
