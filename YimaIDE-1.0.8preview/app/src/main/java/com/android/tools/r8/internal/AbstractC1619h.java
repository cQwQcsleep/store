package com.android.tools.r8.internal;

import java.util.AbstractCollection;

/* JADX INFO: renamed from: com.android.tools.r8.internal.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1619h extends AbstractCollection implements F6 {
    public boolean a(boolean z) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(Object obj) {
        return a(((Boolean) obj).booleanValue());
    }

    public abstract boolean b(boolean z);

    public boolean c(boolean z) {
        K6 it = iterator();
        while (it.hasNext()) {
            if (z == it.n()) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.android.tools.r8.internal.F6
    public final boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        return b(((Boolean) obj).booleanValue());
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean remove(Object obj) {
        if (obj == null) {
            return false;
        }
        return c(((Boolean) obj).booleanValue());
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        StringBuilder sb = new StringBuilder("{");
        K6 it = iterator();
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
            sb.append(String.valueOf(it.n()));
            size = i;
        }
    }
}
