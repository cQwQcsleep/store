package com.android.tools.r8.internal;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class VI extends LinkedHashMap {
    public final int b;
    public final int c;

    public VI() {
        super(8, 0.75f);
        this.b = 8;
        this.c = 8;
    }

    public final void a(Object obj, Object obj2, IA ia) {
        ((Map) computeIfAbsent(obj, new Function() { // from class: t6f
            @Override // java.util.function.Function
            public final Object apply(Object obj3) {
                return this.b.a(obj3);
            }
        })).putIfAbsent(obj2, ia);
    }

    @Override // java.util.LinkedHashMap
    public final boolean removeEldestEntry(Map.Entry entry) {
        return size() > this.b;
    }

    public final /* synthetic */ Map a(Object obj) {
        return new UI(this.c);
    }
}
