package defpackage;

import io.vavr.CheckedFunction5;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class jn1 implements CheckedFunction5, Serializable {
    public final /* synthetic */ CheckedFunction5 b;

    public /* synthetic */ jn1(CheckedFunction5 checkedFunction5) {
        this.b = checkedFunction5;
    }

    @Override // io.vavr.CheckedFunction5
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return this.b.apply(obj5, obj4, obj3, obj2, obj);
    }
}
