package com.shadow.okhttp3.internal.cache;

import com.shadow.okio.Sink;
import java.io.IOException;

/* loaded from: /workspace/unpacked/classes2.dex */
public interface CacheRequest {
    void abort();

    Sink body() throws IOException;
}
