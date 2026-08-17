package io.github.rosemoe.sora.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TemporaryCharBuffer {
    private static char[] sTemp;

    public static char[] obtain(int i) {
        char[] cArr;
        synchronized (TemporaryCharBuffer.class) {
            cArr = sTemp;
            sTemp = null;
        }
        return (cArr == null || cArr.length < i) ? new char[i] : cArr;
    }

    public static void recycle(char[] cArr) {
        if (cArr.length > 1000) {
            return;
        }
        synchronized (TemporaryCharBuffer.class) {
            sTemp = cArr;
        }
    }
}
