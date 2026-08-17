package defpackage;

import io.vavr.Function1;
import io.vavr.Function5;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class xn5 implements Function1, Serializable {
    public final /* synthetic */ Function5 b;

    public /* synthetic */ xn5(Function5 function5) {
        this.b = function5;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function5.H7(this.b, obj);
    }
}
