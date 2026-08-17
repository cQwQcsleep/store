package com.android.tools.r8.internal;

import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.b0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1111b0 extends V implements Cloneable, InterfaceC3177zA {
    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set = (Set) obj;
        if (set.size() != size()) {
            return false;
        }
        return containsAll(set);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int size = size();
        InterfaceC1640hA it = iterator();
        int iQ = 0;
        while (true) {
            int i = size - 1;
            if (size == 0) {
                return iQ;
            }
            iQ += it.q();
            size = i;
        }
    }

    @Override // com.android.tools.r8.internal.V
    public final boolean j(int i) {
        return k(i);
    }

    public boolean k(int i) {
        return super.j(i);
    }
}
