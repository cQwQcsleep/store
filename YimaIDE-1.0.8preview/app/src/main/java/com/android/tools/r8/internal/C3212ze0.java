package com.android.tools.r8.internal;

import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ze0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3212ze0 implements Consumer, Spliterator {
    public final Spliterator a;
    public long b;
    public Object c;
    public final /* synthetic */ Be0 d;

    public C3212ze0(Spliterator spliterator, long j, Be0 be0) {
        this.d = be0;
        this.a = spliterator;
        this.b = j;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.c = obj;
    }

    @Override // java.util.Spliterator
    public final int characteristics() {
        return this.a.characteristics() & 16464;
    }

    @Override // java.util.Spliterator
    public final long estimateSize() {
        return this.a.estimateSize();
    }

    @Override // java.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        if (!this.a.tryAdvance(this)) {
            return false;
        }
        try {
            Be0 be0 = this.d;
            Object obj = this.c;
            long j = this.b;
            this.b = 1 + j;
            consumer.accept(be0.a(obj, j));
            return true;
        } finally {
            this.c = null;
        }
    }

    @Override // java.util.Spliterator
    public final Spliterator trySplit() {
        Spliterator spliteratorTrySplit = this.a.trySplit();
        if (spliteratorTrySplit == null) {
            return null;
        }
        long j = this.b;
        C3212ze0 c3212ze0 = new C3212ze0(spliteratorTrySplit, j, this.d);
        this.b = spliteratorTrySplit.getExactSizeIfKnown() + j;
        return c3212ze0;
    }
}
