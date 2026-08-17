package com.android.tools.r8.internal;

import defpackage.cih;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kQ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC1911kQ extends Y5 {
    default void a(Y5 y5) {
        y5.forEach(new cih(this));
    }

    void a(Iterable iterable, Object obj);

    Set b(Object obj);

    Object put(Object obj, Object obj2);

    default void putAll(Map map) {
        ((IdentityHashMap) map).forEach(new cih(this));
    }

    default void a(Set set) {
        set.forEach(new Consumer() { // from class: bih
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.b(obj);
            }
        });
    }
}
