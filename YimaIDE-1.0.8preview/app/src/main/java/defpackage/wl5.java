package defpackage;

import io.vavr.Function1;
import io.vavr.Function3;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class wl5 implements Function3, Serializable {
    public final /* synthetic */ Function3 b;
    public final /* synthetic */ Function1 c;

    public /* synthetic */ wl5(Function3 function3, Function1 function1) {
        this.b = function3;
        this.c = function1;
    }

    @Override // io.vavr.Function3
    public final Object apply(Object obj, Object obj2, Object obj3) {
        return Function3.C0(this.b, this.c, obj, obj2, obj3);
    }
}
