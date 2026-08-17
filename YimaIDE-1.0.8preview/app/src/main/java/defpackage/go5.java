package defpackage;

import io.vavr.Function6;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class go5 implements Function6, Serializable {
    public final /* synthetic */ Function6 b;

    public /* synthetic */ go5(Function6 function6) {
        this.b = function6;
    }

    @Override // io.vavr.Function6
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return this.b.apply(obj6, obj5, obj4, obj3, obj2, obj);
    }
}
