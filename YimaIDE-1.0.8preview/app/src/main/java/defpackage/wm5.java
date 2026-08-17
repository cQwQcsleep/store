package defpackage;

import io.vavr.Function4;
import io.vavr.control.Try;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class wm5 implements Function4, Serializable {
    public final /* synthetic */ Function4 b;

    public /* synthetic */ wm5(Function4 function4) {
        this.b = function4;
    }

    @Override // io.vavr.Function4
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4) {
        return Try.of(new qm5(this.b, obj, obj2, obj3, obj4)).toOption();
    }
}
