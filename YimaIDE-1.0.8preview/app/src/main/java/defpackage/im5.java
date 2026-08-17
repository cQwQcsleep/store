package defpackage;

import io.vavr.Function3;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class im5 implements Function3, Serializable {
    public final /* synthetic */ Function3 b;

    public /* synthetic */ im5(Function3 function3) {
        this.b = function3;
    }

    @Override // io.vavr.Function3
    public final Object apply(Object obj, Object obj2, Object obj3) {
        return this.b.apply(obj3, obj2, obj);
    }
}
