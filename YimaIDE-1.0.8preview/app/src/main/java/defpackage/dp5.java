package defpackage;

import io.vavr.Function7;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class dp5 implements Function7, Serializable {
    public final /* synthetic */ Function7 b;

    public /* synthetic */ dp5(Function7 function7) {
        this.b = function7;
    }

    @Override // io.vavr.Function7
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return this.b.apply(obj7, obj6, obj5, obj4, obj3, obj2, obj);
    }
}
