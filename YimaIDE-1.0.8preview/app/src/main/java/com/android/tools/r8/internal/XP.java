package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class XP extends AbstractC1228cN {
    public final /* synthetic */ YP b;

    public XP(YP yp) {
        this.b = yp;
    }

    @Override // com.android.tools.r8.internal.AbstractC1228cN
    public final Map a() {
        return this.b;
    }

    public final /* synthetic */ Collection c(Object obj) {
        return this.b.d.get(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        Set setKeySet = this.b.d.keySet();
        return new WM(setKeySet.iterator(), new InterfaceC0392Br() { // from class: byf
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.c(obj);
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC1228cN, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (!contains(obj)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Objects.requireNonNull(entry);
        YP yp = this.b;
        yp.d.keySet().remove(entry.getKey());
        return true;
    }
}
