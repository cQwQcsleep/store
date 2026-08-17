package defpackage;

import io.vavr.Function1;
import io.vavr.Function2;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class ll5 implements Function1, Serializable {
    public final /* synthetic */ Function2 b;

    public /* synthetic */ ll5(Function2 function2) {
        this.b = function2;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function2.H8(this.b, obj);
    }
}
