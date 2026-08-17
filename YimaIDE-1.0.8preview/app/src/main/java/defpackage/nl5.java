package defpackage;

import io.vavr.Function2;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class nl5 implements Function2, Serializable {
    public final /* synthetic */ Object b;

    public /* synthetic */ nl5(Object obj) {
        this.b = obj;
    }

    @Override // io.vavr.Function2, java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return Function2.d1(this.b, obj, obj2);
    }
}
