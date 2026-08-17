package defpackage;

import io.vavr.Function4;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class bn5 implements Function4, Serializable {
    public final /* synthetic */ Function4 b;

    public /* synthetic */ bn5(Function4 function4) {
        this.b = function4;
    }

    @Override // io.vavr.Function4
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4) {
        return this.b.apply(obj4, obj3, obj2, obj);
    }
}
