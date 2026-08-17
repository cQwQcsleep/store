package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.A5;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.InterfaceC0332x5;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class F {
    public static final /* synthetic */ boolean c = true;
    public final C0333y a;
    public final ArrayList b = new ArrayList();

    public F(C0333y c0333y) {
        this.a = c0333y;
        c0333y.M();
    }

    public final ArrayList a(Collection collection, ExecutorService executorService) {
        if (!c && !this.b.isEmpty()) {
            x1f.a();
            return null;
        }
        com.android.tools.r8.K.a(this.a, collection, new Consumer() { // from class: sk4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((D2) obj);
            }
        }, executorService);
        this.b.sort(new Comparator() { // from class: tk4
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((InterfaceC0332x5) obj).getReference().b(((InterfaceC0332x5) obj2).getReference());
            }
        });
        return this.b;
    }

    public boolean b(InterfaceC0332x5 interfaceC0332x5) {
        return this.a.t().a(interfaceC0332x5).h;
    }

    public final void a(com.android.tools.r8.graph.D2 d2) {
        if (a((InterfaceC0332x5) d2)) {
            d2.m(new Consumer() { // from class: qk4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a((A5) obj);
                }
            });
        }
    }

    public final boolean a(InterfaceC0332x5 interfaceC0332x5) {
        if (!b(interfaceC0332x5)) {
            return true;
        }
        synchronized (this.b) {
            this.b.add(interfaceC0332x5);
        }
        return false;
    }
}
