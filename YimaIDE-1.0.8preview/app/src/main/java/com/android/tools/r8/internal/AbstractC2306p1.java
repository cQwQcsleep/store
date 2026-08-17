package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.p1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2306p1 {
    public abstract AbstractC1955kv a();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AbstractC2306p1) {
            return a().equals(((AbstractC2306p1) obj).a());
        }
        return false;
    }

    public final int hashCode() {
        return a().hashCode();
    }

    public final String toString() {
        return a().toString();
    }
}
