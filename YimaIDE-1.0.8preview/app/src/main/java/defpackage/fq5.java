package defpackage;

import io.vavr.Function8;
import java.io.Serializable;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class fq5 implements Function8, Serializable {
    public final /* synthetic */ Function8 b;
    public final /* synthetic */ Function c;

    public /* synthetic */ fq5(Function8 function8, Function function) {
        this.b = function8;
        this.c = function;
    }

    @Override // io.vavr.Function8
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return this.c.apply(this.b.apply(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8));
    }
}
