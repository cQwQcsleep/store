package com.android.tools.r8.naming;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class J0 {
    public static final /* synthetic */ boolean c = true;
    public final String a;
    public final String b;

    public J0(String str, String str2) {
        boolean z = c;
        if (!z && str == null) {
            x1f.a();
            throw null;
        }
        if (!z && str2 == null) {
            x1f.a();
            throw null;
        }
        this.a = str;
        this.b = str2;
    }

    public final String a() {
        return this.b;
    }

    public final String b() {
        return this.a;
    }
}
