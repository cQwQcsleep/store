package com.android.tools.r8.internal;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2131n extends AbstractCollection implements Cloneable, Collection, Iterable, Set {
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        return ((C1497fb) this).a(((Character) obj).charValue());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        int i;
        if (obj == null) {
            return false;
        }
        char cCharValue = ((Character) obj).charValue();
        C1497fb c1497fb = (C1497fb) this;
        int i2 = c1497fb.c;
        while (true) {
            i = i2 - 1;
            if (i2 == 0) {
                i = -1;
                break;
            }
            if (c1497fb.b[i] == cCharValue) {
                break;
            }
            i2 = i;
        }
        return i != -1;
    }

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
    public final int hashCode() {
        C1497fb c1497fb = (C1497fb) this;
        int i = c1497fb.c;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i - 1;
            if (i == 0) {
                return i2;
            }
            if (i3 >= c1497fb.c) {
                z0e.a();
                return 0;
            }
            i2 += c1497fb.b[i3];
            i = i4;
            i3++;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int i;
        if (obj == null) {
            return false;
        }
        char cCharValue = ((Character) obj).charValue();
        C1497fb c1497fb = (C1497fb) this;
        int i2 = c1497fb.c;
        while (true) {
            i = i2 - 1;
            if (i2 == 0) {
                i = -1;
                break;
            }
            if (c1497fb.b[i] == cCharValue) {
                break;
            }
            i2 = i;
        }
        if (i == -1) {
            return false;
        }
        int i3 = (c1497fb.c - i) - 1;
        for (int i4 = 0; i4 < i3; i4++) {
            char[] cArr = c1497fb.b;
            int i5 = i + i4;
            cArr[i5] = cArr[i5 + 1];
        }
        c1497fb.c--;
        return true;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        StringBuilder sb = new StringBuilder("{");
        C1497fb c1497fb = (C1497fb) this;
        int i = c1497fb.c;
        boolean z = true;
        int i2 = 0;
        while (true) {
            int i3 = i - 1;
            if (i == 0) {
                sb.append("}");
                return sb.toString();
            }
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            if (i2 >= c1497fb.c) {
                z0e.a();
                return null;
            }
            sb.append(String.valueOf(c1497fb.b[i2]));
            i = i3;
            i2++;
        }
    }
}
