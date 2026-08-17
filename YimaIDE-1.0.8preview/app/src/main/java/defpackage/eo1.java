package defpackage;

import io.vavr.CheckedFunction6;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class eo1 implements CheckedFunction6, Serializable {
    public final /* synthetic */ CheckedFunction6 b;

    public /* synthetic */ eo1(CheckedFunction6 checkedFunction6) {
        this.b = checkedFunction6;
    }

    @Override // io.vavr.CheckedFunction6
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return this.b.apply(obj6, obj5, obj4, obj3, obj2, obj);
    }
}
