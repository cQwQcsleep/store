package io.vavr;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class a implements CheckedFunction0, Memoized, Serializable {
    public final /* synthetic */ Lazy b;

    public /* synthetic */ a(Lazy lazy) {
        this.b = lazy;
    }

    @Override // io.vavr.CheckedFunction0
    public final Object apply() {
        return CheckedFunction0.M4(this.b);
    }
}
