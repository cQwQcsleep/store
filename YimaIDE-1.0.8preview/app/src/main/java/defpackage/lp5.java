package defpackage;

import io.vavr.Function1;
import io.vavr.Function7;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class lp5 implements Function1, Serializable {
    public final /* synthetic */ Function7 b;
    public final /* synthetic */ Object c;

    public /* synthetic */ lp5(Function7 function7, Object obj) {
        this.b = function7;
        this.c = obj;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function7.z0(this.b, this.c, obj);
    }
}
