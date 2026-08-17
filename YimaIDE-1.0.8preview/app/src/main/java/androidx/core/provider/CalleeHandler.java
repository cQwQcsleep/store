package androidx.core.provider;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
class CalleeHandler {
    private CalleeHandler() {
    }

    public static Handler create() {
        return Looper.myLooper() == null ? new Handler(Looper.getMainLooper()) : new Handler();
    }
}
