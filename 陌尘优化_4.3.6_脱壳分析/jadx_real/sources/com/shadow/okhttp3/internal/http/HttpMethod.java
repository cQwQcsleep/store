package com.shadow.okhttp3.internal.http;

import com.shadow.kotlin.io.CloseableKt;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class HttpMethod {
    public static final HttpMethod INSTANCE = new HttpMethod();

    private HttpMethod() {
    }

    public static final boolean permitsRequestBody(String str) {
        CloseableKt.checkNotNullParameter(str, "method");
        return (str.equals("GET") || str.equals("HEAD")) ? false : true;
    }

    public static final boolean requiresRequestBody(String str) {
        CloseableKt.checkNotNullParameter(str, "method");
        return str.equals("POST") || str.equals("PUT") || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("REPORT");
    }

    public final boolean invalidatesCache(String str) {
        CloseableKt.checkNotNullParameter(str, "method");
        return str.equals("POST") || str.equals("PATCH") || str.equals("PUT") || str.equals("DELETE") || str.equals("MOVE");
    }

    public final boolean redirectsToGet(String str) {
        CloseableKt.checkNotNullParameter(str, "method");
        return !str.equals("PROPFIND");
    }

    public final boolean redirectsWithBody(String str) {
        CloseableKt.checkNotNullParameter(str, "method");
        return str.equals("PROPFIND");
    }
}
