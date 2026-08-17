package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.internal.C2847vL;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class U4 extends T4.a {
    public final Collection c;

    public U4(List list) {
        super(C2847vL.a((Collection) list, new Function() { // from class: aye
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0231j1) obj).E0();
            }
        }));
        this.c = list;
    }

    @Override // com.android.tools.r8.graph.T4.a
    public final void a(Consumer consumer, Consumer consumer2) {
        super.a((Consumer<I2>) consumer, (Consumer<? super C0231j1>) consumer2);
        this.c.forEach(consumer2);
    }

    @Override // com.android.tools.r8.graph.T4.a
    public final boolean y() {
        return this.c.size() > 0;
    }
}
