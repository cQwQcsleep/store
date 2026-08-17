package com.android.tools.r8.internal;

import java.util.HashSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gF, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1560gF {
    public abstract void a(RG rg);

    public abstract void a(AbstractC1645hF abstractC1645hF);

    public void a(HashSet hashSet) {
    }

    public boolean equals(Object obj) {
        return this == obj;
    }

    public int hashCode() {
        return System.identityHashCode(this);
    }

    public final String toString() {
        String typeName = getClass().getTypeName();
        return typeName.substring(typeName.lastIndexOf(36) + 1);
    }
}
