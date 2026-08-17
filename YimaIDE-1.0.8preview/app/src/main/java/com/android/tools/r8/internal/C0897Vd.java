package com.android.tools.r8.internal;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Vd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0897Vd implements Spliterator {
    public final /* synthetic */ Spliterator a;
    public final /* synthetic */ Function b;

    public C0897Vd(Spliterator spliterator, Function function) {
        this.a = spliterator;
        this.b = function;
    }

    @Override // java.util.Spliterator
    public final int characteristics() {
        return this.a.characteristics() & (-262);
    }

    @Override // java.util.Spliterator
    public final long estimateSize() {
        return this.a.estimateSize();
    }

    @Override // java.util.Spliterator
    public final void forEachRemaining(final Consumer consumer) {
        Spliterator spliterator = this.a;
        final Function function = this.b;
        spliterator.forEachRemaining(new Consumer() { // from class: eaf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                consumer.accept(function.apply(obj));
            }
        });
    }

    @Override // java.util.Spliterator
    public final boolean tryAdvance(final Consumer consumer) {
        Spliterator spliterator = this.a;
        final Function function = this.b;
        return spliterator.tryAdvance(new Consumer() { // from class: daf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                consumer.accept(function.apply(obj));
            }
        });
    }

    @Override // java.util.Spliterator
    public final Spliterator trySplit() {
        Spliterator spliteratorTrySplit = this.a.trySplit();
        if (spliteratorTrySplit == null) {
            return null;
        }
        Function function = this.b;
        function.getClass();
        return new C0897Vd(spliteratorTrySplit, function);
    }
}
