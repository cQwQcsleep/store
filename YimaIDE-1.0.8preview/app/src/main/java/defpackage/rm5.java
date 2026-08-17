package defpackage;

import io.vavr.Function1;
import io.vavr.Function4;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class rm5 implements Function1, Serializable {
    public final /* synthetic */ Function4 b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ rm5(Function4 function4, Object obj, Object obj2) {
        this.b = function4;
        this.c = obj;
        this.d = obj2;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function4.i1(this.b, this.c, this.d, obj);
    }
}
