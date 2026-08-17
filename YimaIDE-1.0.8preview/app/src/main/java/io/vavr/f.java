package io.vavr;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class f implements CheckedFunction5, Memoized, Serializable {
    public final /* synthetic */ CheckedFunction5 b;
    public final /* synthetic */ ReentrantLock c;
    public final /* synthetic */ Map d;

    public /* synthetic */ f(CheckedFunction5 checkedFunction5, ReentrantLock reentrantLock, Map map) {
        this.b = checkedFunction5;
        this.c = reentrantLock;
        this.d = map;
    }

    @Override // io.vavr.CheckedFunction5
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return CheckedFunction5.t5(this.b, this.c, this.d, obj, obj2, obj3, obj4, obj5);
    }
}
