package defpackage;

import io.vavr.CheckedFunction3;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class wl1 implements Function1, Serializable {
    public final /* synthetic */ CheckedFunction3 b;

    public /* synthetic */ wl1(CheckedFunction3 checkedFunction3) {
        this.b = checkedFunction3;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return CheckedFunction3.y4(this.b, obj);
    }
}
