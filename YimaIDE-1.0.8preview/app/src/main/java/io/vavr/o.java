package io.vavr;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class o implements Function5, Memoized, Serializable {
    public final /* synthetic */ Function5 b;
    public final /* synthetic */ ReentrantLock c;
    public final /* synthetic */ Map d;

    public /* synthetic */ o(Function5 function5, ReentrantLock reentrantLock, Map map) {
        this.b = function5;
        this.c = reentrantLock;
        this.d = map;
    }

    @Override // io.vavr.Function5
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return Function5.d4(this.b, this.c, this.d, obj, obj2, obj3, obj4, obj5);
    }
}
