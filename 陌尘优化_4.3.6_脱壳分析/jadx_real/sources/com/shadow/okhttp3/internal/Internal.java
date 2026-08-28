package com.shadow.okhttp3.internal;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okhttp3.Cache;
import com.shadow.okhttp3.ConnectionSpec;
import com.shadow.okhttp3.Cookie;
import com.shadow.okhttp3.Headers;
import com.shadow.okhttp3.HttpUrl;
import com.shadow.okhttp3.Request;
import com.shadow.okhttp3.Response;
import javax.net.ssl.SSLSocket;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class Internal {
    public static final Headers.Builder addHeaderLenient(Headers.Builder builder, String str) {
        CloseableKt.checkNotNullParameter(builder, "builder");
        CloseableKt.checkNotNullParameter(str, "line");
        return builder.addLenient$okhttp(str);
    }

    public static final void applyConnectionSpec(ConnectionSpec connectionSpec, SSLSocket sSLSocket, boolean z) {
        CloseableKt.checkNotNullParameter(connectionSpec, "connectionSpec");
        CloseableKt.checkNotNullParameter(sSLSocket, "sslSocket");
        connectionSpec.apply$okhttp(sSLSocket, z);
    }

    public static final Response cacheGet(Cache cache, Request request) {
        CloseableKt.checkNotNullParameter(cache, "cache");
        CloseableKt.checkNotNullParameter(request, "request");
        return cache.get$okhttp(request);
    }

    public static final String cookieToString(Cookie cookie, boolean z) {
        CloseableKt.checkNotNullParameter(cookie, "cookie");
        return cookie.toString$okhttp(z);
    }

    public static final Cookie parseCookie(long j, HttpUrl httpUrl, String str) {
        CloseableKt.checkNotNullParameter(httpUrl, "url");
        CloseableKt.checkNotNullParameter(str, "setCookie");
        return Cookie.Companion.parse$okhttp(j, httpUrl, str);
    }

    public static final Headers.Builder addHeaderLenient(Headers.Builder builder, String str, String str2) {
        CloseableKt.checkNotNullParameter(builder, "builder");
        CloseableKt.checkNotNullParameter(str, "name");
        CloseableKt.checkNotNullParameter(str2, "value");
        return builder.addLenient$okhttp(str, str2);
    }
}
