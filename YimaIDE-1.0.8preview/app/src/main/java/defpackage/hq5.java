package defpackage;

import io.vavr.Function1;
import io.vavr.Function8;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class hq5 implements Function8, Serializable {
    public final /* synthetic */ Function8 b;
    public final /* synthetic */ Function1 c;

    public /* synthetic */ hq5(Function8 function8, Function1 function1) {
        this.b = function8;
        this.c = function1;
    }

    @Override // io.vavr.Function8
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return Function8.Sa(this.b, this.c, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }
}
