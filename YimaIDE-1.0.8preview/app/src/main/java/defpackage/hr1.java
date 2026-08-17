package defpackage;

import io.vavr.CheckedFunction8;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class hr1 implements Function1, Serializable {
    public final /* synthetic */ CheckedFunction8 b;
    public final /* synthetic */ Object c;

    public /* synthetic */ hr1(CheckedFunction8 checkedFunction8, Object obj) {
        this.b = checkedFunction8;
        this.c = obj;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return CheckedFunction8.nc(this.b, this.c, obj);
    }
}
