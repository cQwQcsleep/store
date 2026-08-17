package io.vavr;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class h implements CheckedFunction7, Memoized, Serializable {
    public final /* synthetic */ CheckedFunction7 b;
    public final /* synthetic */ ReentrantLock c;
    public final /* synthetic */ Map d;

    public /* synthetic */ h(CheckedFunction7 checkedFunction7, ReentrantLock reentrantLock, Map map) {
        this.b = checkedFunction7;
        this.c = reentrantLock;
        this.d = map;
    }

    @Override // io.vavr.CheckedFunction7
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return CheckedFunction7.Z1(this.b, this.c, this.d, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }
}
