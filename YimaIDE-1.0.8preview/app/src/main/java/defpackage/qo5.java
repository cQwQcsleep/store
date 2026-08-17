package defpackage;

import io.vavr.Function1;
import io.vavr.Function6;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class qo5 implements Function1, Serializable {
    public final /* synthetic */ Function6 b;
    public final /* synthetic */ Object c;

    public /* synthetic */ qo5(Function6 function6, Object obj) {
        this.b = function6;
        this.c = obj;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function6.E7(this.b, this.c, obj);
    }
}
