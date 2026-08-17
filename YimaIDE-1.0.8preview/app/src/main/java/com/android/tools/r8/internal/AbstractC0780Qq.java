package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC0780Qq;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Qq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0780Qq {
    public static boolean a(Consumer consumer, final Predicate predicate) {
        final E6 e6 = new E6(true);
        consumer.accept(new Consumer() { // from class: c1c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AbstractC0780Qq.a(e6, predicate, obj);
            }
        });
        return e6.e();
    }

    public static void a(E6 e6, Predicate predicate, Object obj) {
        e6.b(predicate.test(obj) && e6.a);
    }
}
