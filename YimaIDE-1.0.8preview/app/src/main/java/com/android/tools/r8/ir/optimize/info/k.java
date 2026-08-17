package com.android.tools.r8.ir.optimize.info;

import com.android.tools.r8.internal.AbstractC0439Dm;
import com.android.tools.r8.internal.Ak0;
import com.android.tools.r8.internal.B1;
import java.util.BitSet;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class k {
    public B1 a = Ak0.a;
    public AbstractC0439Dm b = AbstractC0439Dm.m();
    public boolean c = true;
    public boolean d = false;
    public BitSet e = null;
    public BitSet f = null;
    public int g = -1;
    public boolean h = false;

    public final k a(boolean z, Consumer consumer) {
        if (z) {
            consumer.accept(this);
        }
        return this;
    }
}
