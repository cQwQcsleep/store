package defpackage;

import io.vavr.CheckedFunction4;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class rm1 implements CheckedFunction4, Serializable {
    public final /* synthetic */ CheckedFunction4 b;

    public /* synthetic */ rm1(CheckedFunction4 checkedFunction4) {
        this.b = checkedFunction4;
    }

    @Override // io.vavr.CheckedFunction4
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4) {
        return this.b.apply(obj4, obj3, obj2, obj);
    }
}
