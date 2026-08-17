package com.android.tools.r8.kotlin;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.P40;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class w0 {
    public static final w0 b;
    public final List a;

    static {
        int i = AbstractC0551Hu.c;
        b = new w0(P40.e);
    }

    public w0(AbstractC0551Hu abstractC0551Hu) {
        this.a = abstractC0551Hu;
    }

    public final boolean a(Consumer consumer) {
        if (this == b) {
            return false;
        }
        consumer.accept(this.a);
        return false;
    }
}
