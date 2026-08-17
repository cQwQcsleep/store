package defpackage;

import io.vavr.CheckedFunction7;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class zo1 implements CheckedFunction7, Serializable {
    public final /* synthetic */ CheckedFunction7 b;
    public final /* synthetic */ Function1 c;

    public /* synthetic */ zo1(CheckedFunction7 checkedFunction7, Function1 function1) {
        this.b = checkedFunction7;
        this.c = function1;
    }

    @Override // io.vavr.CheckedFunction7
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return CheckedFunction7.L4(this.b, this.c, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }
}
