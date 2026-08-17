package com.android.tools.r8.graph;

import com.android.tools.r8.internal.InterfaceC1936kh0;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class T {
    public static final /* synthetic */ boolean f = true;
    public final InterfaceC0189d1 a;
    public final int b;
    public final HashSet c = new HashSet();
    public final ArrayDeque d = new ArrayDeque();
    public boolean e = false;

    public T(InterfaceC0189d1 interfaceC0189d1, int i) {
        this.a = interfaceC0189d1;
        this.b = i;
    }

    public abstract void a(E0 e0);

    public final void a(Iterable iterable, InterfaceC1936kh0 interfaceC1936kh0) throws Throwable {
        Iterator it = iterable.iterator();
        while (true) {
            if (!it.hasNext() && this.d.isEmpty()) {
                this.c.clear();
                return;
            }
            if (this.d.isEmpty()) {
                a((E0) it.next());
                if (this.d.isEmpty()) {
                    continue;
                }
            }
            E0 e0 = (E0) this.d.removeFirst();
            if (!this.c.add(e0)) {
                continue;
            } else {
                if (!f && this.b == 4 && !e0.a0()) {
                    x1f.a();
                    return;
                }
                interfaceC1936kh0.accept(e0);
            }
        }
    }
}
