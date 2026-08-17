package defpackage;

import io.vavr.CheckedFunction8;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class gq1 implements Function1, Serializable {
    public final /* synthetic */ CheckedFunction8 b;

    public /* synthetic */ gq1(CheckedFunction8 checkedFunction8) {
        this.b = checkedFunction8;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return CheckedFunction8.q5(this.b, obj);
    }
}
