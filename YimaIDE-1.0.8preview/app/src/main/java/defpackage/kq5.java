package defpackage;

import io.vavr.Function1;
import io.vavr.Function8;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class kq5 implements Function1, Serializable {
    public final /* synthetic */ Function8 b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ kq5(Function8 function8, Object obj, Object obj2) {
        this.b = function8;
        this.c = obj;
        this.d = obj2;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function8.d7(this.b, this.c, this.d, obj);
    }
}
