package com.shadow.okhttp3;

import java.net.Socket;

/* loaded from: /workspace/unpacked/classes2.dex */
public interface Connection {
    Handshake handshake();

    Protocol protocol();

    Route route();

    Socket socket();
}
