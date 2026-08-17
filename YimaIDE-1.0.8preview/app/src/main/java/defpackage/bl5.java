package defpackage;

import io.vavr.Function1;
import io.vavr.Tuple1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class bl5 implements Function1, Serializable {
    public final /* synthetic */ Function1 b;

    public /* synthetic */ bl5(Function1 function1) {
        this.b = function1;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function1.s2(this.b, (Tuple1) obj);
    }
}
