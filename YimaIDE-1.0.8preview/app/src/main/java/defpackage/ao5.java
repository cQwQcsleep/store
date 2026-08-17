package defpackage;

import io.vavr.Function1;
import io.vavr.Function6;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class ao5 implements Function6, Serializable {
    public final /* synthetic */ Function6 b;
    public final /* synthetic */ Function1 c;

    public /* synthetic */ ao5(Function6 function6, Function1 function1) {
        this.b = function6;
        this.c = function1;
    }

    @Override // io.vavr.Function6
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return Function6.V0(this.b, this.c, obj, obj2, obj3, obj4, obj5, obj6);
    }
}
