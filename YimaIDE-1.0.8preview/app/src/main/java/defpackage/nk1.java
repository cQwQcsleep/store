package defpackage;

import io.vavr.CheckedFunction1;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class nk1 implements CheckedFunction1, Serializable {
    public final /* synthetic */ CheckedFunction1 b;
    public final /* synthetic */ Function1 c;

    public /* synthetic */ nk1(CheckedFunction1 checkedFunction1, Function1 function1) {
        this.b = checkedFunction1;
        this.c = function1;
    }

    @Override // io.vavr.CheckedFunction1
    public final Object apply(Object obj) {
        return CheckedFunction1.od(this.b, this.c, obj);
    }
}
