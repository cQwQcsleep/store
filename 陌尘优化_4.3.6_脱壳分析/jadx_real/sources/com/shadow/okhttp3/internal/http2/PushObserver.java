package com.shadow.okhttp3.internal.http2;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okio.BufferedSource;
import java.io.IOException;
import java.util.List;

/* loaded from: /workspace/unpacked/classes2.dex */
public interface PushObserver {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final PushObserver CANCEL = new Companion.PushObserverCancel();

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public static final class PushObserverCancel implements PushObserver {
            @Override // com.shadow.okhttp3.internal.http2.PushObserver
            public boolean onData(int i, BufferedSource bufferedSource, int i2, boolean z) throws IOException {
                CloseableKt.checkNotNullParameter(bufferedSource, "source");
                bufferedSource.skip(i2);
                return true;
            }

            @Override // com.shadow.okhttp3.internal.http2.PushObserver
            public boolean onHeaders(int i, List<Header> list, boolean z) {
                CloseableKt.checkNotNullParameter(list, "responseHeaders");
                return true;
            }

            @Override // com.shadow.okhttp3.internal.http2.PushObserver
            public boolean onRequest(int i, List<Header> list) {
                CloseableKt.checkNotNullParameter(list, "requestHeaders");
                return true;
            }

            @Override // com.shadow.okhttp3.internal.http2.PushObserver
            public void onReset(int i, ErrorCode errorCode) {
                CloseableKt.checkNotNullParameter(errorCode, "errorCode");
            }
        }

        private Companion() {
        }
    }

    boolean onData(int i, BufferedSource bufferedSource, int i2, boolean z) throws IOException;

    boolean onHeaders(int i, List<Header> list, boolean z);

    boolean onRequest(int i, List<Header> list);

    void onReset(int i, ErrorCode errorCode);
}
