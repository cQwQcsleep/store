package com.android.tools.r8.internal;

import java.util.AbstractCollection;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class V extends AbstractCollection implements InterfaceC1215cA {
    public boolean a(InterfaceC1215cA interfaceC1215cA) {
        InterfaceC1640hA it = interfaceC1215cA.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (add(it.q())) {
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean add(Integer num) {
        return add(num.intValue());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.android.tools.r8.internal.InterfaceC1215cA
    public final boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        return f(((Integer) obj).intValue());
    }

    public boolean j(int i) {
        InterfaceC1640hA it = iterator();
        while (it.hasNext()) {
            if (i == it.q()) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean remove(Object obj) {
        if (obj == null) {
            return false;
        }
        return j(((Integer) obj).intValue());
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        InterfaceC1640hA it = iterator();
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
            sb.append(String.valueOf(it.q()));
            size = i;
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC1215cA
    public boolean add(int i) {
        throw new UnsupportedOperationException();
    }

    public int[] a(int[] iArr) {
        if (iArr == null || iArr.length < size()) {
            iArr = new int[size()];
        }
        AbstractC1895kA.a(iterator(), iArr);
        return iArr;
    }
}
