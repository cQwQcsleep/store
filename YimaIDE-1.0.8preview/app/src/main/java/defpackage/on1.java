package defpackage;

import io.vavr.CheckedFunction5;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class on1 implements Function1, Serializable {
    public final /* synthetic */ CheckedFunction5 b;

    public /* synthetic */ on1(CheckedFunction5 checkedFunction5) {
        this.b = checkedFunction5;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return CheckedFunction5.D4(this.b, obj);
    }
}
