package com.android.tools.r8.utils.structural;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.F2;
import com.android.tools.r8.graph.I2;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class o {
    public abstract void a(I2 i2);

    public abstract void a(String str);

    public final void a(List list) {
        Iterator it = list.iterator();
        defpackage.h hVar = new defpackage.h();
        q qVar = (q) this;
        while (it.hasNext()) {
            hVar.a(it.next(), qVar);
        }
    }

    public final void a(x[] xVarArr) {
        a(Arrays.asList(xVarArr));
    }

    public final void a(C0245l1 c0245l1) {
        c0245l1.o().a(new p(c0245l1, (q) this));
    }

    public final void a(C0322w2 c0322w2) {
        c0322w2.o().a(new p(c0322w2, (q) this));
    }

    public final void a(F2 f2) {
        f2.a(new Consumer() { // from class: dwh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((I2) obj);
            }
        }, new Consumer() { // from class: jwh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C0245l1) obj);
            }
        }, new Consumer() { // from class: mwh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C0322w2) obj);
            }
        });
    }
}
