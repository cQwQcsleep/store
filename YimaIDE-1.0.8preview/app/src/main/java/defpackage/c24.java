package defpackage;

import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.internal.EX;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class c24 implements EX {
    public final /* synthetic */ Predicate b;

    public /* synthetic */ c24(Predicate predicate) {
        this.b = predicate;
    }

    @Override // com.android.tools.r8.internal.EX
    public final boolean apply(Object obj) {
        return this.b.test((C0210g1) obj);
    }
}
