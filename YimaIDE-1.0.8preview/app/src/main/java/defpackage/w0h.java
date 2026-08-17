package defpackage;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.XR;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class w0h implements Function {
    public final /* synthetic */ XR b;

    public /* synthetic */ w0h(XR xr) {
        this.b = xr;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return this.b.c((I2) obj);
    }
}
