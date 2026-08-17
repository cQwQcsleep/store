package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Xd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0949Xd implements Spliterator {
    public final Spliterator.OfInt a;
    public final /* synthetic */ IntFunction b;
    public final /* synthetic */ int c;
    public final /* synthetic */ Comparator d;

    public C0949Xd(Spliterator.OfInt ofInt, IntFunction intFunction, int i, Comparator comparator) {
        this.b = intFunction;
        this.c = i;
        this.d = comparator;
        this.a = ofInt;
    }

    @Override // java.util.Spliterator
    public final int characteristics() {
        return this.c | 16464;
    }

    @Override // java.util.Spliterator
    public final long estimateSize() {
        return this.a.estimateSize();
    }

    @Override // java.util.Spliterator
    public final void forEachRemaining(final Consumer consumer) {
        Spliterator.OfInt ofInt = this.a;
        final IntFunction intFunction = this.b;
        ofInt.forEachRemaining(new IntConsumer() { // from class: kzf
            @Override // java.util.function.IntConsumer
            public final void accept(int i) {
                consumer.accept(intFunction.apply(i));
            }
        });
    }

    @Override // java.util.Spliterator
    public final Comparator getComparator() {
        if (hasCharacteristics(4)) {
            return this.d;
        }
        g33.a();
        return null;
    }

    @Override // java.util.Spliterator
    public final boolean tryAdvance(final Consumer consumer) {
        Spliterator.OfInt ofInt = this.a;
        final IntFunction intFunction = this.b;
        return ofInt.tryAdvance(new IntConsumer() { // from class: lzf
            @Override // java.util.function.IntConsumer
            public final void accept(int i) {
                consumer.accept(intFunction.apply(i));
            }
        });
    }

    @Override // java.util.Spliterator
    public final Spliterator trySplit() {
        Spliterator.OfInt ofIntTrySplit = this.a.trySplit();
        if (ofIntTrySplit == null) {
            return null;
        }
        return new C0949Xd(ofIntTrySplit, this.b, this.c, this.d);
    }
}
