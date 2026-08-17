package com.android.tools.r8.internal;

import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.Zg0;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Zg0 {
    public static void a(C2752uB c2752uB, Yg0 yg0, int i, List list) {
        Ch0 ch0A = Ch0.a(c2752uB, "Timing");
        list.set(i, ch0A);
        ch0A.a("Task " + (i + 1));
        yg0.b(ch0A);
        ch0A.b();
        ch0A.b();
    }

    public static void a(final Yg0 yg0, Ng0 ng0) {
        ng0.b(new InterfaceC1681hh0() { // from class: x7g
            @Override // com.android.tools.r8.internal.InterfaceC1681hh0
            public final void b() {
                yg0.b(Ch0.a());
            }
        });
    }

    public static void a(final C2752uB c2752uB, final Yg0 yg0, final int i, Ng0 ng0, final List list) {
        ng0.b(new InterfaceC1681hh0() { // from class: w7g
            @Override // com.android.tools.r8.internal.InterfaceC1681hh0
            public final void b() {
                Zg0.a(c2752uB, yg0, i, list);
            }
        });
    }
}
