package defpackage;

import io.vavr.Function8;
import io.vavr.control.Try;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class bq5 implements Function8, Serializable {
    public final /* synthetic */ Function8 b;

    public /* synthetic */ bq5(Function8 function8) {
        this.b = function8;
    }

    @Override // io.vavr.Function8
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return Try.of(new lq5(this.b, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8)).toOption();
    }
}
