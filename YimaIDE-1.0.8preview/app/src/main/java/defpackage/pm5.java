package defpackage;

import io.vavr.Function4;
import java.io.Serializable;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class pm5 implements Function4, Serializable {
    public final /* synthetic */ Function4 b;
    public final /* synthetic */ Function c;

    public /* synthetic */ pm5(Function4 function4, Function function) {
        this.b = function4;
        this.c = function;
    }

    @Override // io.vavr.Function4
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4) {
        return this.c.apply(this.b.apply(obj, obj2, obj3, obj4));
    }
}
