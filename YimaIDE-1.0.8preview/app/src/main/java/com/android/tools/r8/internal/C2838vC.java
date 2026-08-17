package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2838vC;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vC, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2838vC extends AbstractC0728Oq {
    public final /* synthetic */ Iterable b;
    public final /* synthetic */ EX c;

    public C2838vC(Iterable iterable, EX ex) {
        this.b = iterable;
        this.c = ex;
    }

    public static /* synthetic */ void a(EX ex, Consumer consumer, Object obj) {
        if (ex.test(obj)) {
            consumer.accept(obj);
        }
    }

    @Override // java.lang.Iterable
    public final void forEach(final Consumer consumer) {
        consumer.getClass();
        Iterable iterable = this.b;
        final EX ex = this.c;
        iterable.forEach(new Consumer() { // from class: ili
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C2838vC.a(ex, consumer, obj);
            }
        });
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        Iterator it = this.b.iterator();
        EX ex = this.c;
        it.getClass();
        ex.getClass();
        return new FC(it, ex);
    }

    @Override // java.lang.Iterable
    public final Spliterator spliterator() {
        Spliterator spliterator = this.b.spliterator();
        EX ex = this.c;
        spliterator.getClass();
        ex.getClass();
        return new C0923Wd(spliterator, ex);
    }
}
