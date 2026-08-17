package defpackage;

import io.vavr.Function8;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class qq5 implements Function8, Serializable {
    public final /* synthetic */ Function8 b;

    public /* synthetic */ qq5(Function8 function8) {
        this.b = function8;
    }

    @Override // io.vavr.Function8
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return this.b.apply(obj8, obj7, obj6, obj5, obj4, obj3, obj2, obj);
    }
}
