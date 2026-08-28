package com.shadow.okhttp3.internal.http2;

import com.shadow.kotlin.io.CloseableKt;
import java.io.IOException;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class StreamResetException extends IOException {
    public final ErrorCode errorCode;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamResetException(ErrorCode errorCode) {
        super("stream was reset: " + errorCode);
        CloseableKt.checkNotNullParameter(errorCode, "errorCode");
        this.errorCode = errorCode;
    }
}
