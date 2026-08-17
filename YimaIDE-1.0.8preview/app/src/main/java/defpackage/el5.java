package defpackage;

import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class el5 implements Function1, Serializable {
    public final /* synthetic */ Function1 b;
    public final /* synthetic */ Function1 c;

    public /* synthetic */ el5(Function1 function1, Function1 function2) {
        this.b = function1;
        this.c = function2;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function1.pa(this.b, this.c, obj);
    }
}
