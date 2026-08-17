package io.vavr;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class d implements CheckedFunction3, Memoized, Serializable {
    public final /* synthetic */ CheckedFunction3 b;
    public final /* synthetic */ ReentrantLock c;
    public final /* synthetic */ Map d;

    public /* synthetic */ d(CheckedFunction3 checkedFunction3, ReentrantLock reentrantLock, Map map) {
        this.b = checkedFunction3;
        this.c = reentrantLock;
        this.d = map;
    }

    @Override // io.vavr.CheckedFunction3
    public final Object apply(Object obj, Object obj2, Object obj3) {
        return CheckedFunction3.sd(this.b, this.c, this.d, obj, obj2, obj3);
    }
}
