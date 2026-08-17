package com.android.tools.r8.kotlin;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.internal.AH;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.kotlin.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3294l extends AbstractC3295m {
    public final AH b;

    public C3294l(AH ah) {
        this.b = ah;
    }

    @Override // com.android.tools.r8.shaking.InterfaceC3369b0
    public final void a(InterfaceC0189d1 interfaceC0189d1) {
    }

    @Override // com.android.tools.r8.kotlin.AbstractC3295m
    public final boolean b(Consumer consumer, C0333y c0333y) {
        consumer.accept(this.b);
        return false;
    }
}
