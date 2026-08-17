package defpackage;

import io.vavr.Function1;
import io.vavr.Function4;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class ym5 implements Function1, Serializable {
    public final /* synthetic */ Function4 b;

    public /* synthetic */ ym5(Function4 function4) {
        this.b = function4;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function4.L6(this.b, obj);
    }
}
