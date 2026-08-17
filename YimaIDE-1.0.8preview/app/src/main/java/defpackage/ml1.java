package defpackage;

import io.vavr.CheckedFunction3;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class ml1 implements CheckedFunction3, Serializable {
    public final /* synthetic */ CheckedFunction3 b;
    public final /* synthetic */ Function1 c;

    public /* synthetic */ ml1(CheckedFunction3 checkedFunction3, Function1 function1) {
        this.b = checkedFunction3;
        this.c = function1;
    }

    @Override // io.vavr.CheckedFunction3
    public final Object apply(Object obj, Object obj2, Object obj3) {
        return CheckedFunction3.M9(this.b, this.c, obj, obj2, obj3);
    }
}
