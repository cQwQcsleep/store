package defpackage;

import io.vavr.CheckedFunction8;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class fr1 implements CheckedFunction8, Serializable {
    public final /* synthetic */ CheckedFunction8 b;
    public final /* synthetic */ Function1 c;

    public /* synthetic */ fr1(CheckedFunction8 checkedFunction8, Function1 function1) {
        this.b = checkedFunction8;
        this.c = function1;
    }

    @Override // io.vavr.CheckedFunction8
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return CheckedFunction8.Y5(this.b, this.c, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }
}
