package defpackage;

import io.vavr.Function7;
import io.vavr.control.Try;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class op5 implements Function7, Serializable {
    public final /* synthetic */ Function7 b;

    public /* synthetic */ op5(Function7 function7) {
        this.b = function7;
    }

    @Override // io.vavr.Function7
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return Try.of(new wp5(this.b, obj, obj2, obj3, obj4, obj5, obj6, obj7)).toOption();
    }
}
