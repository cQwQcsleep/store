package com.shadow.okhttp3;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okio.ByteString;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class WebSocketListener {
    public void onClosed(WebSocket webSocket, int i, String str) {
        CloseableKt.checkNotNullParameter(webSocket, "webSocket");
        CloseableKt.checkNotNullParameter(str, "reason");
    }

    public void onClosing(WebSocket webSocket, int i, String str) {
        CloseableKt.checkNotNullParameter(webSocket, "webSocket");
        CloseableKt.checkNotNullParameter(str, "reason");
    }

    public void onFailure(WebSocket webSocket, Throwable th, Response response) {
        CloseableKt.checkNotNullParameter(webSocket, "webSocket");
        CloseableKt.checkNotNullParameter(th, "t");
    }

    public void onMessage(WebSocket webSocket, ByteString byteString) {
        CloseableKt.checkNotNullParameter(webSocket, "webSocket");
        CloseableKt.checkNotNullParameter(byteString, "bytes");
    }

    public void onOpen(WebSocket webSocket, Response response) {
        CloseableKt.checkNotNullParameter(webSocket, "webSocket");
        CloseableKt.checkNotNullParameter(response, "response");
    }

    public void onMessage(WebSocket webSocket, String str) {
        CloseableKt.checkNotNullParameter(webSocket, "webSocket");
        CloseableKt.checkNotNullParameter(str, "text");
    }
}
