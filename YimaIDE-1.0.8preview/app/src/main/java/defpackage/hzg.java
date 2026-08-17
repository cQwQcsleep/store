package defpackage;

import com.android.tools.r8.graph.AbstractC0327x0;
import com.android.tools.r8.graph.I2;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class hzg implements Function {
    public final /* synthetic */ AbstractC0327x0 b;

    public /* synthetic */ hzg(AbstractC0327x0 abstractC0327x0) {
        this.b = abstractC0327x0;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return this.b.g((I2) obj);
    }
}
