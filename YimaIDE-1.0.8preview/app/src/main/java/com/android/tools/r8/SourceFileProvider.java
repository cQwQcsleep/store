package com.android.tools.r8;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface SourceFileProvider {
    default boolean allowDiscardingSourceFile() {
        return false;
    }

    String get(SourceFileEnvironment sourceFileEnvironment);
}
