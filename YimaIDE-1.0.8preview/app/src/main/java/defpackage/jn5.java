package defpackage;

import io.vavr.Function5;
import java.io.Serializable;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class jn5 implements Function5, Serializable {
    public final /* synthetic */ Function5 b;
    public final /* synthetic */ Function c;

    public /* synthetic */ jn5(Function5 function5, Function function) {
        this.b = function5;
        this.c = function;
    }

    @Override // io.vavr.Function5
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return this.c.apply(this.b.apply(obj, obj2, obj3, obj4, obj5));
    }
}
