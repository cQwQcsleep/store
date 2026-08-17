package defpackage;

import io.vavr.Function1;
import io.vavr.Function8;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class nq5 implements Function1, Serializable {
    public final /* synthetic */ Function8 b;
    public final /* synthetic */ Object c;

    public /* synthetic */ nq5(Function8 function8, Object obj) {
        this.b = function8;
        this.c = obj;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function8.p5(this.b, this.c, obj);
    }
}
