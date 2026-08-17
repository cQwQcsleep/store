package com.android.tools.r8.internal;

import com.android.tools.r8.MarkerInfo;

/* JADX INFO: renamed from: com.android.tools.r8.internal.lN, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1994lN implements MarkerInfo {
    public final com.android.tools.r8.dex.W a;

    public C1994lN(com.android.tools.r8.dex.W w) {
        this.a = w;
    }

    @Override // com.android.tools.r8.MarkerInfo
    public final int getMinApi() {
        if (this.a.k()) {
            return this.a.e().intValue();
        }
        return -1;
    }

    @Override // com.android.tools.r8.MarkerInfo
    public final String getRawEncoding() {
        return this.a.toString();
    }

    @Override // com.android.tools.r8.MarkerInfo
    public final String getTool() {
        return this.a.h().toString();
    }

    @Override // com.android.tools.r8.MarkerInfo
    public final String getVersion() {
        return this.a.i();
    }

    @Override // com.android.tools.r8.MarkerInfo
    public final boolean hasBackend() {
        return this.a.a() != null;
    }

    @Override // com.android.tools.r8.MarkerInfo
    public final boolean hasCompilationMode() {
        return this.a.a.b.containsKey("compilation-mode");
    }

    @Override // com.android.tools.r8.MarkerInfo
    public final boolean isBackendClassFiles() {
        return "cf".equals(this.a.a());
    }

    @Override // com.android.tools.r8.MarkerInfo
    public final boolean isBackendDexFiles() {
        return "dex".equals(this.a.a());
    }

    @Override // com.android.tools.r8.MarkerInfo
    public final boolean isCompilationModeDebug() {
        return "debug".equals(this.a.b());
    }

    @Override // com.android.tools.r8.MarkerInfo
    public final boolean isCompilationModeRelease() {
        return "release".equals(this.a.b());
    }

    @Override // com.android.tools.r8.MarkerInfo
    public final boolean isD8() {
        return this.a.b == com.android.tools.r8.dex.W.b.b;
    }

    @Override // com.android.tools.r8.MarkerInfo
    public final boolean isL8() {
        return this.a.b == com.android.tools.r8.dex.W.b.d;
    }

    @Override // com.android.tools.r8.MarkerInfo
    public final boolean isR8() {
        return this.a.b == com.android.tools.r8.dex.W.b.e;
    }

    @Override // com.android.tools.r8.MarkerInfo
    public final boolean isR8ModeCompatibility() {
        return isR8() && "compatibility".equals(this.a.g());
    }

    @Override // com.android.tools.r8.MarkerInfo
    public final boolean isR8ModeFull() {
        return isR8() && "full".equals(this.a.g());
    }
}
