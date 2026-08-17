package defpackage;

import io.vavr.CheckedFunction2;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class wk1 implements CheckedFunction2, Serializable {
    public final /* synthetic */ CheckedFunction2 b;
    public final /* synthetic */ Function1 c;

    public /* synthetic */ wk1(CheckedFunction2 checkedFunction2, Function1 function1) {
        this.b = checkedFunction2;
        this.c = function1;
    }

    @Override // io.vavr.CheckedFunction2
    public final Object apply(Object obj, Object obj2) {
        return CheckedFunction2.V3(this.b, this.c, obj, obj2);
    }
}
