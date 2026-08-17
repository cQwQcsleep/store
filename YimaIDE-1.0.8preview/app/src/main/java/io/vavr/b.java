package io.vavr;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class b implements CheckedFunction1, Memoized, Serializable {
    public final /* synthetic */ CheckedFunction1 b;
    public final /* synthetic */ ReentrantLock c;
    public final /* synthetic */ Map d;

    public /* synthetic */ b(CheckedFunction1 checkedFunction1, ReentrantLock reentrantLock, Map map) {
        this.b = checkedFunction1;
        this.c = reentrantLock;
        this.d = map;
    }

    @Override // io.vavr.CheckedFunction1
    public final Object apply(Object obj) {
        return CheckedFunction1.b9(this.b, this.c, this.d, obj);
    }
}
