package androidx.core.os;

import android.os.Message;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class MessageCompat {
    private MessageCompat() {
    }

    public static boolean isAsynchronous(Message message) {
        return message.isAsynchronous();
    }

    public static void setAsynchronous(Message message, boolean z) {
        message.setAsynchronous(z);
    }
}
