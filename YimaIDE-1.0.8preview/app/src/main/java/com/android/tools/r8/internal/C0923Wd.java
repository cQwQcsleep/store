package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Wd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0923Wd implements Spliterator, Consumer {
    public Object a = null;
    public final /* synthetic */ Spliterator b;
    public final /* synthetic */ Predicate c;

    public C0923Wd(Spliterator spliterator, Predicate predicate) {
        this.b = spliterator;
        this.c = predicate;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.a = obj;
    }

    @Override // java.util.Spliterator
    public final int characteristics() {
        return this.b.characteristics() & 277;
    }

    @Override // java.util.Spliterator
    public final long estimateSize() {
        return this.b.estimateSize() / 2;
    }

    @Override // java.util.Spliterator
    public final Comparator getComparator() {
        return this.b.getComparator();
    }

    @Override // java.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        while (this.b.tryAdvance(this)) {
            try {
                Object obj = this.a;
                if (this.c.test(obj)) {
                    consumer.accept(obj);
                    this.a = null;
                    return true;
                }
                this.a = null;
            } catch (Throwable th) {
                this.a = null;
                throw th;
            }
        }
        return false;
    }

    @Override // java.util.Spliterator
    public final Spliterator trySplit() {
        Spliterator spliteratorTrySplit = this.b.trySplit();
        if (spliteratorTrySplit == null) {
            return null;
        }
        Predicate predicate = this.c;
        predicate.getClass();
        return new C0923Wd(spliteratorTrySplit, predicate);
    }
}
