package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Zu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1018Zu extends AbstractC1529fv {
    public final /* synthetic */ AbstractC1105av f;

    public C1018Zu(AbstractC1105av abstractC1105av) {
        this.f = abstractC1105av;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    public final int b(Object obj) {
        Collection collection = (Collection) this.f.f.get(obj);
        if (collection == null) {
            return 0;
        }
        return collection.size();
    }

    @Override // com.android.tools.r8.internal.AbstractC1529fv, com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.f.f.containsKey(obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC1529fv
    /* JADX INFO: renamed from: g */
    public final AbstractC2554rv F() {
        return this.f.f.keySet();
    }

    @Override // com.android.tools.r8.internal.AbstractC1529fv
    public final AbstractC1314dQ j(int i) {
        Map.Entry entry = (Map.Entry) this.f.f.entrySet().a().get(i);
        return new C1485fQ(((Collection) entry.getValue()).size(), entry.getKey());
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.f.g;
    }
}
