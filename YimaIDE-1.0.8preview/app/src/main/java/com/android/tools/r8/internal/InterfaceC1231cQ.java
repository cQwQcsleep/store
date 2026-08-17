package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC1314dQ;
import com.android.tools.r8.internal.InterfaceC1231cQ;
import java.util.Collection;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.cQ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC1231cQ extends Collection {
    static /* synthetic */ void a(Consumer consumer, AbstractC1314dQ abstractC1314dQ) {
        Object objB = abstractC1314dQ.b();
        int iA = abstractC1314dQ.a();
        for (int i = 0; i < iA; i++) {
            consumer.accept(objB);
        }
    }

    Set F();

    int a(Object obj);

    int a(Object obj, int i);

    boolean a(int i, Object obj);

    int b(int i, Object obj);

    int b(Object obj);

    Set entrySet();

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    boolean equals(Object obj);

    default void forEach(final Consumer consumer) {
        consumer.getClass();
        entrySet().forEach(new Consumer() { // from class: ljg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                InterfaceC1231cQ.a(consumer, (AbstractC1314dQ) obj);
            }
        });
    }

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    int hashCode();

    @Override // java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1231cQ
    default Spliterator spliterator() {
        return AbstractC1656hQ.a(this);
    }

    default void a(final ObjIntConsumer objIntConsumer) {
        objIntConsumer.getClass();
        entrySet().forEach(new Consumer() { // from class: mjg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AbstractC1314dQ abstractC1314dQ = (AbstractC1314dQ) obj;
                objIntConsumer.accept(abstractC1314dQ.b(), abstractC1314dQ.a());
            }
        });
    }
}
