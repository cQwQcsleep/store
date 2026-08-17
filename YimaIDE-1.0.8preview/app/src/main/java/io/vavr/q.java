package io.vavr;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class q implements Function7, Memoized, Serializable {
    public final /* synthetic */ Function7 b;
    public final /* synthetic */ ReentrantLock c;
    public final /* synthetic */ Map d;

    public /* synthetic */ q(Function7 function7, ReentrantLock reentrantLock, Map map) {
        this.b = function7;
        this.c = reentrantLock;
        this.d = map;
    }

    @Override // io.vavr.Function7
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return Function7.J3(this.b, this.c, this.d, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }
}
