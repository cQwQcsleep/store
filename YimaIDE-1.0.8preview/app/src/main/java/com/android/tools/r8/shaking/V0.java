package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.C1819jJ;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class V0 {
    public static final /* synthetic */ boolean a = true;

    public final void a(Consumer consumer, Consumer consumer2) {
        if (this instanceof T0) {
            consumer.accept(((T0) this).b);
        } else if (a || (this instanceof U0)) {
            consumer2.accept(b());
        } else {
            x1f.a();
        }
    }

    public C1819jJ b() {
        return null;
    }

    public com.android.tools.r8.graph.D2 a() {
        return null;
    }
}
