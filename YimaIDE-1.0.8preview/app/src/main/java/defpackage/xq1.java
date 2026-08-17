package defpackage;

import io.vavr.CheckedFunction8;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class xq1 implements CheckedFunction8, Serializable {
    public final /* synthetic */ CheckedFunction8 b;

    public /* synthetic */ xq1(CheckedFunction8 checkedFunction8) {
        this.b = checkedFunction8;
    }

    @Override // io.vavr.CheckedFunction8
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return this.b.apply(obj8, obj7, obj6, obj5, obj4, obj3, obj2, obj);
    }
}
