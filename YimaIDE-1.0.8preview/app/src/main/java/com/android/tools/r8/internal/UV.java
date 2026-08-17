package com.android.tools.r8.internal;

import com.android.tools.r8.internal.OV;
import com.android.tools.r8.internal.UV;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class UV extends WV {
    public final /* synthetic */ XV p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UV(XV xv, com.android.tools.r8.graph.B5 b5, List list) {
        super(xv, b5, list);
        this.p = xv;
    }

    @Override // com.android.tools.r8.internal.WV
    public final void a(int i, int i2, OV ov) {
        synchronized (this.p.b) {
            ((List) this.p.b.computeIfAbsent(ov, new Function() { // from class: sye
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UV.a((OV) obj);
                }
            })).add(this.a);
        }
    }

    public static /* synthetic */ List a(OV ov) {
        return new ArrayList();
    }
}
