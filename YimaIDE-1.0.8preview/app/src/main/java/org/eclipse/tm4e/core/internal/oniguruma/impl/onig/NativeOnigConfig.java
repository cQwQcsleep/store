package org.eclipse.tm4e.core.internal.oniguruma.impl.onig;

import io.github.rosemoe.oniguruma.OnigNative;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class NativeOnigConfig {
    private static boolean searchInBatch = true;

    public static boolean isAvailable() {
        try {
            OnigNative.releaseRegex(0L);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isSearchInBatch() {
        return searchInBatch;
    }

    public static void setSearchInBatch(boolean z) {
        searchInBatch = z;
    }
}
