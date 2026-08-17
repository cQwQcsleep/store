package com.android.tools.r8.internal;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tn, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2717tn {
    public static <K, V> Consumer<Map.Entry<K, V>> a(final BiConsumer<K, V> biConsumer) {
        return new Consumer() { // from class: sdi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Map.Entry entry = (Map.Entry) obj;
                biConsumer.accept(entry.getKey(), entry.getValue());
            }
        };
    }
}
