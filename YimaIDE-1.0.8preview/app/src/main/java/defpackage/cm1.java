package defpackage;

import io.vavr.CheckedFunction4;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class cm1 implements Function1, Serializable {
    public final /* synthetic */ CheckedFunction4 b;

    public /* synthetic */ cm1(CheckedFunction4 checkedFunction4) {
        this.b = checkedFunction4;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return CheckedFunction4.ea(this.b, obj);
    }
}
