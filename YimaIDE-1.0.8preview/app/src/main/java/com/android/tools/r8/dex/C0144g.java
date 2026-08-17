package com.android.tools.r8.dex;

import com.android.tools.r8.SourceFileEnvironment;
import com.android.tools.r8.naming.J0;

/* JADX INFO: renamed from: com.android.tools.r8.dex.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0144g implements SourceFileEnvironment {
    public final /* synthetic */ J0 a;

    public C0144g(J0 j0) {
        this.a = j0;
    }

    @Override // com.android.tools.r8.SourceFileEnvironment
    public final String getMapHash() {
        return this.a.b;
    }

    @Override // com.android.tools.r8.SourceFileEnvironment
    public final String getMapId() {
        return this.a.a;
    }
}
