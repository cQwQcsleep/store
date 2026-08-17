package io.vavr;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class e implements CheckedFunction4, Memoized, Serializable {
    public final /* synthetic */ CheckedFunction4 b;
    public final /* synthetic */ ReentrantLock c;
    public final /* synthetic */ Map d;

    public /* synthetic */ e(CheckedFunction4 checkedFunction4, ReentrantLock reentrantLock, Map map) {
        this.b = checkedFunction4;
        this.c = reentrantLock;
        this.d = map;
    }

    @Override // io.vavr.CheckedFunction4
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4) {
        return CheckedFunction4.D3(this.b, this.c, this.d, obj, obj2, obj3, obj4);
    }
}
