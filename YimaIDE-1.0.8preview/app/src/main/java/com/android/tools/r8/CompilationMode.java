package com.android.tools.r8;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum CompilationMode {
    DEBUG,
    RELEASE;

    CompilationMode() {
    }

    public boolean isDebug() {
        return this == DEBUG;
    }

    public boolean isRelease() {
        return this == RELEASE;
    }
}
