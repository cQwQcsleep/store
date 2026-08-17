package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.L8;
import com.android.tools.r8.internal.N8;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class N8 extends O8 {
    public static final /* synthetic */ boolean c = true;
    public final C0333y a;
    public final Collection b;

    public N8(C0333y c0333y, ArrayList arrayList) {
        if (!c && arrayList.isEmpty()) {
            x1f.a();
            throw null;
        }
        this.a = c0333y;
        this.b = arrayList;
    }

    @Override // com.android.tools.r8.internal.O8
    public final void a(ExecutorService executorService, final Q8 q8) {
        if (!c && ((Set) this.b.stream().map(new Function() { // from class: aba
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((L8) obj).a();
            }
        }).collect(Collectors.toSet())).size() != this.b.size()) {
            x1f.a();
            return;
        }
        final C0509Ge c0509GeM = this.a.m();
        com.android.tools.r8.K.a(this.a, this.b, new Consumer() { // from class: bba
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                N8.a(c0509GeM, q8, (L8) obj);
            }
        }, executorService);
    }

    public static void a(C0509Ge c0509Ge, Q8 q8, L8 l8) {
        c0509Ge.getClass();
        C0405Ce c0405Ce = new C0405Ce(c0509Ge, l8);
        if (!C0509Ge.c) {
            c0509Ge.a(c0405Ce);
        }
        l8.a(c0405Ce, q8);
    }
}
