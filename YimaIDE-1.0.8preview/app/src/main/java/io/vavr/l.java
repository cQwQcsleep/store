package io.vavr;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class l implements Function2, Memoized, Serializable {
    public final /* synthetic */ Function2 b;
    public final /* synthetic */ ReentrantLock c;
    public final /* synthetic */ Map d;

    public /* synthetic */ l(Function2 function2, ReentrantLock reentrantLock, Map map) {
        this.b = function2;
        this.c = reentrantLock;
        this.d = map;
    }

    @Override // io.vavr.Function2, java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return Function2.nd(this.b, this.c, this.d, obj, obj2);
    }
}
