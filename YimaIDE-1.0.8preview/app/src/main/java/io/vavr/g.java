package io.vavr;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class g implements CheckedFunction6, Memoized, Serializable {
    public final /* synthetic */ CheckedFunction6 b;
    public final /* synthetic */ ReentrantLock c;
    public final /* synthetic */ Map d;

    public /* synthetic */ g(CheckedFunction6 checkedFunction6, ReentrantLock reentrantLock, Map map) {
        this.b = checkedFunction6;
        this.c = reentrantLock;
        this.d = map;
    }

    @Override // io.vavr.CheckedFunction6
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return CheckedFunction6.K6(this.b, this.c, this.d, obj, obj2, obj3, obj4, obj5, obj6);
    }
}
