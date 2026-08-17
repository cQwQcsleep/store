package defpackage;

import io.vavr.Function3;
import io.vavr.control.Try;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class hm5 implements Function3, Serializable {
    public final /* synthetic */ Function3 b;

    public /* synthetic */ hm5(Function3 function3) {
        this.b = function3;
    }

    @Override // io.vavr.Function3
    public final Object apply(Object obj, Object obj2, Object obj3) {
        return Try.of(new fm5(this.b, obj, obj2, obj3)).toOption();
    }
}
