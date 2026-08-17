package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ve, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2870ve extends AbstractC2943wV implements Serializable {
    public final Comparator b;

    public C2870ve(Comparator comparator) {
        comparator.getClass();
        this.b = comparator;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return this.b.compare(obj, obj2);
    }

    @Override // java.util.Comparator
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C2870ve) {
            return this.b.equals(((C2870ve) obj).b);
        }
        return false;
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    public final String toString() {
        return this.b.toString();
    }
}
