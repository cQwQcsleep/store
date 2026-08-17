package com.android.tools.r8.internal;

import java.util.HashSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Mb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0661Mb {
    public final String a;
    public final HashSet b = new HashSet();

    public C0661Mb(String str) {
        this.a = str;
    }

    public final void a(String str) {
        if (this.b.add(str)) {
            return;
        }
        throw new IllegalArgumentException(this.a + " '" + str + "' already declared");
    }
}
