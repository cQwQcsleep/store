package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Su, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0836Su extends AbstractC1955kv {
    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = m().get(entry.getKey());
            if (obj2 != null && obj2.equals(entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return m().m();
    }

    @Override // com.android.tools.r8.internal.AbstractC2554rv, java.util.Collection, java.util.Set
    public int hashCode() {
        return m().hashCode();
    }

    @Override // com.android.tools.r8.internal.AbstractC2554rv
    public boolean i() {
        AbstractC0706Nu abstractC0706NuM = m();
        abstractC0706NuM.getClass();
        return abstractC0706NuM instanceof O40;
    }

    public abstract AbstractC0706Nu m();

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return m().size();
    }
}
