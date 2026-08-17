package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C0822Sg;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Sg, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0822Sg {
    public static /* synthetic */ void a(Set set, Consumer consumer, Object obj) {
        if (set.add(obj)) {
            consumer.accept(obj);
        }
    }

    public static <T> Consumer<T> b() {
        return new Consumer() { // from class: o8d
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C0822Sg.a(obj);
            }
        };
    }

    public static <T> InterfaceC1936kh0<T, RuntimeException> c() {
        return new InterfaceC1936kh0() { // from class: r8d
            @Override // com.android.tools.r8.internal.InterfaceC1936kh0
            public final void accept(Object obj) {
                C0822Sg.b(obj);
            }
        };
    }

    public static /* synthetic */ void b(Object obj) {
    }

    public static /* synthetic */ void a(Object obj, Object obj2) {
    }

    public static Consumer a(final Consumer consumer, final Set set) {
        return new Consumer() { // from class: p8d
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C0822Sg.a(set, consumer, obj);
            }
        };
    }

    public static /* synthetic */ void a(Object obj) {
    }

    public static BiConsumer a(final BiFunction biFunction, final Consumer consumer) {
        return new BiConsumer() { // from class: s8d
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                consumer.accept(biFunction.apply(obj, obj2));
            }
        };
    }

    public static BiConsumer a() {
        return new BiConsumer() { // from class: q8d
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                C0822Sg.a(obj, obj2);
            }
        };
    }
}
