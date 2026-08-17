package defpackage;

import io.vavr.CheckedFunction1;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class fk1 implements Function1, Serializable {
    public final /* synthetic */ CheckedFunction1 b;

    public /* synthetic */ fk1(CheckedFunction1 checkedFunction1) {
        this.b = checkedFunction1;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return CheckedFunction1.h6(this.b, obj);
    }
}
