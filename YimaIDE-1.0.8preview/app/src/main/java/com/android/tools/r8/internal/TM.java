package com.android.tools.r8.internal;

import com.android.tools.r8.naming.C3313b;
import com.android.tools.r8.naming.C3331k;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class TM extends SM {
    public final C3313b a;

    public TM(C3313b c3313b) {
        this.a = c3313b;
    }

    @Override // com.android.tools.r8.internal.SM
    public final C3331k a(String str) {
        return this.a.b(str);
    }

    @Override // com.android.tools.r8.internal.SM
    public final String b(String str) {
        return (String) this.a.e.get(str);
    }

    public static SM a(C3313b c3313b) {
        return new TM(c3313b);
    }
}
