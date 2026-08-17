package defpackage;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C1605gm0;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class bge implements Predicate {
    public final /* synthetic */ C1605gm0 b;

    public /* synthetic */ bge(C1605gm0 c1605gm0) {
        this.b = c1605gm0;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return this.b.d((I2) obj);
    }
}
