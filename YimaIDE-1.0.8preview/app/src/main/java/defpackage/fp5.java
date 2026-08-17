package defpackage;

import io.vavr.Function1;
import io.vavr.Function7;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class fp5 implements Function1, Serializable {
    public final /* synthetic */ Function7 b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ fp5(Function7 function7, Object obj, Object obj2) {
        this.b = function7;
        this.c = obj;
        this.d = obj2;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function7.La(this.b, this.c, this.d, obj);
    }
}
