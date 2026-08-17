package defpackage;

import io.vavr.Function1;
import io.vavr.Function5;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class en5 implements Function1, Serializable {
    public final /* synthetic */ Function5 b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ en5(Function5 function5, Object obj, Object obj2) {
        this.b = function5;
        this.c = obj;
        this.d = obj2;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function5.c8(this.b, this.c, this.d, obj);
    }
}
