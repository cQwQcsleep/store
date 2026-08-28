package com.shadow.okhttp3.internal.http;

import com.shadow.kotlin.collections.EmptyList;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import com.shadow.kotlin.text.Regex;
import com.shadow.okhttp3.HttpUrl;
import com.shadow.okhttp3.Interceptor;
import com.shadow.okhttp3.OkHttpClient;
import com.shadow.okhttp3.Request;
import com.shadow.okhttp3.RequestBody;
import com.shadow.okhttp3.Response;
import com.shadow.okhttp3.ResponseBody;
import com.shadow.okhttp3.Route;
import com.shadow.okhttp3.internal.Util;
import com.shadow.okhttp3.internal.connection.Exchange;
import com.shadow.okhttp3.internal.connection.RealCall;
import com.shadow.okhttp3.internal.connection.RealConnection;
import com.shadow.okhttp3.internal.connection.RouteException;
import com.shadow.okhttp3.internal.http2.ConnectionShutdownException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class RetryAndFollowUpInterceptor implements Interceptor {
    public static final Companion Companion = new Companion(null);
    private static final int MAX_FOLLOW_UPS = 20;
    private final OkHttpClient client;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public RetryAndFollowUpInterceptor(OkHttpClient okHttpClient) {
        CloseableKt.checkNotNullParameter(okHttpClient, "client");
        this.client = okHttpClient;
    }

    private final Request buildRedirectRequest(Response response, String str) {
        String strHeader$default;
        HttpUrl httpUrlResolve;
        if (!this.client.followRedirects() || (strHeader$default = Response.header$default(response, "Location", null, 2, null)) == null || (httpUrlResolve = response.request().url().resolve(strHeader$default)) == null) {
            return null;
        }
        if (!CloseableKt.areEqual(httpUrlResolve.scheme(), response.request().url().scheme()) && !this.client.followSslRedirects()) {
            return null;
        }
        Request.Builder builderNewBuilder = response.request().newBuilder();
        if (HttpMethod.permitsRequestBody(str)) {
            int iCode = response.code();
            HttpMethod httpMethod = HttpMethod.INSTANCE;
            boolean z = httpMethod.redirectsWithBody(str) || iCode == 308 || iCode == 307;
            if (!httpMethod.redirectsToGet(str) || iCode == 308 || iCode == 307) {
                builderNewBuilder.method(str, z ? response.request().body() : null);
            } else {
                builderNewBuilder.method("GET", null);
            }
            if (!z) {
                builderNewBuilder.removeHeader("Transfer-Encoding");
                builderNewBuilder.removeHeader("Content-Length");
                builderNewBuilder.removeHeader("Content-Type");
            }
        }
        if (!Util.canReuseConnectionFor(response.request().url(), httpUrlResolve)) {
            builderNewBuilder.removeHeader("Authorization");
        }
        return builderNewBuilder.url(httpUrlResolve).build();
    }

    private final Request followUpRequest(Response response, Exchange exchange) throws IOException {
        RealConnection connection$okhttp;
        Route route = (exchange == null || (connection$okhttp = exchange.getConnection$okhttp()) == null) ? null : connection$okhttp.route();
        int iCode = response.code();
        String strMethod = response.request().method();
        if (iCode != 307 && iCode != 308) {
            if (iCode == 401) {
                return this.client.authenticator().authenticate(route, response);
            }
            if (iCode == 421) {
                RequestBody requestBodyBody = response.request().body();
                if ((requestBodyBody != null && requestBodyBody.isOneShot()) || exchange == null || !exchange.isCoalescedConnection$okhttp()) {
                    return null;
                }
                exchange.getConnection$okhttp().noCoalescedConnections$okhttp();
                return response.request();
            }
            if (iCode == 503) {
                Response responsePriorResponse = response.priorResponse();
                if ((responsePriorResponse == null || responsePriorResponse.code() != 503) && retryAfter(response, Integer.MAX_VALUE) == 0) {
                    return response.request();
                }
                return null;
            }
            if (iCode == 407) {
                CloseableKt.checkNotNull(route);
                if (route.proxy().type() == Proxy.Type.HTTP) {
                    return this.client.proxyAuthenticator().authenticate(route, response);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            }
            if (iCode == 408) {
                if (!this.client.retryOnConnectionFailure()) {
                    return null;
                }
                RequestBody requestBodyBody2 = response.request().body();
                if (requestBodyBody2 != null && requestBodyBody2.isOneShot()) {
                    return null;
                }
                Response responsePriorResponse2 = response.priorResponse();
                if ((responsePriorResponse2 == null || responsePriorResponse2.code() != 408) && retryAfter(response, 0) <= 0) {
                    return response.request();
                }
                return null;
            }
            switch (iCode) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return null;
            }
        }
        return buildRedirectRequest(response, strMethod);
    }

    private final boolean isRecoverable(IOException iOException, boolean z) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        return iOException instanceof InterruptedIOException ? (iOException instanceof SocketTimeoutException) && !z : (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    private final boolean recover(IOException iOException, RealCall realCall, Request request, boolean z) {
        if (this.client.retryOnConnectionFailure()) {
            return !(z && requestIsOneShot(iOException, request)) && isRecoverable(iOException, z) && realCall.retryAfterFailure();
        }
        return false;
    }

    private final boolean requestIsOneShot(IOException iOException, Request request) {
        RequestBody requestBodyBody = request.body();
        return (requestBodyBody != null && requestBodyBody.isOneShot()) || (iOException instanceof FileNotFoundException);
    }

    private final int retryAfter(Response response, int i) throws NumberFormatException {
        String strHeader$default = Response.header$default(response, "Retry-After", null, 2, null);
        if (strHeader$default == null) {
            return i;
        }
        if (!new Regex("\\d+").matches(strHeader$default)) {
            return Integer.MAX_VALUE;
        }
        Integer numValueOf = Integer.valueOf(strHeader$default);
        CloseableKt.checkNotNullExpressionValue(numValueOf, "valueOf(header)");
        return numValueOf.intValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.shadow.okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        EmptyList emptyList;
        Exchange interceptorScopedExchange$okhttp;
        Request requestFollowUpRequest;
        CloseableKt.checkNotNullParameter(chain, "chain");
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        Request request$okhttp = realInterceptorChain.getRequest$okhttp();
        RealCall call$okhttp = realInterceptorChain.getCall$okhttp();
        EmptyList emptyList2 = EmptyList.INSTANCE;
        Response response = null;
        boolean z = true;
        int i = 0;
        while (true) {
            call$okhttp.enterNetworkInterceptorExchange(request$okhttp, z);
            try {
                if (call$okhttp.isCanceled()) {
                    throw new IOException("Canceled");
                }
                try {
                    Response responseProceed = realInterceptorChain.proceed(request$okhttp);
                    if (response != null) {
                        responseProceed = responseProceed.newBuilder().priorResponse(response.newBuilder().body(null).build()).build();
                    }
                    response = responseProceed;
                    interceptorScopedExchange$okhttp = call$okhttp.getInterceptorScopedExchange$okhttp();
                    requestFollowUpRequest = followUpRequest(response, interceptorScopedExchange$okhttp);
                } catch (RouteException e) {
                    if (!recover(e.getLastConnectException(), call$okhttp, request$okhttp, false)) {
                        throw Util.withSuppressed(e.getFirstConnectException(), emptyList2);
                    }
                    IOException firstConnectException = e.getFirstConnectException();
                    CloseableKt.checkNotNullParameter(emptyList2, "<this>");
                    ArrayList arrayList = new ArrayList(emptyList2.size() + 1);
                    arrayList.addAll(emptyList2);
                    arrayList.add(firstConnectException);
                    emptyList = arrayList;
                    call$okhttp.exitNetworkInterceptorExchange$okhttp(true);
                    emptyList2 = emptyList;
                    z = false;
                } catch (IOException e2) {
                    if (!recover(e2, call$okhttp, request$okhttp, !(e2 instanceof ConnectionShutdownException))) {
                        throw Util.withSuppressed(e2, emptyList2);
                    }
                    CloseableKt.checkNotNullParameter(emptyList2, "<this>");
                    ArrayList arrayList2 = new ArrayList(emptyList2.size() + 1);
                    arrayList2.addAll(emptyList2);
                    arrayList2.add(e2);
                    emptyList = arrayList2;
                    call$okhttp.exitNetworkInterceptorExchange$okhttp(true);
                    emptyList2 = emptyList;
                    z = false;
                }
                if (requestFollowUpRequest == null) {
                    if (interceptorScopedExchange$okhttp != null && interceptorScopedExchange$okhttp.isDuplex$okhttp()) {
                        call$okhttp.timeoutEarlyExit();
                    }
                    call$okhttp.exitNetworkInterceptorExchange$okhttp(false);
                    return response;
                }
                RequestBody requestBodyBody = requestFollowUpRequest.body();
                if (requestBodyBody != null && requestBodyBody.isOneShot()) {
                    call$okhttp.exitNetworkInterceptorExchange$okhttp(false);
                    return response;
                }
                ResponseBody responseBodyBody = response.body();
                if (responseBodyBody != null) {
                    Util.closeQuietly(responseBodyBody);
                }
                i++;
                if (i > MAX_FOLLOW_UPS) {
                    throw new ProtocolException("Too many follow-up requests: " + i);
                }
                call$okhttp.exitNetworkInterceptorExchange$okhttp(true);
                request$okhttp = requestFollowUpRequest;
                z = true;
            } catch (Throwable th) {
                call$okhttp.exitNetworkInterceptorExchange$okhttp(true);
                throw th;
            }
        }
    }
}
