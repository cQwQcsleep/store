package defpackage;

import io.vavr.CheckedFunction1;
import io.vavr.Tuple1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class ek1 implements CheckedFunction1, Serializable {
    public final /* synthetic */ CheckedFunction1 b;

    public /* synthetic */ ek1(CheckedFunction1 checkedFunction1) {
        this.b = checkedFunction1;
    }

    @Override // io.vavr.CheckedFunction1
    public final Object apply(Object obj) {
        return CheckedFunction1.Oc(this.b, (Tuple1) obj);
    }
}
