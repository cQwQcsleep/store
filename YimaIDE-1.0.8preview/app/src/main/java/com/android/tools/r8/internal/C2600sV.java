package com.android.tools.r8.internal;

import defpackage.f63;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sV, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2600sV {
    public final String[] a;
    public int b = 0;

    public C2600sV(String[] strArr) {
        this.a = strArr;
    }

    public final String a() {
        int i = this.b;
        String[] strArr = this.a;
        if (i < strArr.length) {
            return strArr[i];
        }
        return null;
    }

    public final String b() {
        int i = this.b;
        if (i < this.a.length) {
            this.b = i + 1;
            return a();
        }
        f63.a("Iterating over the end of argument list.");
        return null;
    }
}
