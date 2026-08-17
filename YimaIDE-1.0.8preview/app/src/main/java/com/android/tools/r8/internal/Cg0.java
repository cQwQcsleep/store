package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Cg0 extends Ng0 {
    public Cg0(C2752uB c2752uB, ExecutorService executorService) {
        super(c2752uB, executorService);
    }

    @Override // com.android.tools.r8.internal.Ng0
    public final void a(Consumer consumer) {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList(this.c);
            this.c.clear();
        }
        while (!arrayList.isEmpty()) {
            this.a.awaitFutures(arrayList);
            if (consumer != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    consumer.accept(AbstractC0496Fr.a((Future) it.next()));
                }
            }
            arrayList = b();
        }
    }

    public final synchronized ArrayList b() {
        ArrayList arrayList;
        arrayList = new ArrayList(this.c);
        this.c.clear();
        return arrayList;
    }

    @Override // com.android.tools.r8.internal.Ng0
    public final synchronized void a(Callable callable) {
        super.a(callable);
    }
}
