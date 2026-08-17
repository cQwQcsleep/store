package defpackage;

import io.vavr.CheckedFunction7;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class pp1 implements CheckedFunction7, Serializable {
    public final /* synthetic */ CheckedFunction7 b;

    public /* synthetic */ pp1(CheckedFunction7 checkedFunction7) {
        this.b = checkedFunction7;
    }

    @Override // io.vavr.CheckedFunction7
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return this.b.apply(obj7, obj6, obj5, obj4, obj3, obj2, obj);
    }
}
