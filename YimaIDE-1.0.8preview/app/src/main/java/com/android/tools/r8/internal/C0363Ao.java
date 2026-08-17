package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ao, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0363Ao {
    public final Object a;
    public final int b;

    public C0363Ao(int i, Object obj) {
        this.a = obj;
        this.b = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0363Ao)) {
            return false;
        }
        C0363Ao c0363Ao = (C0363Ao) obj;
        return this.a == c0363Ao.a && this.b == c0363Ao.b;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.a) * 65535) + this.b;
    }
}
