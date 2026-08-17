package defpackage;

import io.vavr.CheckedFunction4;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class mm1 implements CheckedFunction4, Serializable {
    public final /* synthetic */ CheckedFunction4 b;
    public final /* synthetic */ Function1 c;

    public /* synthetic */ mm1(CheckedFunction4 checkedFunction4, Function1 function1) {
        this.b = checkedFunction4;
        this.c = function1;
    }

    @Override // io.vavr.CheckedFunction4
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4) {
        return CheckedFunction4.H3(this.b, this.c, obj, obj2, obj3, obj4);
    }
}
