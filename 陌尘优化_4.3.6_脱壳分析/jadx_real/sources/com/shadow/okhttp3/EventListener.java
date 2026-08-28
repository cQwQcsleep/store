package com.shadow.okhttp3;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class EventListener {
    public static final Companion Companion = new Companion(null);
    public static final EventListener NONE = new EventListener() { // from class: com.shadow.okhttp3.EventListener$Companion$NONE$1
    };

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public interface Factory {
        EventListener create(Call call);
    }

    public void cacheConditionalHit(Call call, Response response) {
        CloseableKt.checkNotNullParameter(call, "call");
        CloseableKt.checkNotNullParameter(response, "cachedResponse");
    }

    public void cacheHit(Call call, Response response) {
        CloseableKt.checkNotNullParameter(call, "call");
        CloseableKt.checkNotNullParameter(response, "response");
    }

    public void cacheMiss(Call call) {
        CloseableKt.checkNotNullParameter(call, "call");
    }

    public void callEnd(Call call) {
        CloseableKt.checkNotNullParameter(call, "call");
    }

    public void callFailed(Call call, IOException iOException) {
        CloseableKt.checkNotNullParameter(call, "call");
        CloseableKt.checkNotNullParameter(iOException, "ioe");
    }

    public void callStart(Call call) {
        CloseableKt.checkNotNullParameter(call, "call");
    }

    public void canceled(Call call) {
        CloseableKt.checkNotNullParameter(call, "call");
    }

    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol) {
        CloseableKt.checkNotNullParameter(call, "call");
        CloseableKt.checkNotNullParameter(inetSocketAddress, "inetSocketAddress");
        CloseableKt.checkNotNullParameter(proxy, "proxy");
    }

    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol, IOException iOException) {
        CloseableKt.checkNotNullParameter(call, "call");
        CloseableKt.checkNotNullParameter(inetSocketAddress, "inetSocketAddress");
        CloseableKt.checkNotNullParameter(proxy, "proxy");
        CloseableKt.checkNotNullParameter(iOException, "ioe");
    }

    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        CloseableKt.checkNotNullParameter(call, "call");
        CloseableKt.checkNotNullParameter(inetSocketAddress, "inetSocketAddress");
        CloseableKt.checkNotNullParameter(proxy, "proxy");
    }

    public void connectionAcquired(Call call, Connection connection) {
        CloseableKt.checkNotNullParameter(call, "call");
        CloseableKt.checkNotNullParameter(connection, "connection");
    }

    public void connectionReleased(Call call, Connection connection) {
        CloseableKt.checkNotNullParameter(call, "call");
        CloseableKt.checkNotNullParameter(connection, "connection");
    }

    public void dnsEnd(Call call, String str, List<InetAddress> list) {
        CloseableKt.checkNotNullParameter(call, "call");
        CloseableKt.checkNotNullParameter(str, "domainName");
        CloseableKt.checkNotNullParameter(list, "inetAddressList");
    }

    public void dnsStart(Call call, String str) {
        CloseableKt.checkNotNullParameter(call, "call");
        CloseableKt.checkNotNullParameter(str, "domainName");
    }

    public void proxySelectEnd(Call call, HttpUrl httpUrl, List<Proxy> list) {
        CloseableKt.checkNotNullParameter(call, "call");
        CloseableKt.checkNotNullParameter(httpUrl, "url");
        CloseableKt.checkNotNullParameter(list, "proxies");
    }

    public void proxySelectStart(Call call, HttpUrl httpUrl) {
        CloseableKt.checkNotNullParameter(call, "call");
        CloseableKt.checkNotNullParameter(httpUrl, "url");
    }

    public void requestBodyEnd(Call call, long j) {
        CloseableKt.checkNotNullParameter(call, "call");
    }

    public void requestBodyStart(Call call) {
        CloseableKt.checkNotNullParameter(call, "call");
    }

    public void requestFailed(Call call, IOException iOException) {
        CloseableKt.checkNotNullParameter(call, "call");
        CloseableKt.checkNotNullParameter(iOException, "ioe");
    }

    public void requestHeadersEnd(Call call, Request request) {
        CloseableKt.checkNotNullParameter(call, "call");
        CloseableKt.checkNotNullParameter(request, "request");
    }

    public void requestHeadersStart(Call call) {
        CloseableKt.checkNotNullParameter(call, "call");
    }

    public void responseBodyEnd(Call call, long j) {
        CloseableKt.checkNotNullParameter(call, "call");
    }

    public void responseBodyStart(Call call) {
        CloseableKt.checkNotNullParameter(call, "call");
    }

    public void responseFailed(Call call, IOException iOException) {
        CloseableKt.checkNotNullParameter(call, "call");
        CloseableKt.checkNotNullParameter(iOException, "ioe");
    }

    public void responseHeadersEnd(Call call, Response response) {
        CloseableKt.checkNotNullParameter(call, "call");
        CloseableKt.checkNotNullParameter(response, "response");
    }

    public void responseHeadersStart(Call call) {
        CloseableKt.checkNotNullParameter(call, "call");
    }

    public void satisfactionFailure(Call call, Response response) {
        CloseableKt.checkNotNullParameter(call, "call");
        CloseableKt.checkNotNullParameter(response, "response");
    }

    public void secureConnectEnd(Call call, Handshake handshake) {
        CloseableKt.checkNotNullParameter(call, "call");
    }

    public void secureConnectStart(Call call) {
        CloseableKt.checkNotNullParameter(call, "call");
    }
}
