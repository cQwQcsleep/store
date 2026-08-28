package com.shadow.okhttp3;

import com.shadow.okio.Timeout;
import java.io.IOException;

/* loaded from: /workspace/unpacked/classes2.dex */
public interface Call extends Cloneable {

    public interface Factory {
        Call newCall(Request request);
    }

    void cancel();

    Call clone();

    void enqueue(Callback callback);

    Response execute() throws IOException;

    boolean isCanceled();

    boolean isExecuted();

    Request request();

    Timeout timeout();
}
