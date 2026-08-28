package com.shadow.okhttp3.internal.http;

import com.shadow.kotlin.LazyKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okhttp3.Interceptor;
import com.shadow.okhttp3.Request;
import com.shadow.okhttp3.RequestBody;
import com.shadow.okhttp3.Response;
import com.shadow.okhttp3.ResponseBody;
import com.shadow.okhttp3.internal.Util;
import com.shadow.okhttp3.internal.connection.Exchange;
import com.shadow.okhttp3.internal.http2.ConnectionShutdownException;
import com.shadow.okio.BufferedSink;
import com.shadow.okio.Okio;
import java.io.IOException;
import java.net.ProtocolException;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class CallServerInterceptor implements Interceptor {
    private final boolean forWebSocket;

    public CallServerInterceptor(boolean z) {
        this.forWebSocket = z;
    }

    private final boolean shouldIgnoreAndWaitForRealResponse(int i) {
        if (i == 100) {
            return true;
        }
        return 102 <= i && i < 200;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00e6 A[Catch: IOException -> 0x00b9, TryCatch #0 {IOException -> 0x00b9, blocks: (B:39:0x00ab, B:41:0x00b4, B:44:0x00bc, B:46:0x00e6, B:48:0x00ef, B:49:0x00f2, B:50:0x0116, B:54:0x0121, B:56:0x0140, B:58:0x014e, B:65:0x0164, B:67:0x016a, B:71:0x0177, B:73:0x018c, B:74:0x0194, B:75:0x019e, B:60:0x0159, B:55:0x0130), top: B:83:0x00ab }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0130 A[Catch: IOException -> 0x00b9, TryCatch #0 {IOException -> 0x00b9, blocks: (B:39:0x00ab, B:41:0x00b4, B:44:0x00bc, B:46:0x00e6, B:48:0x00ef, B:49:0x00f2, B:50:0x0116, B:54:0x0121, B:56:0x0140, B:58:0x014e, B:65:0x0164, B:67:0x016a, B:71:0x0177, B:73:0x018c, B:74:0x0194, B:75:0x019e, B:60:0x0159, B:55:0x0130), top: B:83:0x00ab }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0159 A[Catch: IOException -> 0x00b9, TryCatch #0 {IOException -> 0x00b9, blocks: (B:39:0x00ab, B:41:0x00b4, B:44:0x00bc, B:46:0x00e6, B:48:0x00ef, B:49:0x00f2, B:50:0x0116, B:54:0x0121, B:56:0x0140, B:58:0x014e, B:65:0x0164, B:67:0x016a, B:71:0x0177, B:73:0x018c, B:74:0x0194, B:75:0x019e, B:60:0x0159, B:55:0x0130), top: B:83:0x00ab }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0164 A[Catch: IOException -> 0x00b9, TryCatch #0 {IOException -> 0x00b9, blocks: (B:39:0x00ab, B:41:0x00b4, B:44:0x00bc, B:46:0x00e6, B:48:0x00ef, B:49:0x00f2, B:50:0x0116, B:54:0x0121, B:56:0x0140, B:58:0x014e, B:65:0x0164, B:67:0x016a, B:71:0x0177, B:73:0x018c, B:74:0x0194, B:75:0x019e, B:60:0x0159, B:55:0x0130), top: B:83:0x00ab }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x016a A[Catch: IOException -> 0x00b9, TryCatch #0 {IOException -> 0x00b9, blocks: (B:39:0x00ab, B:41:0x00b4, B:44:0x00bc, B:46:0x00e6, B:48:0x00ef, B:49:0x00f2, B:50:0x0116, B:54:0x0121, B:56:0x0140, B:58:0x014e, B:65:0x0164, B:67:0x016a, B:71:0x0177, B:73:0x018c, B:74:0x0194, B:75:0x019e, B:60:0x0159, B:55:0x0130), top: B:83:0x00ab }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0177 A[Catch: IOException -> 0x00b9, TryCatch #0 {IOException -> 0x00b9, blocks: (B:39:0x00ab, B:41:0x00b4, B:44:0x00bc, B:46:0x00e6, B:48:0x00ef, B:49:0x00f2, B:50:0x0116, B:54:0x0121, B:56:0x0140, B:58:0x014e, B:65:0x0164, B:67:0x016a, B:71:0x0177, B:73:0x018c, B:74:0x0194, B:75:0x019e, B:60:0x0159, B:55:0x0130), top: B:83:0x00ab }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.shadow.okhttp3.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response.Builder responseHeaders;
        int iCode;
        Response responseBuild;
        ResponseBody responseBodyBody;
        boolean z;
        CloseableKt.checkNotNullParameter(chain, "chain");
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        Exchange exchange$okhttp = realInterceptorChain.getExchange$okhttp();
        CloseableKt.checkNotNull(exchange$okhttp);
        Request request$okhttp = realInterceptorChain.getRequest$okhttp();
        RequestBody requestBodyBody = request$okhttp.body();
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean z2 = true;
        try {
            exchange$okhttp.writeRequestHeaders(request$okhttp);
            if (!HttpMethod.permitsRequestBody(request$okhttp.method()) || requestBodyBody == null) {
                exchange$okhttp.noRequestBody();
                responseHeaders = null;
            } else {
                if ("100-continue".equalsIgnoreCase(request$okhttp.header("Expect"))) {
                    exchange$okhttp.flushRequest();
                    responseHeaders = exchange$okhttp.readResponseHeaders(true);
                    try {
                        exchange$okhttp.responseHeadersStart();
                        z = false;
                    } catch (IOException e) {
                        e = e;
                        if (e instanceof ConnectionShutdownException) {
                            throw e;
                        }
                        throw e;
                    }
                } else {
                    responseHeaders = null;
                    z = true;
                }
                try {
                    if (responseHeaders != null) {
                        exchange$okhttp.noRequestBody();
                        if (!exchange$okhttp.getConnection$okhttp().isMultiplexed$okhttp()) {
                            exchange$okhttp.noNewExchangesOnConnection();
                        }
                    } else if (requestBodyBody.isDuplex()) {
                        exchange$okhttp.flushRequest();
                        requestBodyBody.writeTo(Okio.buffer(exchange$okhttp.createRequestBody(request$okhttp, true)));
                    } else {
                        BufferedSink bufferedSinkBuffer = Okio.buffer(exchange$okhttp.createRequestBody(request$okhttp, false));
                        requestBodyBody.writeTo(bufferedSinkBuffer);
                        bufferedSinkBuffer.close();
                    }
                    z2 = z;
                } catch (IOException e2) {
                    e = e2;
                    z2 = z;
                    if ((e instanceof ConnectionShutdownException) || !exchange$okhttp.getHasFailure$okhttp()) {
                        throw e;
                    }
                    if (responseHeaders == null) {
                    }
                    Response responseBuild2 = responseHeaders.request(request$okhttp).handshake(exchange$okhttp.getConnection$okhttp().handshake()).sentRequestAtMillis(jCurrentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
                    iCode = responseBuild2.code();
                    if (shouldIgnoreAndWaitForRealResponse(iCode)) {
                    }
                    exchange$okhttp.responseHeadersEnd(responseBuild2);
                    if (this.forWebSocket) {
                    }
                    if (!"close".equalsIgnoreCase(responseBuild.request().header("Connection"))) {
                        exchange$okhttp.noNewExchangesOnConnection();
                    }
                    if (iCode != 204) {
                        responseBodyBody = responseBuild.body();
                        if ((responseBodyBody == null ? responseBodyBody.contentLength() : -1L) > 0) {
                        }
                    }
                    return responseBuild;
                }
            }
            if (requestBodyBody == null || !requestBodyBody.isDuplex()) {
                exchange$okhttp.finishRequest();
            }
            e = null;
        } catch (IOException e3) {
            e = e3;
            responseHeaders = null;
        }
        if (responseHeaders == null) {
            try {
                responseHeaders = exchange$okhttp.readResponseHeaders(false);
                CloseableKt.checkNotNull(responseHeaders);
                if (z2) {
                    exchange$okhttp.responseHeadersStart();
                    z2 = false;
                }
            } catch (IOException e4) {
                if (e == null) {
                    throw e4;
                }
                LazyKt.a(e, e4);
                throw e;
            }
        }
        Response responseBuild22 = responseHeaders.request(request$okhttp).handshake(exchange$okhttp.getConnection$okhttp().handshake()).sentRequestAtMillis(jCurrentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
        iCode = responseBuild22.code();
        if (shouldIgnoreAndWaitForRealResponse(iCode)) {
            Response.Builder responseHeaders2 = exchange$okhttp.readResponseHeaders(false);
            CloseableKt.checkNotNull(responseHeaders2);
            if (z2) {
                exchange$okhttp.responseHeadersStart();
            }
            responseBuild22 = responseHeaders2.request(request$okhttp).handshake(exchange$okhttp.getConnection$okhttp().handshake()).sentRequestAtMillis(jCurrentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
            iCode = responseBuild22.code();
        }
        exchange$okhttp.responseHeadersEnd(responseBuild22);
        responseBuild = (this.forWebSocket || iCode != 101) ? responseBuild22.newBuilder().body(exchange$okhttp.openResponseBody(responseBuild22)).build() : responseBuild22.newBuilder().body(Util.EMPTY_RESPONSE).build();
        if (!"close".equalsIgnoreCase(responseBuild.request().header("Connection")) || "close".equalsIgnoreCase(Response.header$default(responseBuild, "Connection", null, 2, null))) {
            exchange$okhttp.noNewExchangesOnConnection();
        }
        if (iCode != 204 || iCode == 205) {
            responseBodyBody = responseBuild.body();
            if ((responseBodyBody == null ? responseBodyBody.contentLength() : -1L) > 0) {
                StringBuilder sb = new StringBuilder("HTTP ");
                sb.append(iCode);
                sb.append(" had non-zero Content-Length: ");
                ResponseBody responseBodyBody2 = responseBuild.body();
                sb.append(responseBodyBody2 != null ? Long.valueOf(responseBodyBody2.contentLength()) : null);
                throw new ProtocolException(sb.toString());
            }
        }
        return responseBuild;
    }
}
