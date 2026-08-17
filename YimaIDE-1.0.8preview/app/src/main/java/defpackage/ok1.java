package defpackage;

import io.vavr.CheckedFunction1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class ok1 implements CheckedFunction1, Serializable {
    public final /* synthetic */ CheckedFunction1 b;
    public final /* synthetic */ CheckedFunction1 c;

    public /* synthetic */ ok1(CheckedFunction1 checkedFunction1, CheckedFunction1 checkedFunction2) {
        this.b = checkedFunction1;
        this.c = checkedFunction2;
    }

    @Override // io.vavr.CheckedFunction1
    public final Object apply(Object obj) {
        return this.c.apply(this.b.apply(obj));
    }
}
