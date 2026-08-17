package com.android.tools.r8.internal;

import java.util.AbstractCollection;
import java.util.Collection;

/* JADX INFO: renamed from: com.android.tools.r8.internal.i0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1706i0 extends AbstractCollection implements Collection, Iterable {
    public abstract InterfaceC1481fM a();

    public boolean a(long j) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(Object obj) {
        return a(((Long) obj).longValue());
    }

    public abstract boolean b(long j);

    public boolean c(long j) {
        InterfaceC1481fM interfaceC1481fMA = a();
        while (interfaceC1481fMA.hasNext()) {
            if (j == interfaceC1481fMA.c()) {
                interfaceC1481fMA.remove();
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        return b(((Long) obj).longValue());
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean remove(Object obj) {
        if (obj == null) {
            return false;
        }
        return c(((Long) obj).longValue());
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        InterfaceC1481fM interfaceC1481fMA = a();
        int size = size();
        boolean z = true;
        while (true) {
            int i = size - 1;
            if (size == 0) {
                sb.append("}");
                return sb.toString();
            }
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(String.valueOf(interfaceC1481fMA.c()));
            size = i;
        }
    }
}
