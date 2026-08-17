package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xo, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3060xo {
    public final C0955Xj a;
    public final int b;

    public C3060xo(C0955Xj c0955Xj, int i) {
        this.a = c0955Xj;
        this.b = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C3060xo)) {
            return false;
        }
        C3060xo c3060xo = (C3060xo) obj;
        return this.a == c3060xo.a && this.b == c3060xo.b;
    }

    public final int hashCode() {
        return (this.a.hashCode() * 65535) + this.b;
    }
}
