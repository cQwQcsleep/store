package io.vavr;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class r implements Function8, Memoized, Serializable {
    public final /* synthetic */ Function8 b;
    public final /* synthetic */ ReentrantLock c;
    public final /* synthetic */ Map d;

    public /* synthetic */ r(Function8 function8, ReentrantLock reentrantLock, Map map) {
        this.b = function8;
        this.c = reentrantLock;
        this.d = map;
    }

    @Override // io.vavr.Function8
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return Function8.N(this.b, this.c, this.d, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }
}
