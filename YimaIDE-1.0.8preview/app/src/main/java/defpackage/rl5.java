package defpackage;

import io.vavr.Function2;
import java.io.Serializable;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class rl5 implements Function2, Serializable {
    public final /* synthetic */ Function2 b;
    public final /* synthetic */ Function c;

    public /* synthetic */ rl5(Function2 function2, Function function) {
        this.b = function2;
        this.c = function;
    }

    @Override // io.vavr.Function2, java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return this.c.apply(this.b.apply(obj, obj2));
    }
}
