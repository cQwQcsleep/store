package defpackage;

import io.vavr.CheckedFunction7;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class qp1 implements Function1, Serializable {
    public final /* synthetic */ CheckedFunction7 b;

    public /* synthetic */ qp1(CheckedFunction7 checkedFunction7) {
        this.b = checkedFunction7;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return CheckedFunction7.L2(this.b, obj);
    }
}
