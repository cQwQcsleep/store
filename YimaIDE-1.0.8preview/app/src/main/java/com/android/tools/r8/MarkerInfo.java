package com.android.tools.r8;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface MarkerInfo {
    int getMinApi();

    String getRawEncoding();

    String getTool();

    String getVersion();

    boolean hasBackend();

    boolean hasCompilationMode();

    boolean isBackendClassFiles();

    boolean isBackendDexFiles();

    boolean isCompilationModeDebug();

    boolean isCompilationModeRelease();

    boolean isD8();

    boolean isL8();

    boolean isR8();

    boolean isR8ModeCompatibility();

    boolean isR8ModeFull();
}
