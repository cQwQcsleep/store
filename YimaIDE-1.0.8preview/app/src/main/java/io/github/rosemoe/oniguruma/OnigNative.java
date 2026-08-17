package io.github.rosemoe.oniguruma;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class OnigNative {
    static {
        System.loadLibrary("oniguruma-binding");
    }

    private static native long nCreateRegex(byte[] bArr, boolean z);

    private static native int[] nRegexSearch(long j, long j2, byte[] bArr, int i, int i2);

    private static native int[] nRegexSearchBatch(long[] jArr, long j, byte[] bArr, int i, int i2);

    public static long newRegex(String str, boolean z) {
        Objects.requireNonNull(str, "pattern can not be null");
        return newRegex(str.getBytes(StandardCharsets.UTF_8), z);
    }

    public static int[] regexSearch(long j, long j2, byte[] bArr, int i, int i2) {
        Objects.requireNonNull(bArr, "string can not be null");
        if (i <= i2 && i >= 0 && i2 <= bArr.length) {
            return nRegexSearch(j, j2, bArr, i, i2);
        }
        we3.a("start:", i, " end:", i2, " str.length:", bArr.length);
        return null;
    }

    public static int[] regexSearchBatch(long[] jArr, long j, byte[] bArr, int i, int i2) {
        if (jArr == null || bArr == null) {
            x0e.a("pointers or string is null");
            return null;
        }
        if (i <= i2 && i >= 0 && i2 <= bArr.length) {
            return nRegexSearchBatch(jArr, j, bArr, i, i2);
        }
        we3.a("start:", i, " end:", i2, " str.length:", bArr.length);
        return null;
    }

    public static native void releaseRegex(long j);

    public static long newRegex(byte[] bArr, boolean z) {
        Objects.requireNonNull(bArr, "pattern can not be null");
        return nCreateRegex(bArr, z);
    }
}
