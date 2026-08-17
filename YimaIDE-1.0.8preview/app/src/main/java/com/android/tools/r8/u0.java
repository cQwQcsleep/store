package com.android.tools.r8;

import com.android.tools.r8.origin.Origin;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class u0 implements t0 {
    public static final /* synthetic */ boolean c = true;
    public final Origin a;
    public final String b;

    public u0(Origin origin, String str) {
        boolean z = c;
        if (!z && origin == null) {
            x1f.a();
            throw null;
        }
        if (!z && str == null) {
            x1f.a();
            throw null;
        }
        this.a = origin;
        this.b = str;
    }

    @Override // com.android.tools.r8.t0
    public final String a() {
        return this.b;
    }

    @Override // com.android.tools.r8.Resource
    public final Origin getOrigin() {
        return this.a;
    }
}
