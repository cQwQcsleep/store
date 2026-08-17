package defpackage;

import io.vavr.Function6;
import io.vavr.control.Try;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class vo5 implements Function6, Serializable {
    public final /* synthetic */ Function6 b;

    public /* synthetic */ vo5(Function6 function6) {
        this.b = function6;
    }

    @Override // io.vavr.Function6
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return Try.of(new io5(this.b, obj, obj2, obj3, obj4, obj5, obj6)).toOption();
    }
}
