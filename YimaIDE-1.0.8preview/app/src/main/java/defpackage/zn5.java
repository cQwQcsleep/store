package defpackage;

import io.vavr.Function6;
import java.io.Serializable;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class zn5 implements Function6, Serializable {
    public final /* synthetic */ Function6 b;
    public final /* synthetic */ Function c;

    public /* synthetic */ zn5(Function6 function6, Function function) {
        this.b = function6;
        this.c = function;
    }

    @Override // io.vavr.Function6
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return this.c.apply(this.b.apply(obj, obj2, obj3, obj4, obj5, obj6));
    }
}
