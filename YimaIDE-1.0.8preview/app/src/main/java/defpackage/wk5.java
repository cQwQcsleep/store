package defpackage;

import io.vavr.Function0;
import java.io.Serializable;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class wk5 implements Function0, Serializable {
    public final /* synthetic */ Supplier b;

    public /* synthetic */ wk5(Supplier supplier) {
        this.b = supplier;
    }

    @Override // io.vavr.Function0
    public final Object apply() {
        return Function0.F(this.b);
    }
}
