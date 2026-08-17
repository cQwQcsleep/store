package defpackage;

import io.vavr.Function1;
import io.vavr.Function5;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class mn5 implements Function1, Serializable {
    public final /* synthetic */ Function5 b;
    public final /* synthetic */ Object c;

    public /* synthetic */ mn5(Function5 function5, Object obj) {
        this.b = function5;
        this.c = obj;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function5.E5(this.b, this.c, obj);
    }
}
