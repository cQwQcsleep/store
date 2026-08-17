package com.android.tools.r8.kotlin;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.internal.C3015xH;
import com.android.tools.r8.internal.C3099yH;
import com.android.tools.r8.kotlin.C3290h;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.kotlin.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3290h extends AbstractC3295m {
    public final C3296n b;

    public C3290h(C3296n c3296n) {
        this.b = c3296n;
    }

    public static /* synthetic */ void a(Consumer consumer, C3015xH c3015xH) {
        if (c3015xH != null) {
            consumer.accept(new C3099yH(c3015xH));
        }
    }

    @Override // com.android.tools.r8.kotlin.AbstractC3295m
    public final boolean b(final Consumer consumer, C0333y c0333y) {
        return this.b.b(new Consumer() { // from class: dzg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C3290h.a(consumer, (C3015xH) obj);
            }
        }, c0333y);
    }

    @Override // com.android.tools.r8.shaking.InterfaceC3369b0
    public final void a(InterfaceC0189d1 interfaceC0189d1) {
        this.b.a(interfaceC0189d1);
    }
}
