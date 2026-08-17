package defpackage;

import io.vavr.CheckedFunction3;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class jl1 implements CheckedFunction3, Serializable {
    public final /* synthetic */ CheckedFunction3 b;

    public /* synthetic */ jl1(CheckedFunction3 checkedFunction3) {
        this.b = checkedFunction3;
    }

    @Override // io.vavr.CheckedFunction3
    public final Object apply(Object obj, Object obj2, Object obj3) {
        return this.b.apply(obj3, obj2, obj);
    }
}
