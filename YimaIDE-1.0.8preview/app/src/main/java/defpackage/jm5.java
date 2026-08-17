package defpackage;

import io.vavr.Function3;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class jm5 implements Function3, Serializable {
    public final /* synthetic */ Object b;

    public /* synthetic */ jm5(Object obj) {
        this.b = obj;
    }

    @Override // io.vavr.Function3
    public final Object apply(Object obj, Object obj2, Object obj3) {
        return Function3.S5(this.b, obj, obj2, obj3);
    }
}
