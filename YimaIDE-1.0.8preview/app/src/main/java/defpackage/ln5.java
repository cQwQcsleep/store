package defpackage;

import io.vavr.Function1;
import io.vavr.Function5;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class ln5 implements Function5, Serializable {
    public final /* synthetic */ Function5 b;
    public final /* synthetic */ Function1 c;

    public /* synthetic */ ln5(Function5 function5, Function1 function1) {
        this.b = function5;
        this.c = function1;
    }

    @Override // io.vavr.Function5
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return Function5.Dc(this.b, this.c, obj, obj2, obj3, obj4, obj5);
    }
}
