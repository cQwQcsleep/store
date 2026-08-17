package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class NC {
    public static JC a(int i, int i2, Object[] objArr) {
        if (i < 0) {
            j2d.a();
            return null;
        }
        DX.a(0, i, objArr.length);
        DX.b(i2, i);
        return i == 0 ? JC.f : new JC(i, i2, objArr);
    }

    public static DC a(Iterable iterable) {
        iterable.getClass();
        return new DC(iterable);
    }

    public static GC a(Iterator it, InterfaceC0392Br interfaceC0392Br) {
        interfaceC0392Br.getClass();
        return new GC(it, interfaceC0392Br);
    }

    public static boolean a(Collection collection, Iterator it) {
        collection.getClass();
        it.getClass();
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= collection.add(it.next());
        }
        return zAdd;
    }
}
