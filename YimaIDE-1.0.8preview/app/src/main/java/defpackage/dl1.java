package defpackage;

import io.vavr.CheckedFunction2;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class dl1 implements CheckedFunction2, Serializable {
    public final /* synthetic */ CheckedFunction2 b;

    public /* synthetic */ dl1(CheckedFunction2 checkedFunction2) {
        this.b = checkedFunction2;
    }

    @Override // io.vavr.CheckedFunction2
    public final Object apply(Object obj, Object obj2) {
        return this.b.apply(obj2, obj);
    }
}
