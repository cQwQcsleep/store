package com.google.crypto.tink.internal;

import android.os.Build;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
final class BuildDispatchedCode {
    private BuildDispatchedCode() {
    }

    @Nullable
    public static Integer getApiLevel() {
        return Integer.valueOf(Build.VERSION.SDK_INT);
    }
}
