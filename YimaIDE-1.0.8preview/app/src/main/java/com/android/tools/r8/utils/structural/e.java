package com.android.tools.r8.utils.structural;

import com.android.tools.r8.graph.H2;
import com.android.tools.r8.naming.AbstractC3345r0;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class e extends d {
    public final ToIntFunction c;

    public e(AbstractC3345r0 abstractC3345r0, ToIntFunction toIntFunction) {
        super(abstractC3345r0);
        this.c = toIntFunction;
    }

    @Override // com.android.tools.r8.utils.structural.c, com.android.tools.r8.utils.structural.AbstractC3519a
    public final int a(H2 h2, H2 h3) {
        if (h2 == h3) {
            return 0;
        }
        return Integer.compare(this.c.applyAsInt(h2), this.c.applyAsInt(h3));
    }
}
