package io.vavr;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class n implements Function4, Memoized, Serializable {
    public final /* synthetic */ Function4 b;
    public final /* synthetic */ ReentrantLock c;
    public final /* synthetic */ Map d;

    public /* synthetic */ n(Function4 function4, ReentrantLock reentrantLock, Map map) {
        this.b = function4;
        this.c = reentrantLock;
        this.d = map;
    }

    @Override // io.vavr.Function4
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4) {
        return Function4.Na(this.b, this.c, this.d, obj, obj2, obj3, obj4);
    }
}
