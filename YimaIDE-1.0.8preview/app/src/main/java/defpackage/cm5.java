package defpackage;

import io.vavr.Function1;
import io.vavr.Function3;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class cm5 implements Function1, Serializable {
    public final /* synthetic */ Function3 b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ cm5(Function3 function3, Object obj, Object obj2) {
        this.b = function3;
        this.c = obj;
        this.d = obj2;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return this.b.apply(this.c, this.d, obj);
    }
}
