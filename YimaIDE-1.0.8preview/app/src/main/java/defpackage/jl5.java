package defpackage;

import io.vavr.Function1;
import io.vavr.Function2;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class jl5 implements Function2, Serializable {
    public final /* synthetic */ Function2 b;
    public final /* synthetic */ Function1 c;

    public /* synthetic */ jl5(Function2 function2, Function1 function1) {
        this.b = function2;
        this.c = function1;
    }

    @Override // io.vavr.Function2, java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return Function2.Xc(this.b, this.c, obj, obj2);
    }
}
