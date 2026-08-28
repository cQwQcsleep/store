package com.shadow.okhttp3.internal.http;

import com.shadow.okhttp3.Headers;
import com.shadow.okhttp3.Request;
import com.shadow.okhttp3.Response;
import com.shadow.okhttp3.internal.connection.RealConnection;
import com.shadow.okio.Sink;
import com.shadow.okio.Source;
import java.io.IOException;

/* loaded from: /workspace/unpacked/classes2.dex */
public interface ExchangeCodec {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;

        private Companion() {
        }
    }

    void cancel();

    Sink createRequestBody(Request request, long j) throws IOException;

    void finishRequest() throws IOException;

    void flushRequest() throws IOException;

    RealConnection getConnection();

    Source openResponseBodySource(Response response) throws IOException;

    Response.Builder readResponseHeaders(boolean z) throws IOException;

    long reportedContentLength(Response response) throws IOException;

    Headers trailers() throws IOException;

    void writeRequestHeaders(Request request) throws IOException;
}
