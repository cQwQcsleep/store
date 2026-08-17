package defpackage;

import io.vavr.Function7;
import java.io.Serializable;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class mp5 implements Function7, Serializable {
    public final /* synthetic */ Function7 b;
    public final /* synthetic */ Function c;

    public /* synthetic */ mp5(Function7 function7, Function function) {
        this.b = function7;
        this.c = function;
    }

    @Override // io.vavr.Function7
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return this.c.apply(this.b.apply(obj, obj2, obj3, obj4, obj5, obj6, obj7));
    }
}
