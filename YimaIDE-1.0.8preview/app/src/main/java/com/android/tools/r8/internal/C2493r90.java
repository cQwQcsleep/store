package com.android.tools.r8.internal;

import java.io.Serializable;

/* JADX INFO: renamed from: com.android.tools.r8.internal.r90, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2493r90 implements Serializable {
    public final Throwable b;

    public C2493r90(Throwable th) {
        this.b = th;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C2493r90) && KB.a(this.b, ((C2493r90) obj).b);
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    public final String toString() {
        return "Failure(" + this.b + ')';
    }
}
