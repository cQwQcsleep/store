package defpackage;

import io.vavr.CheckedFunction1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class pk1 implements CheckedFunction1, Serializable {
    public final /* synthetic */ Object b;

    public /* synthetic */ pk1(Object obj) {
        this.b = obj;
    }

    @Override // io.vavr.CheckedFunction1
    public final Object apply(Object obj) {
        return CheckedFunction1.l2(this.b, obj);
    }
}
