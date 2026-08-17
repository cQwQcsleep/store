package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Yu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0992Yu extends AbstractC3066xu {
    public final AbstractC1105av c;

    public C0992Yu(AbstractC1105av abstractC1105av) {
        this.c = abstractC1105av;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            AbstractC1105av abstractC1105av = this.c;
            Object key = entry.getKey();
            Object value = entry.getValue();
            Collection collection = (Collection) abstractC1105av.b().get(key);
            if (collection != null && collection.contains(value)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return this.c.f.m();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Ck0 iterator() {
        AbstractC1105av abstractC1105av = this.c;
        abstractC1105av.getClass();
        return new C0966Xu(abstractC1105av);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.c.g;
    }
}
