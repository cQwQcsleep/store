package com.shadow.okhttp3.internal.connection;

import com.shadow.kotlin.LazyKt;
import com.shadow.kotlin.io.CloseableKt;
import java.io.IOException;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class RouteException extends RuntimeException {
    private final IOException firstConnectException;
    private IOException lastConnectException;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RouteException(IOException iOException) {
        super(iOException);
        CloseableKt.checkNotNullParameter(iOException, "firstConnectException");
        this.firstConnectException = iOException;
        this.lastConnectException = iOException;
    }

    public final void addConnectException(IOException iOException) {
        CloseableKt.checkNotNullParameter(iOException, "e");
        LazyKt.a(this.firstConnectException, iOException);
        this.lastConnectException = iOException;
    }

    public final IOException getFirstConnectException() {
        return this.firstConnectException;
    }

    public final IOException getLastConnectException() {
        return this.lastConnectException;
    }
}
