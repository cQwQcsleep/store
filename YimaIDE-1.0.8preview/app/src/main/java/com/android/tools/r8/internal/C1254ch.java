package com.android.tools.r8.internal;

import java.io.Serializable;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ch, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1254ch implements Serializable {
    public int b;

    public C1254ch(int i) {
        this.b = i;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C1254ch) && ((C1254ch) obj).b == this.b;
    }

    public final int hashCode() {
        return this.b;
    }

    public final String toString() {
        return Integer.toString(this.b);
    }
}
