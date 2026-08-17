package defpackage;

import io.vavr.Function3;
import java.io.Serializable;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class am5 implements Function3, Serializable {
    public final /* synthetic */ Function3 b;
    public final /* synthetic */ Function c;

    public /* synthetic */ am5(Function3 function3, Function function) {
        this.b = function3;
        this.c = function;
    }

    @Override // io.vavr.Function3
    public final Object apply(Object obj, Object obj2, Object obj3) {
        return this.c.apply(this.b.apply(obj, obj2, obj3));
    }
}
