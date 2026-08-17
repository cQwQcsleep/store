package defpackage;

import io.vavr.CheckedFunction5;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class bn1 implements CheckedFunction5, Serializable {
    public final /* synthetic */ CheckedFunction5 b;
    public final /* synthetic */ Function1 c;

    public /* synthetic */ bn1(CheckedFunction5 checkedFunction5, Function1 function1) {
        this.b = checkedFunction5;
        this.c = function1;
    }

    @Override // io.vavr.CheckedFunction5
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return CheckedFunction5.X5(this.b, this.c, obj, obj2, obj3, obj4, obj5);
    }
}
