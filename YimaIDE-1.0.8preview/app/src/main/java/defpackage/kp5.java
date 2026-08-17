package defpackage;

import io.vavr.Function1;
import io.vavr.Function7;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class kp5 implements Function7, Serializable {
    public final /* synthetic */ Function7 b;
    public final /* synthetic */ Function1 c;

    public /* synthetic */ kp5(Function7 function7, Function1 function1) {
        this.b = function7;
        this.c = function1;
    }

    @Override // io.vavr.Function7
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return Function7.cb(this.b, this.c, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }
}
