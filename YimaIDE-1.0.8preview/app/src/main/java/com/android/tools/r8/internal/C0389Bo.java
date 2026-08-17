package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Bo, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0389Bo {
    public static final /* synthetic */ int b = 0;
    public final Map a;

    static {
        new C0389Bo(0);
    }

    public C0389Bo() {
        this.a = new HashMap();
    }

    public final void a(C0703Nr c0703Nr) {
        this.a.put(new C0363Ao(c0703Nr.d.b, c0703Nr.a), c0703Nr);
    }

    public C0389Bo(int i) {
        this.a = Collections.EMPTY_MAP;
    }
}
