package defpackage;

import io.vavr.Function1;
import io.vavr.Function6;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class oo5 implements Function1, Serializable {
    public final /* synthetic */ Function6 b;

    public /* synthetic */ oo5(Function6 function6) {
        this.b = function6;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function6.z3(this.b, obj);
    }
}
