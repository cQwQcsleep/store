package defpackage;

import io.vavr.Function1;
import io.vavr.Function7;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class xp5 implements Function1, Serializable {
    public final /* synthetic */ Function7 b;

    public /* synthetic */ xp5(Function7 function7) {
        this.b = function7;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function7.P9(this.b, obj);
    }
}
