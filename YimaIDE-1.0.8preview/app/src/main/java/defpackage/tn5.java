package defpackage;

import io.vavr.Function5;
import io.vavr.control.Try;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class tn5 implements Function5, Serializable {
    public final /* synthetic */ Function5 b;

    public /* synthetic */ tn5(Function5 function5) {
        this.b = function5;
    }

    @Override // io.vavr.Function5
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return Try.of(new wn5(this.b, obj, obj2, obj3, obj4, obj5)).toOption();
    }
}
