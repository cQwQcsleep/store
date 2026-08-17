package defpackage;

import io.vavr.CheckedFunction0;
import io.vavr.Function0;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class ak1 implements Function0, Serializable {
    public final /* synthetic */ CheckedFunction0 b;

    public /* synthetic */ ak1(CheckedFunction0 checkedFunction0) {
        this.b = checkedFunction0;
    }

    @Override // io.vavr.Function0
    public final Object apply() {
        return CheckedFunction0.C6(this.b);
    }
}
