package com.android.tools.r8.internal;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Zd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1001Zd implements Spliterator {
    public Spliterator a;
    public final Spliterator b;
    public final Function c;
    public final InterfaceC0975Yd d;
    public int e;
    public long f;

    public AbstractC1001Zd(Spliterator spliterator, Spliterator spliterator2, Function function, InterfaceC0975Yd interfaceC0975Yd, int i, long j) {
        this.a = spliterator;
        this.b = spliterator2;
        this.c = function;
        this.d = interfaceC0975Yd;
        this.e = i;
        this.f = j;
    }

    public final /* synthetic */ void a(Consumer consumer, Object obj) {
        Spliterator spliterator = (Spliterator) this.c.apply(obj);
        if (spliterator != null) {
            spliterator.forEachRemaining(consumer);
        }
    }

    @Override // java.util.Spliterator
    public final int characteristics() {
        return this.e;
    }

    @Override // java.util.Spliterator
    public final long estimateSize() {
        Spliterator spliterator = this.a;
        if (spliterator != null) {
            this.f = Math.max(this.f, spliterator.estimateSize());
        }
        return Math.max(this.f, 0L);
    }

    @Override // java.util.Spliterator
    public final void forEachRemaining(final Consumer consumer) {
        Spliterator spliterator = this.a;
        if (spliterator != null) {
            spliterator.forEachRemaining(consumer);
            this.a = null;
        }
        this.b.forEachRemaining(new Consumer() { // from class: y6g
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(consumer, obj);
            }
        });
        this.f = 0L;
    }

    @Override // java.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        do {
            Spliterator spliterator = this.a;
            if (spliterator != null && spliterator.tryAdvance(consumer)) {
                long j = this.f;
                if (j == Long.MAX_VALUE) {
                    return true;
                }
                this.f = j - 1;
                return true;
            }
            this.a = null;
        } while (this.b.tryAdvance(new Consumer() { // from class: z6g
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(obj);
            }
        }));
        return false;
    }

    @Override // java.util.Spliterator
    public final Spliterator trySplit() {
        Spliterator spliteratorTrySplit = this.b.trySplit();
        if (spliteratorTrySplit == null) {
            Spliterator spliterator = this.a;
            if (spliterator == null) {
                return null;
            }
            this.a = null;
            return spliterator;
        }
        int i = this.e & (-65);
        long jEstimateSize = estimateSize();
        if (jEstimateSize < Long.MAX_VALUE) {
            jEstimateSize /= 2;
            this.f -= jEstimateSize;
            this.e = i;
        }
        Spliterator spliteratorA = this.d.a(this.a, spliteratorTrySplit, this.c, i, jEstimateSize);
        this.a = null;
        return spliteratorA;
    }

    public final /* synthetic */ void a(Object obj) {
        this.a = (Spliterator) this.c.apply(obj);
    }
}
