package io.vavr;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class m implements Function3, Memoized, Serializable {
    public final /* synthetic */ Function3 b;
    public final /* synthetic */ ReentrantLock c;
    public final /* synthetic */ Map d;

    public /* synthetic */ m(Function3 function3, ReentrantLock reentrantLock, Map map) {
        this.b = function3;
        this.c = reentrantLock;
        this.d = map;
    }

    @Override // io.vavr.Function3
    public final Object apply(Object obj, Object obj2, Object obj3) {
        return Function3.U3(this.b, this.c, this.d, obj, obj2, obj3);
    }
}
