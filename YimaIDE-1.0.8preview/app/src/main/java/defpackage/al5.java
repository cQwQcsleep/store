package defpackage;

import io.vavr.Function1;
import java.io.Serializable;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class al5 implements Function1, Serializable {
    public final /* synthetic */ Function1 b;
    public final /* synthetic */ Function c;

    public /* synthetic */ al5(Function1 function1, Function function) {
        this.b = function1;
        this.c = function;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function1.c1(this.b, this.c, obj);
    }
}
