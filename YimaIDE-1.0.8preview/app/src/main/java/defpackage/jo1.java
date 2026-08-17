package defpackage;

import io.vavr.CheckedFunction6;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class jo1 implements CheckedFunction6, Serializable {
    public final /* synthetic */ CheckedFunction6 b;
    public final /* synthetic */ Function1 c;

    public /* synthetic */ jo1(CheckedFunction6 checkedFunction6, Function1 function1) {
        this.b = checkedFunction6;
        this.c = function1;
    }

    @Override // io.vavr.CheckedFunction6
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return CheckedFunction6.h8(this.b, this.c, obj, obj2, obj3, obj4, obj5, obj6);
    }
}
