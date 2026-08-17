package com.android.tools.r8.synthesis;

import com.android.tools.r8.graph.AbstractC0327x0;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.InterfaceC0174b0;
import com.android.tools.r8.internal.AbstractC0551Hu;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.synthesis.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3492a implements InterfaceC3510t {
    public static final /* synthetic */ boolean f = true;
    public final AbstractC0327x0 a;
    public final int b;
    public final C3494c c;
    public final AbstractC0551Hu d;
    public final E e;

    public C3492a(int i, AbstractC0327x0 abstractC0327x0, C3494c c3494c, AbstractC0551Hu abstractC0551Hu, E e) {
        this.b = i;
        this.a = abstractC0327x0;
        this.c = c3494c;
        this.d = abstractC0551Hu;
        this.e = e;
        if (f) {
            return;
        }
        boolean z = C3494c.f;
        if (!z) {
            C3494c.a(abstractC0327x0, c3494c.b.keySet());
        }
        if (!z) {
            C3494c.a(abstractC0327x0, c3494c.c.keySet());
        }
        if (z) {
            c3494c.getClass();
        } else {
            C3494c.a(abstractC0327x0, c3494c.e);
        }
    }

    @Override // com.android.tools.r8.synthesis.InterfaceC3510t
    public final InterfaceC0174b0 a(Function function, I2 i2) {
        return (InterfaceC0174b0) function.apply(i2);
    }
}
