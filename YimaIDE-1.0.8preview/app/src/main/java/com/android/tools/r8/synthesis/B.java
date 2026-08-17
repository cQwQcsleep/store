package com.android.tools.r8.synthesis;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.InterfaceC0332x5;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class B implements A {
    public final ConcurrentHashMap a = new ConcurrentHashMap();

    @Override // com.android.tools.r8.synthesis.A
    public final void a(I2 i2, Collection collection) {
        final Set set = (Set) this.a.computeIfAbsent(i2, new Function() { // from class: wj0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ConcurrentHashMap.newKeySet();
            }
        });
        collection.forEach(new Consumer() { // from class: yj0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                set.add(((InterfaceC0332x5) obj).z());
            }
        });
    }

    @Override // com.android.tools.r8.synthesis.A
    public final void forEach(BiConsumer biConsumer) {
        this.a.forEach(biConsumer);
    }

    @Override // com.android.tools.r8.synthesis.A
    public final boolean isEmpty() {
        return this.a.isEmpty();
    }
}
