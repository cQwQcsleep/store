package com.android.tools.r8.utils.structural;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.naming.AbstractC3345r0;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class g extends e {
    public final ToIntFunction d;

    public g(AbstractC3345r0 abstractC3345r0, ToIntFunction toIntFunction, ToIntFunction toIntFunction2) {
        super(abstractC3345r0, toIntFunction);
        this.d = toIntFunction2;
    }

    @Override // com.android.tools.r8.utils.structural.d, com.android.tools.r8.utils.structural.AbstractC3519a
    public final int a(I2 i2, I2 i3) {
        if (i2 == i3) {
            return 0;
        }
        return Integer.compare(this.d.applyAsInt(i2), this.d.applyAsInt(i3));
    }
}
