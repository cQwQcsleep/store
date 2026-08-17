package defpackage;

import io.vavr.Function1;
import io.vavr.Function4;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class tm5 implements Function4, Serializable {
    public final /* synthetic */ Function4 b;
    public final /* synthetic */ Function1 c;

    public /* synthetic */ tm5(Function4 function4, Function1 function1) {
        this.b = function4;
        this.c = function1;
    }

    @Override // io.vavr.Function4
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4) {
        return Function4.B7(this.b, this.c, obj, obj2, obj3, obj4);
    }
}
