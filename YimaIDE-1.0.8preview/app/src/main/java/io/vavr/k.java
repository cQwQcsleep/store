package io.vavr;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class k implements Function1, Memoized, Serializable {
    public final /* synthetic */ Function1 b;
    public final /* synthetic */ ReentrantLock c;
    public final /* synthetic */ Map d;

    public /* synthetic */ k(Function1 function1, ReentrantLock reentrantLock, Map map) {
        this.b = function1;
        this.c = reentrantLock;
        this.d = map;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function1.sa(this.b, this.c, this.d, obj);
    }
}
