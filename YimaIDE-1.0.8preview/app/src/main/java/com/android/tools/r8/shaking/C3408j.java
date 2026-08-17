package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.C0173b;
import com.android.tools.r8.graph.C0226i3;
import com.android.tools.r8.graph.C0233j3;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0256m5;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.E0;
import com.android.tools.r8.internal.C1755ib0;
import com.android.tools.r8.shaking.C3408j;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3408j {
    public final Set a = C1755ib0.a();
    public final Set b = C1755ib0.a();

    public final void a(final C3403i c3403i) {
        Set set = this.a;
        Objects.requireNonNull(c3403i);
        set.forEach(new Consumer() { // from class: o9h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c3403i.a((E0) obj);
            }
        });
        Consumer consumer = new Consumer() { // from class: u9h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C0256m5) obj);
            }
        };
        C0256m5 c0256m5 = (C0256m5) c3403i.t;
        c0256m5.getClass();
        consumer.accept(c0256m5);
        c0256m5.a(c3403i);
        final C0226i3 c0226i3 = c3403i.s;
        this.b.forEach(new Consumer() { // from class: aah
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C3408j.a(c0226i3, (C0245l1) obj);
            }
        });
        this.a.clear();
    }

    public final void a(C0245l1 c0245l1) {
        this.b.add(c0245l1);
    }

    public final /* synthetic */ void a(final C0256m5 c0256m5) {
        Set set = this.a;
        Objects.requireNonNull(c0256m5);
        set.forEach(new Consumer() { // from class: eah
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c0256m5.h((D2) obj);
            }
        });
    }

    public static void a(C0226i3 c0226i3, C0245l1 c0245l1) {
        C0233j3 c0233j3 = (C0233j3) c0226i3.a.get(c0245l1);
        if (c0233j3 != null) {
            c0233j3.d = C0173b.a;
        }
    }
}
