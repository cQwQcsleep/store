package defpackage;

import com.android.tools.r8.graph.B1;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class ubi implements Function {
    public final /* synthetic */ B1 b;

    public /* synthetic */ ubi(B1 b1) {
        this.b = b1;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return this.b.c((String) obj);
    }
}
