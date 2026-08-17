package defpackage;

import io.vavr.Function1;
import io.vavr.Function2;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class ml5 implements Function1, Serializable {
    public final /* synthetic */ Function2 b;
    public final /* synthetic */ Object c;

    public /* synthetic */ ml5(Function2 function2, Object obj) {
        this.b = function2;
        this.c = obj;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return this.b.apply(this.c, obj);
    }
}
