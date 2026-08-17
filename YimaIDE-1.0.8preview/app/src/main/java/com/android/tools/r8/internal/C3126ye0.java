package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ye0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3126ye0 extends Spliterators.AbstractSpliterator {
    public final /* synthetic */ Iterator a;
    public final /* synthetic */ Iterator b;
    public final /* synthetic */ BiFunction c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C3126ye0(long j, int i, Iterator it, Iterator it2, BiFunction biFunction) {
        super(j, i);
        this.a = it;
        this.b = it2;
        this.c = biFunction;
    }

    @Override // java.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        if (!this.a.hasNext() || !this.b.hasNext()) {
            return false;
        }
        consumer.accept(this.c.apply(this.a.next(), this.b.next()));
        return true;
    }
}
