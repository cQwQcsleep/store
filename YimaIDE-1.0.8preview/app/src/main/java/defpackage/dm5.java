package defpackage;

import io.vavr.Function1;
import io.vavr.Function3;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class dm5 implements Function1, Serializable {
    public final /* synthetic */ Function3 b;

    public /* synthetic */ dm5(Function3 function3) {
        this.b = function3;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function3.yc(this.b, obj);
    }
}
