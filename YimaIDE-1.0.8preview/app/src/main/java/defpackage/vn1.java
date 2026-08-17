package defpackage;

import io.vavr.CheckedFunction6;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class vn1 implements Function1, Serializable {
    public final /* synthetic */ CheckedFunction6 b;

    public /* synthetic */ vn1(CheckedFunction6 checkedFunction6) {
        this.b = checkedFunction6;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return CheckedFunction6.P1(this.b, obj);
    }
}
