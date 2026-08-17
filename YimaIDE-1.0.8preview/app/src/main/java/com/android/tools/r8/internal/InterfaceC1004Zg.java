package com.android.tools.r8.internal;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C1512fi0;
import com.android.tools.r8.internal.InterfaceC1004Zg;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Zg, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC1004Zg {
    AbstractC1597gi0 a(Object obj, InterfaceC1938ki0 interfaceC1938ki0, Object obj2);

    AbstractC1597gi0 a(Object obj, Object obj2, BiFunction biFunction);

    AbstractC1597gi0 a(Object obj, BiFunction biFunction, AbstractC3159z1 abstractC3159z1);

    Object a();

    default Object a(final Object obj) {
        if (AbstractC0978Yg.a || b(obj)) {
            return AbstractC1683hi0.a(new Function() { // from class: l7g
                @Override // java.util.function.Function
                public final Object apply(Object obj2) {
                    return this.b.c(obj, (Function) obj2);
                }
            });
        }
        x1f.a();
        return null;
    }

    AbstractC1597gi0 b(Object obj, Object obj2, BiFunction biFunction);

    default AbstractC1597gi0 b(final Object obj, final BiFunction biFunction) {
        return a(obj, (Object) null, biFunction).a(new Function() { // from class: g7g
            @Override // java.util.function.Function
            public final Object apply(Object obj2) {
                return this.b.b(obj, biFunction, (C1512fi0) obj2);
            }
        });
    }

    default void c(final Consumer consumer, Object obj) {
        b(consumer, obj);
        a(obj, new BiConsumer() { // from class: e7g
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj2, Object obj3) {
                consumer.accept(obj2);
            }
        });
    }

    AbstractC1597gi0 d(Object obj, BiFunction biFunction);

    default boolean d(final Object obj) {
        return !AbstractC1683hi0.a(0, new Consumer() { // from class: p7g
            @Override // java.util.function.Consumer
            public final void accept(Object obj2) {
                this.b.h(obj, (Function) obj2);
            }
        });
    }

    default Object e(final Object obj) {
        if (AbstractC0978Yg.a || c(obj)) {
            return AbstractC1683hi0.a(new Function() { // from class: u7g
                @Override // java.util.function.Function
                public final Object apply(Object obj2) {
                    return this.b.i(obj, (Function) obj2);
                }
            });
        }
        x1f.a();
        return null;
    }

    default boolean f(final Object obj) {
        return !AbstractC1683hi0.a(0, new Consumer() { // from class: k7g
            @Override // java.util.function.Consumer
            public final void accept(Object obj2) {
                this.b.d(obj, (Function) obj2);
            }
        });
    }

    /* synthetic */ default void h(Object obj, final Function function) {
        c(obj, new BiFunction() { // from class: v7g
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj2, Object obj3) {
                return InterfaceC1004Zg.a(function, obj2, (I2) obj3);
            }
        });
    }

    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    default AbstractC1597gi0 i(Object obj, final Function function) {
        return b(obj, new BiFunction() { // from class: i7g
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj2, Object obj3) {
                return InterfaceC1004Zg.c(function, obj2, obj3);
            }
        });
    }

    default boolean c(final Object obj) {
        return AbstractC1683hi0.a(1, new Consumer() { // from class: t7g
            @Override // java.util.function.Consumer
            public final void accept(Object obj2) {
                this.b.a(obj, (Function) obj2);
            }
        });
    }

    default void f(Object obj, final Function function) {
        d(obj, new BiFunction() { // from class: r7g
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj2, Object obj3) {
                return InterfaceC1004Zg.a(function, obj2, obj3);
            }
        });
    }

    static /* synthetic */ AbstractC1597gi0 c(Function function, Object obj, Object obj2) {
        return (AbstractC1597gi0) function.apply(obj);
    }

    static /* synthetic */ AbstractC1597gi0 d(Function function, Object obj, Object obj2) {
        return (AbstractC1597gi0) function.apply(obj);
    }

    default boolean b(final Object obj) {
        return AbstractC1683hi0.a(1, new Consumer() { // from class: n7g
            @Override // java.util.function.Consumer
            public final void accept(Object obj2) {
                this.b.g(obj, (Function) obj2);
            }
        });
    }

    default void c(Object obj, final BiFunction biFunction) {
        a(obj, new InterfaceC1938ki0() { // from class: j7g
            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
            public final Object a(Object obj2, Object obj3, Object obj4) {
                return InterfaceC1004Zg.a(biFunction, obj2, (I2) obj3, obj4);
            }
        }, (Object) null);
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    default void d(Object obj, final Function function) {
        b(obj, (Object) null, new BiFunction() { // from class: f7g
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj2, Object obj3) {
                return InterfaceC1004Zg.d(function, obj2, obj3);
            }
        });
    }

    static /* synthetic */ AbstractC1597gi0 b(Function function, Object obj, Object obj2) {
        return (AbstractC1597gi0) function.apply(obj);
    }

    /* synthetic */ default AbstractC1597gi0 b(Object obj, BiFunction biFunction, C1512fi0 c1512fi0) {
        return b(obj, c1512fi0.f(), biFunction);
    }

    static /* synthetic */ AbstractC1597gi0 b(BiFunction biFunction, Object obj, com.android.tools.r8.graph.I2 i2, Object obj2) {
        return (AbstractC1597gi0) biFunction.apply(obj, obj2);
    }

    default void b(final Consumer consumer, Object obj) {
        f(obj, new Function() { // from class: s7g
            @Override // java.util.function.Function
            public final Object apply(Object obj2) {
                return InterfaceC1004Zg.a(consumer, obj2);
            }
        });
    }

    static /* synthetic */ AbstractC1597gi0 a(Function function, Object obj, com.android.tools.r8.graph.I2 i2) {
        return (AbstractC1597gi0) function.apply(obj);
    }

    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    default AbstractC1597gi0 g(Object obj, final Function function) {
        return a(obj, new BiFunction() { // from class: q7g
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj2, Object obj3) {
                return InterfaceC1004Zg.b(function, obj2, obj3);
            }
        });
    }

    static /* synthetic */ AbstractC1597gi0 a(Function function, Object obj, Object obj2) {
        return (AbstractC1597gi0) function.apply(obj);
    }

    static /* synthetic */ AbstractC1597gi0 a(BiFunction biFunction, Object obj, com.android.tools.r8.graph.I2 i2, Object obj2) {
        return (AbstractC1597gi0) biFunction.apply(obj, i2);
    }

    default AbstractC1597gi0 a(final Object obj, final BiFunction biFunction) {
        return d(obj, biFunction).a(new Function() { // from class: m7g
            @Override // java.util.function.Function
            public final Object apply(Object obj2) {
                return this.b.a(obj, biFunction, (C1512fi0) obj2);
            }
        });
    }

    /* synthetic */ default AbstractC1597gi0 a(Object obj, final BiFunction biFunction, C1512fi0 c1512fi0) {
        return a(obj, new InterfaceC1938ki0() { // from class: o7g
            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
            public final Object a(Object obj2, Object obj3, Object obj4) {
                return InterfaceC1004Zg.b(biFunction, obj2, (I2) obj3, obj4);
            }
        }, c1512fi0.f());
    }

    static /* synthetic */ AbstractC1597gi0 a(Consumer consumer, Object obj) {
        consumer.accept(obj);
        return C1512fi0.c;
    }

    default void a(Object obj, final BiConsumer biConsumer) {
        c(obj, new BiFunction() { // from class: h7g
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj2, Object obj3) {
                return InterfaceC1004Zg.a(biConsumer, obj2, (I2) obj3);
            }
        });
    }

    static /* synthetic */ AbstractC1597gi0 a(BiConsumer biConsumer, Object obj, com.android.tools.r8.graph.I2 i2) {
        biConsumer.accept(obj, i2);
        return C1512fi0.c;
    }
}
