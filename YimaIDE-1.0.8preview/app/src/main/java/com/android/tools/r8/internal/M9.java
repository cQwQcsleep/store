package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class M9 extends XO {
    public final String c;
    public final Map d;

    public M9(String str, Map map) {
        super(589824, null);
        this.c = str;
        this.d = map;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b(int i, WI wi) {
        this.d.put(Integer.valueOf(i), this.c);
    }
}
