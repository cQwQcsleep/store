package defpackage;

import io.vavr.Function2;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class ql5 implements Function2, Serializable {
    public final /* synthetic */ Function2 b;

    public /* synthetic */ ql5(Function2 function2) {
        this.b = function2;
    }

    @Override // io.vavr.Function2, java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return this.b.apply(obj2, obj);
    }
}
