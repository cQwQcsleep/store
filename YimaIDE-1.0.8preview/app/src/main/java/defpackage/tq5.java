package defpackage;

import io.vavr.Function1;
import io.vavr.Function8;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class tq5 implements Function1, Serializable {
    public final /* synthetic */ Function8 b;

    public /* synthetic */ tq5(Function8 function8) {
        this.b = function8;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function8.P2(this.b, obj);
    }
}
