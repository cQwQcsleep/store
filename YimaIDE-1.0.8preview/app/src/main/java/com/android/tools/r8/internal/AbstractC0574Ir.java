package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ir, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0574Ir implements UN, Cloneable {
    public T7 b = T7.b;

    public static void a(Iterable iterable, Collection collection) {
        if (iterable instanceof InterfaceC3186zJ) {
            Iterator it = ((InterfaceC3186zJ) iterable).h().iterator();
            while (it.hasNext()) {
                it.next().getClass();
            }
            collection.addAll((Collection) iterable);
            return;
        }
        if (iterable instanceof Collection) {
            Iterator it2 = iterable.iterator();
            while (it2.hasNext()) {
                it2.next().getClass();
            }
            collection.addAll((Collection) iterable);
            return;
        }
        for (Object obj : iterable) {
            obj.getClass();
            collection.add(obj);
        }
    }

    public abstract AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo);

    public abstract AbstractC0574Ir a(AbstractC0729Or abstractC0729Or);

    public abstract L0 c();

    public abstract AbstractC0729Or d();
}
