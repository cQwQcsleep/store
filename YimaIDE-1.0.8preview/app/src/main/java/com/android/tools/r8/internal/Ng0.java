package com.android.tools.r8.internal;

import com.android.tools.r8.internal.Ng0;
import com.android.tools.r8.threading.ThreadingModule;
import defpackage.wk8;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Ng0 {
    public final ThreadingModule a;
    public final ExecutorService b;
    public final ArrayList c;

    public Ng0(ThreadingModule threadingModule, ExecutorService executorService, int i) {
        this.a = threadingModule;
        this.b = executorService;
        this.c = i > 0 ? new ArrayList(i) : new ArrayList();
    }

    public void a(Consumer consumer) {
        this.a.awaitFutures(this.c);
        if (consumer != null) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                consumer.accept(AbstractC0496Fr.a((Future) it.next()));
            }
        }
        this.c.clear();
    }

    public final void b(final InterfaceC1681hh0 interfaceC1681hh0) {
        a(new Callable() { // from class: aia
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Ng0.a(interfaceC1681hh0);
            }
        });
    }

    public Ng0(C2752uB c2752uB, ExecutorService executorService) {
        this(c2752uB.N(), executorService, -1);
    }

    public void a(Callable callable) {
        this.c.add(this.a.submit(callable, this.b));
    }

    public static /* synthetic */ Object a(InterfaceC1681hh0 interfaceC1681hh0) throws Throwable {
        interfaceC1681hh0.b();
        return null;
    }

    public final ArrayList a() {
        ArrayList arrayList = new ArrayList(this.c.size());
        a(new wk8(arrayList));
        return arrayList;
    }

    public final ArrayList a(final Predicate predicate) {
        if (predicate == null) {
            return a();
        }
        final ArrayList arrayList = new ArrayList();
        a(new Consumer() { // from class: zha
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Ng0.a(predicate, arrayList, obj);
            }
        });
        return arrayList;
    }

    public static /* synthetic */ void a(Predicate predicate, List list, Object obj) {
        if (predicate.test(obj)) {
            list.add(obj);
        }
    }
}
