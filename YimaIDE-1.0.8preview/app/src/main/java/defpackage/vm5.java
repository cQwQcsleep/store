package defpackage;

import io.vavr.Function1;
import io.vavr.Function4;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class vm5 implements Function1, Serializable {
    public final /* synthetic */ Function4 b;
    public final /* synthetic */ Object c;

    public /* synthetic */ vm5(Function4 function4, Object obj) {
        this.b = function4;
        this.c = obj;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function4.t0(this.b, this.c, obj);
    }
}
