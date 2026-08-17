package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0285r0;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0346z5;
import com.android.tools.r8.graph.EnumC0272p0;
import com.android.tools.r8.graph.InterfaceC0332x5;
import com.android.tools.r8.shaking.AbstractC3389f0;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class G0 {
    public final M a;
    public final Queue b;

    public G0(M m, ConcurrentLinkedQueue concurrentLinkedQueue) {
        this.a = m;
        this.b = concurrentLinkedQueue;
    }

    public abstract C3439p0 a();

    public abstract void a(B5 b5);

    public abstract void a(B5 b5, C0322w2 c0322w2);

    public abstract void a(com.android.tools.r8.graph.D2 d2);

    public abstract void a(com.android.tools.r8.graph.D2 d2, B5 b5, int i, D1 d1);

    public abstract void a(com.android.tools.r8.graph.D2 d2, M0 m0);

    public abstract void a(com.android.tools.r8.graph.I2 i2, B5 b5);

    public abstract void a(C0245l1 c0245l1, B5 b5);

    public abstract void a(C0322w2 c0322w2, B5 b5, D1 d1);

    public abstract void a(C0322w2 c0322w2, B5 b5, C3477x c3477x);

    public abstract void a(InterfaceC0332x5 interfaceC0332x5, C0285r0 c0285r0, EnumC0272p0 enumC0272p0);

    public abstract void a(C0346z5 c0346z5, B5 b5);

    public abstract void a(C0346z5 c0346z5, InterfaceC0332x5 interfaceC0332x5, D1 d1);

    public abstract void a(com.android.tools.r8.internal.Y1 y1);

    public abstract void a(D1 d1, B5 b5);

    public abstract void a(M0 m0, C0346z5 c0346z5);

    public abstract void a(AbstractC3389f0 abstractC3389f0);

    public final void a(Collection collection) {
        collection.forEach(new Consumer() { // from class: jw5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((AbstractC3389f0) obj);
            }
        });
    }

    public abstract boolean a(B5 b5, InterfaceC0332x5 interfaceC0332x5, D1 d1);

    public abstract void b(B5 b5, C0322w2 c0322w2);

    public abstract void b(com.android.tools.r8.graph.D2 d2, M0 m0);

    public abstract void b(com.android.tools.r8.graph.I2 i2, B5 b5);

    public abstract void b(C0346z5 c0346z5, B5 b5);

    public abstract void c(com.android.tools.r8.graph.D2 d2, M0 m0);

    public abstract void c(C0346z5 c0346z5, B5 b5);
}
