package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Spliterators;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Ae0 extends Spliterators.AbstractSpliterator {
    public long a;
    public final /* synthetic */ Iterator b;
    public final /* synthetic */ Be0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Ae0(long j, int i, Iterator it, Be0 be0) {
        super(j, i);
        this.b = it;
        this.c = be0;
        this.a = 0L;
    }

    @Override // java.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        if (!this.b.hasNext()) {
            return false;
        }
        Be0 be0 = this.c;
        Object next = this.b.next();
        long j = this.a;
        this.a = 1 + j;
        consumer.accept(be0.a(next, j));
        return true;
    }
}
