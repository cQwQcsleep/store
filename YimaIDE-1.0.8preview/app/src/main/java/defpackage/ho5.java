package defpackage;

import io.vavr.Function1;
import io.vavr.Function6;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class ho5 implements Function1, Serializable {
    public final /* synthetic */ Function6 b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    public /* synthetic */ ho5(Function6 function6, Object obj, Object obj2, Object obj3) {
        this.b = function6;
        this.c = obj;
        this.d = obj2;
        this.e = obj3;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function6.Bb(this.b, this.c, this.d, this.e, obj);
    }
}
