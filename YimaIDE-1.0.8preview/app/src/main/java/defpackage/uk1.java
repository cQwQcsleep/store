package defpackage;

import io.vavr.CheckedFunction2;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class uk1 implements CheckedFunction2, Serializable {
    public final /* synthetic */ Object b;

    public /* synthetic */ uk1(Object obj) {
        this.b = obj;
    }

    @Override // io.vavr.CheckedFunction2
    public final Object apply(Object obj, Object obj2) {
        return CheckedFunction2.w5(this.b, obj, obj2);
    }
}
