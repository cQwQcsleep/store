package com.android.tools.r8.internal;

import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ht, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0550Ht {
    public final ArrayList a = new ArrayList();

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0550Ht)) {
            return false;
        }
        C0550Ht c0550Ht = (C0550Ht) obj;
        if (c0550Ht.a.size() != this.a.size()) {
            return false;
        }
        for (int i = 0; i < this.a.size(); i++) {
            if (this.a.get(i) != c0550Ht.a.get(i)) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
