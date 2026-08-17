package com.android.tools.r8.internal;

import com.android.tools.r8.internal.E4;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class E4 {
    public static Function a(final Consumer consumer) {
        return new Function() { // from class: h34
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return E4.a(consumer, obj);
            }
        };
    }

    public static /* synthetic */ Void a(Consumer consumer, Object obj) {
        consumer.accept(obj);
        return null;
    }
}
