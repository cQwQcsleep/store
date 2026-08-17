package io.vavr;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class c implements CheckedFunction2, Memoized, Serializable {
    public final /* synthetic */ CheckedFunction2 b;
    public final /* synthetic */ ReentrantLock c;
    public final /* synthetic */ Map d;

    public /* synthetic */ c(CheckedFunction2 checkedFunction2, ReentrantLock reentrantLock, Map map) {
        this.b = checkedFunction2;
        this.c = reentrantLock;
        this.d = map;
    }

    @Override // io.vavr.CheckedFunction2
    public final Object apply(Object obj, Object obj2) {
        return CheckedFunction2.J0(this.b, this.c, this.d, obj, obj2);
    }
}
