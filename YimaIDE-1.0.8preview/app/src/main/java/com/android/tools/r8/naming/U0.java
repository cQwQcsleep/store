package com.android.tools.r8.naming;

import com.android.tools.r8.SourceFileEnvironment;
import com.android.tools.r8.SourceFileProvider;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class U0 implements SourceFileProvider {
    public final /* synthetic */ String a;
    public final /* synthetic */ boolean b;

    public U0(String str, boolean z) {
        this.a = str;
        this.b = z;
    }

    @Override // com.android.tools.r8.SourceFileProvider
    public final boolean allowDiscardingSourceFile() {
        return this.b;
    }

    @Override // com.android.tools.r8.SourceFileProvider
    public final String get(SourceFileEnvironment sourceFileEnvironment) {
        return this.a;
    }
}
