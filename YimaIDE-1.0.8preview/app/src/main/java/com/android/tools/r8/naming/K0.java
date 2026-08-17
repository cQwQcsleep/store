package com.android.tools.r8.naming;

import com.android.tools.r8.MapIdEnvironment;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class K0 implements MapIdEnvironment {
    public final /* synthetic */ String a;

    public K0(String str) {
        this.a = str;
    }

    @Override // com.android.tools.r8.MapIdEnvironment
    public final String getMapHash() {
        return this.a;
    }
}
