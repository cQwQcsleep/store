package com.android.tools.r8.internal;

import java.io.Reader;
import java.io.StringReader;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2607sb extends C2522rb {
    public C2607sb(String str) {
        super(str);
    }

    @Override // com.android.tools.r8.internal.C2522rb
    public final Reader a() {
        return new StringReader((String) this.a);
    }
}
